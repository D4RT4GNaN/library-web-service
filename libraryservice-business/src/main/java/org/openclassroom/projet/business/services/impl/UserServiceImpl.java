package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import org.openclassroom.projet.model.enums.TokenTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

@Named("userService")
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    // ==================== Attributes ====================
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MailService mailService;

    @Value("${service.address}")
    private String serverAddress;



    // ==================== Public Methods ====================
    @Override
    public void save(UsagerDto usager) {
        validUsagerConstraints(usager);
        if (!emailExist(usager.getEmail())) {
            throw new RuntimeException("There is already user with this email!");
        } else {
            createNewAccount(usager);
        }
    }

    @Override
    public Usager login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            return getDaoFactory().getUsagerRepository().findByEmail(username);
        }

        return null;
    }

    @Override
    public VerificationToken verifyEmailFrom(String token) {
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByToken(token);
        if (vToken != null && vToken.getType().equals("EMAIL")) {
            if (checkValidityOf(vToken)) {
                return vToken;
            } else {
                throw new RuntimeException("the link is no longer valid !");
            }
        } else {
            throw new RuntimeException("This verification token doesn't exist or is not valid !");
        }
    }

    @Override
    public void validAccountFor(VerificationToken vToken) {
        try {
            Usager usager = vToken.getUsager();
            if (vToken.getType().equals("EMAIL")) {
                usager.setEnabled(true);
                getDaoFactory().getUsagerRepository().save(usager);
                getDaoFactory().getVerificationTokenRepository().delete(vToken);
            } else {
                throw new RuntimeException("There is no email token associated to this account !");
            }
        } catch (NullPointerException npe) {
            throw new RuntimeException("There is no account associated to this token !");
        }
    }

    @Override
    public void resendVerificationEmail(String email) {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        if (!usager.isEnabled()) {
            String newToken = createVerificationToken(usager, TokenTypeEnum.EMAIL);
            mailService.sendMailSMTP(usager.getEmail(), "Resend : Confirm your account", createVerificationEmailContent(newToken), true);
        } else {
            throw new RuntimeException("This account is already activated !");
        }
    }

    @Override
    public void sendEmailToResetPasswordFor(String email) {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        if (usager.isEnabled()) {
            String token = createVerificationToken(usager, TokenTypeEnum.PASSWORD);
            mailService.sendMailSMTP(usager.getEmail(), "Forgotten password", createResetPasswordEmailContent(token), true);
        } else {
            throw new RuntimeException("This account is not already activated ! First check your email !");
        }
    }

    @Override
    public void createNewPasswordForUsagerWith(String token, String newPassword, String confirmation) {
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByToken(token);
        try {
            Usager usager = vToken.getUsager();
            UsagerDto usagerDto = validConstraintsWhenChangingPassword(usager, newPassword, confirmation);

            if (vToken.getType().equals("PASSWORD")) {
                encryptPasswordAndSave(usagerDto);
                getDaoFactory().getVerificationTokenRepository().delete(vToken);
            } else {
                throw new RuntimeException("This token is not for reset password !");
            }
        } catch (NullPointerException npe) {
            throw new RuntimeException("There is no account associated to this token for reset password !");
        }
    }

    @Override
    public void changeUserPassword(String email, String newPassword, String confirmation) {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        UsagerDto usagerDto = validConstraintsWhenChangingPassword(usager, newPassword, confirmation);

        if (usager.isEnabled()) {
            encryptPasswordAndSave(usagerDto);
        } else {
            throw new RuntimeException("This account is not already activated ! First check your email !");
        }
    }

    @Override
    public void updateUsagerInfos(String email, Usager usager) {
        Usager dbUsager = (Usager)userDetailsService.loadUserByUsername(email);

        dbUsager.setFirstName(usager.getFirstName());
        dbUsager.setLastName(usager.getLastName());
        dbUsager.setAddress(usager.getAddress());

        if (!dbUsager.getEmail().equals(usager.getEmail())) {
            dbUsager.setEmail(usager.getEmail());
            dbUsager.setEnabled(false);
            getDaoFactory().getUsagerRepository().save(dbUsager);
            resendVerificationEmail(dbUsager.getEmail());
        } else {
            getDaoFactory().getUsagerRepository().save(dbUsager);
        }
    }



    // ==================== Inner Methods ====================
    private Boolean emailExist(String email) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(email);
        if (usager != null) { return false; }
        return true;
    }

    private void createNewAccount(UsagerDto usagerDto) {
        Usager usager = encryptPasswordAndSave(usagerDto);
        System.out.println("\n\n\n First \n\n\n");
        String token = createVerificationToken(usager, TokenTypeEnum.EMAIL);
        System.out.println("\n\n\n Second \n\n\n");
        mailService.sendMailSMTP(usager.getEmail(), "Confirm your account", createVerificationEmailContent(token) ,true);
        System.out.println("\n\n\n Third \n\n\n");
    }

    private Usager encryptPasswordAndSave(UsagerDto usagerDto) {
        Usager usager = new Usager(usagerDto);
        getDaoFactory().getUsagerRepository().save(usager);
        return usager;
    }

    private String createVerificationToken(Usager usager, TokenTypeEnum type) {
        String token = UUID.randomUUID().toString();
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByUsager(usager);
        if (vToken != null) {
            vToken.setToken(token);
            vToken.resetExpiryDate();
            vToken.setType(type.name());
        } else {
            vToken = new VerificationToken(usager, token, type);
        }
        getDaoFactory().getVerificationTokenRepository().save(vToken);
        return token;
    }

    private String createVerificationEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "Thanks for signing up on our service! You must follow this link to activate your account:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + serverAddress + "?token=" + token + lineBreak + after;
    }

    private String createResetPasswordEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "To reset your password, follow this link and change it:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + serverAddress + "?token=" + token + lineBreak + after;
    }

    private boolean checkValidityOf(VerificationToken token) {
        Calendar cal = Calendar.getInstance();
        return token.getExpiryDate().getTime() - cal.getTime().getTime() >= 0;
    }

    private boolean validUsagerConstraints(UsagerDto usager) {
        Set<ConstraintViolation<UsagerDto>> vViolations = getConstraintValidator().validate(usager);
        if (!vViolations.isEmpty()) {
            throw new ConstraintViolationException(vViolations);
        }
        return true;
    }

    private UsagerDto validConstraintsWhenChangingPassword(Usager usager, String newPassword, String confirmation) {
        UsagerDto usagerDto = new UsagerDto(usager);
        usagerDto.setPassword(newPassword);
        usagerDto.setConfirmPassword(confirmation);

        return validUsagerConstraints(usagerDto) ? usagerDto : null;
    }

}
