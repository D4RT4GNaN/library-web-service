package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        if (vToken != null) {
            if (checkValidityOf(vToken)) {
                return vToken;
            } else {
                throw new RuntimeException("the link is no longer valid !");
            }
        } else {
            throw new RuntimeException("This verification token doesn't exist !");
        }
    }

    @Override
    public void validAccountFor(VerificationToken vToken) {
        Usager usager = vToken.getUsager();
        usager.setEnabled(true);
        getDaoFactory().getUsagerRepository().save(usager);
        getDaoFactory().getVerificationTokenRepository().delete(vToken);
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
        String token = createVerificationToken(usager);
        mailService.sendMailSMTP(usager.getEmail(), "Confirm your account", createEmailContent(token) ,true);
    }

    private String createVerificationToken(Usager usager) {
        String token = UUID.randomUUID().toString();
        VerificationToken vToken = new VerificationToken(usager, token);
        getDaoFactory().getVerificationTokenRepository().save(vToken);
        return token;
    }

    private String createEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "Thanks for signing up on our service! You must follow this link to activate your account:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + serverAddress + "?token=" + token + lineBreak + after;
    }

    private boolean checkValidityOf(VerificationToken token) {
        Calendar cal = Calendar.getInstance();
        return token.getExpiryDate().getTime() - cal.getTime().getTime() >= 0;
    }

}
