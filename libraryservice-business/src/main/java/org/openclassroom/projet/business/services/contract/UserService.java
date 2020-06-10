package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;

/**
 * Business module interface for the {@link Usager} object
 * */
public interface UserService {

    /**
     * Saving a {@link Usager} object in the database from a {@link UsagerDto} object.
     *
     * @param usager - {@link UsagerDto} object that contains the user's information.
     *
     * @return The newly created token.
     * */
    String save(UsagerDto usager) throws Exception;



    /**
     * Tests a login/password pair to be able to log in a {@link Usager user}.
     *
     * @param username - The user ID.
     * @param password - The user's password.
     *
     * @return The {@link Usager user} corresponding to the login/password pair.
     * */
    Usager login(String username, String password) throws Exception;



    /**
     * Find out if there is a {@link Usager user} who matches the token passed in parameter.
     *
     * @param token - String in the link sent by email to the user.
     *
     * @return The {@link VerificationToken} object associated to the token.
     * */
    VerificationToken verifyEmailFrom(String token);



    /**
     * Activates the account associated with the {@link VerificationToken} object passed in parameter.
     *
     * @param vToken - The {@link VerificationToken} object previously recovered.
     *
     * @return If the account has been activated.
     * */
    boolean validAccountFor(VerificationToken vToken);



    /**
     * Resend the verification email.
     *
     * @param email - The email you need to send the email to.
     *
     * @return If the email was indeed sent.
     * */
    boolean resendVerificationEmail(String email) throws Exception;



    /**
     * Send an email with a link to change your password when you have lost it.
     *
     * @param email - The email you need to send the email to.
     *
     * @return The newly created token.
     * */
    String sendEmailToResetPasswordFor(String email) throws Exception;



    /**
     * Change the password when it has been lost by identifying the user with a token contained in the link
     * previously sent to him by email.
     *
     * @param token - The string contained in the link previously emailed to the user.
     * @param newPassword - The new password entered by the user.
     * @param confirmation - Confirmation of the new password entered by the user.
     *
     * @return If the password has been changed.
     * */
    boolean createNewPasswordForUsagerWith(String token, String newPassword, String confirmation);



    /**
     * Changing the user's password.
     *
     * @param email - The email you need to send the email to.
     * @param newPassword - The new password entered by the user.
     * @param confirmation - Confirmation of the new password entered by the user.
     *
     * @return If the password has been changed.
     * */
    boolean changeUserPassword(String email, String newPassword, String confirmation);



    /**
     * Modifies user information.
     *
     * @param email - The email of the user whose information is modified.
     * @param usager - The {@link Usager} object that contains the changes.
     *
     * @return If the user's information has been changed.
     * */
    boolean updateUsagerInfos(String email, Usager usager) throws Exception;

}
