package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import org.openclassroom.projet.model.enums.TokenTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

/**
 * Service implementation of the business module for the object {@link Usager}.
 * */
@Named("userService")
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    // ==================== Attributes ====================
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;



    // ==================== Public Methods ====================
    @Override
    public String save(UsagerDto usager) throws Exception {
        validUsagerConstraints(usager);
        if (!emailExist(usager.getEmail())) {
            throw new Exception("There is already user with this email!");
        } else {
            return createNewAccount(usager);
        }
    }

    @Override
    public Usager login(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.authenticate(username, password);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            Usager user = getDaoFactory().getUsagerRepository().findByEmail(username);
            if (user.isEnabled()) {
                return user;
            } else {
                throw new Exception("Your account is not yet activated. Check your mail before !");
            }
        } else {
            throw new Exception("Incorrect username or password !");
        }
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
    public boolean validAccountFor(VerificationToken vToken) {
        try {
            Usager usager = vToken.getUsager();
            if (vToken.getType().equals("EMAIL")) {
                usager.setEnabled(true);
                getDaoFactory().getUsagerRepository().save(usager);
                getDaoFactory().getVerificationTokenRepository().delete(vToken);

                return getDaoFactory().getUsagerRepository().findByEmail(usager.getEmail()).isEnabled();
            } else {
                throw new RuntimeException("There is no email token associated to this account !");
            }
        } catch (NullPointerException npe) {
            throw new RuntimeException("There is no account associated to this token !");
        }
    }

    @Override
    public boolean resendVerificationEmail(String email) throws Exception {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        if (!usager.isEnabled()) {
            String newToken = createVerificationToken(usager, TokenTypeEnum.EMAIL);
            return getMailService().resendConfirmationEmail(usager, newToken);
        } else {
            throw new Exception("This account is already activated !");
        }
    }

    @Override
    public String sendEmailToResetPasswordFor(String email) throws Exception {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        if (usager.isEnabled()) {
            String token = createVerificationToken(usager, TokenTypeEnum.PASSWORD);
            getMailService().sendResetPasswordEmail(usager, token);

            return token;
        } else {
            throw new Exception("This account is not already activated ! First check your email !");
        }
    }

    @Override
    public boolean createNewPasswordForUsagerWith(String token, String newPassword, String confirmation) {
        VerificationToken vToken = getDaoFactory().getVerificationTokenRepository().findByToken(token);
        try {
            Usager usager = vToken.getUsager();
            usager = validConstraintsWhenChangingPassword(usager, newPassword, confirmation);

            if (vToken.getType().equals("PASSWORD")) {
                getDaoFactory().getUsagerRepository().save(usager);
                getDaoFactory().getVerificationTokenRepository().delete(vToken);

                return this.authenticate(usager.getUsername(), newPassword).isAuthenticated();
            } else {
                throw new RuntimeException("This token is not for reset password !");
            }
        } catch (NullPointerException npe) {
            throw new RuntimeException("There is no account associated to this token for reset password !");
        }
    }

    @Override
    public boolean changeUserPassword(String email, String newPassword, String confirmation) {
        Usager usager = (Usager)userDetailsService.loadUserByUsername(email);
        usager = validConstraintsWhenChangingPassword(usager, newPassword, confirmation);

        if (usager.isEnabled()) {
            getDaoFactory().getUsagerRepository().save(usager);
            return this.authenticate(usager.getUsername(), newPassword).isAuthenticated();
        } else {
            throw new RuntimeException("This account is not already activated ! First check your email !");
        }
    }

    @Override
    public boolean updateUsagerInfos(String email, Usager usager) throws Exception {
        Usager dbUsager = (Usager)userDetailsService.loadUserByUsername(email);
        Usager tempUsager = dbUsager;

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

        return !tempUsager.equals(userDetailsService.loadUserByUsername(email));
    }



    // ==================== Private Methods ====================
    /**
     * Verifies the tested email address does not exist in the database.
     *
     * @param email - The email address to test.
     *
     * @return True if the email is unique.
     * */
    private Boolean emailExist(String email) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(email);
        if (usager != null) { return false; }
        return true;
    }



    /**
     * Transforms the transitory {@link UsagerDto} object into a {@link Usager} object then saves it in the database
     * and sends the verification email.
     *
     * @param usagerDto - The transitory object {@link UsagerDto} to be transformed.
     *
     * @return The newly random created token.
     * */
    private String createNewAccount(UsagerDto usagerDto) {
        Usager usager = new Usager(usagerDto);
        getDaoFactory().getUsagerRepository().save(usager);
        String token = createVerificationToken(usager, TokenTypeEnum.EMAIL);
        getMailService().sendConfirmationEmail(usager, token);
        return token;
    }



    /**
     * Allows you to create a new custom token for a user and save it in a database.
     * Allows to manage the reset of the latter in case it already exists.
     *
     * @param usager - The {@link Usager usager} whose token will be associated.
     * @param type - The type of token (password reset or account verification).
     *
     * @return The newly created random token.
     * */
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



    /**
     * Checks if the token is still valid in relation to its expiration date.
     *
     * @param token - The {@link VerificationToken} object that contains the token and its expiration date.
     *
     * @return if the token is still valid
     * */
    private boolean checkValidityOf(VerificationToken token) {
        Calendar cal = Calendar.getInstance();
        return token.getExpiryDate().getTime() - cal.getTime().getTime() >= 0;
    }



    /**
     * Validation of the attributes of the {@link UsagerDto} object in relation to the constraints imposed.
     *
     * @param usager - Transitional {@link UsagerDto} object containing the information to be validated.
     *
     * @return If all the attributes of the object meet the constraints.
     * */
    private boolean validUsagerConstraints(UsagerDto usager) {
        Set<ConstraintViolation<UsagerDto>> vViolations = getConstraintValidator().validate(usager);
        if (!vViolations.isEmpty()) {
            throw new ConstraintViolationException(vViolations);
        }
        return true;
    }



    /**
     * Re-validation of the attributes of a {@link Usager} object after changing a password.
     *
     * @param usager - The {@link Usager} object to validate.
     * @param newPassword - The new password chosen by the user.
     * @param confirmation - Confirmation of the new password chosen by the user.
     *
     * @return A validated {@link Usager} object containing the new password.
     * */
    private Usager validConstraintsWhenChangingPassword(Usager usager, String newPassword, String confirmation) {
        UsagerDto usagerDto = new UsagerDto(usager);
        int id = usager.getId();
        boolean enabled = usager.isEnabled();

        usagerDto.setPassword(newPassword);
        usagerDto.setConfirmPassword(confirmation);

        if (validUsagerConstraints(usagerDto)) {
            usager = new Usager(usagerDto);
            usager.setId(id);
            usager.setEnabled(enabled);
            return usager;
        } else {
            return null;
        }
    }



    /**
     * Retrieves a {@link Usager user} by his username and compares his password to authenticate him.
     *
     * @param username - The username to retrieve the user from the database.
     * @param password - The password to be tested with the user's password found in the database.
     *
     * @return A {@link UsernamePasswordAuthenticationToken} object containing the authentication response, as well as the {@link Usager user} to be returned.
     * */
    private UsernamePasswordAuthenticationToken authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return usernamePasswordAuthenticationToken;
    }

}
