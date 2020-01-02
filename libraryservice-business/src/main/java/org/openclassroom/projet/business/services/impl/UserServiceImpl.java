package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MailService mailService;

    @Value("${service.address}")
    private String serverAddress;



    // ==================== Public Methods ====================
    @Override // Think to add Role when user is created
    public void save(Usager usager) {
        Set<ConstraintViolation<Usager>> vViolations = getConstraintValidator().validate(usager);
        if (!vViolations.isEmpty()) {
            throw new ConstraintViolationException(vViolations);
        }

        if (emailExist(usager.getEmail())) {
            createNewAccount(usager);
        } else {
            throw new RuntimeException("There is already user with this email!");
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
                updateUsagerWithToken(usager, vToken);
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
    public String createNewPasswordForUsagerWith(String token, String newPassword) {
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByToken(token);
        try {
            Usager usager = vToken.getUsager();

            if (vToken.getType().equals("PASSWORD")) {
                usager.setPassword(passwordEncoder.encode(newPassword));
                updateUsagerWithToken(usager, vToken);
            } else {
                throw new RuntimeException("This token is not for reset password !");
            }
        } catch (NullPointerException npe) {
            throw new RuntimeException("There is no account associated to this token for reset password !");
        }

        return "SUCCESS";
    }



    // ==================== Inner Methods ====================
    private Boolean emailExist(String email) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(email);

        if (usager != null) { return false; }

        return true;
    }

    private void createNewAccount(Usager usager) {
        usager.setPassword(passwordEncoder.encode(usager.getPassword()));
        getDaoFactory().getUsagerRepository().save(usager);
        String token = createVerificationToken(usager, TokenTypeEnum.EMAIL);
        mailService.sendMailSMTP(usager.getEmail(), "Confirm your account", createVerificationEmailContent(token) ,true);
    }

    private String createVerificationToken(Usager usager, TokenTypeEnum type) {
        String token = UUID.randomUUID().toString();
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByUsager(usager);
        if (vToken != null) {
            vToken.setToken(token);
            vToken.resetExpiryDate();
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

    private void updateUsagerWithToken(Usager usager, VerificationToken vToken) {
        getDaoFactory().getUsagerRepository().save(usager);
        getDaoFactory().getVerificationTokenRepository().delete(vToken);
    }

}
