package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Service implementation of the business module for email sending methods.
 * */
@Service
public class MailService {

    // ==================== Attributes ====================
    @Autowired
    @Qualifier("customProperties")
    private Properties customMailProperties;

    @Value("${client.url}")
    private String clientUrl;

    @Value("${client.newpassword.url}")
    private String newPasswordUrl;

    @Value("${client.confirmemail.url}")
    private String confirmEmailUrl;



    // ==================== Public Methods ====================
    /**
     * Sends an email reminder regarding an expired {@link org.openclassroom.projet.model.database.service.Borrowing borrowing}.
     *
     * @param usager - The {@link Usager user} to whom the mail is to be sent.
     * */
    public void sendReminderEmail(Usager usager) {
        String toAddress = usager.getEmail();
        String title = "Reminder : Borrowing expired !";
        String content = "Your borrowing has expired please extend it if it's not already done or return quickly your book !";

        sendMailSMTP(toAddress, title, content, true);
    }



    /**
     * Sending an account verification email.
     *
     * @param usager - The {@link Usager user} to whom the mail is to be sent.
     * @param token - A string contained in the link sent by email to identify the account to be validated.
     * */
    public void sendConfirmationEmail(Usager usager, String token) {
        sendMailSMTP(usager.getEmail(), "Confirm your account", createVerificationEmailContent(token) ,true);
    }



    /**
     * Sends a new account verification email.
     *
     * @param usager - The {@link Usager user} to whom the mail is to be sent.
     * @param newToken - The new token to replace the old one.
     *
     * @return If the email was indeed sent.
     * */
    public boolean resendConfirmationEmail(Usager usager, String newToken) {
        return sendMailSMTP(usager.getEmail(), "Resend : Confirm your account", createVerificationEmailContent(newToken), true);
    }



    /**
     * Sending an email containing a link to change your password when you have forgotten it.
     *
     * @param usager - The {@link Usager user} to whom the mail is to be sent.
     * @param token - A string contained in the link sent by e-mail to identify the account whose password needs to be changed.
     *
     * @return If the email was indeed sent.
     * */
    public boolean sendResetPasswordEmail(Usager usager, String token) {
        return sendMailSMTP(usager.getEmail(), "Forgotten password", createResetPasswordEmailContent(token), true);
    }



    // ==================== Private Methods ====================
    /**
     * The basic function that allows you to send mail via JavaMail.
     *
     * @param toAddress - The email address to which the email should be sent.
     * @param title - The title that appears in the header of the email.
     * @param content - The content of the email where the possible links can be found.
     * @param debug - Allows the display of messages in the logs to debug the code in case of error.
     *
     * @return If the email was indeed sent.
     * */
    private boolean sendMailSMTP(String toAddress, String title, String content, boolean debug) {
        boolean result = false;
        String username = customMailProperties.getProperty("mail.user");
        String password = customMailProperties.getProperty("mail.password");

        try {
            Session session = Session.getInstance(customMailProperties);

            Message message = new MimeMessage(session);
            // Header
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            // Sender
            message.setFrom(new InternetAddress(username));
            // Receiver
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            // Content
            message.setSubject(title);
            message.setText(content);
            message.setSentDate(new Date());

            // Debug and sending
            session.setDebug(debug);
            Transport.send(message, username, password);
            result = true;
        } catch ( AddressException ae ) {
            ae.printStackTrace();
        } catch ( MessagingException me ) {
            me.printStackTrace();
        }

        return result;
    }



    /**
     * Allows you to create the content of the account verification email by adding the personalized link containing the identification token.
     *
     * @param token - A string contained in the link sent by email to identify the account to be validated.
     *
     * @return the content of the account verification email
     * */
    private String createVerificationEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "Thanks for signing up on our service! You must follow this link to activate your account:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + clientUrl + confirmEmailUrl + "?token=" + token + lineBreak + after;
    }



    /**
     * Allows you to create the content of the password reset email by adding the personalized link containing the identification token.
     *
     * @param token - A string contained in the link sent by e-mail to identify the account whose password needs to be changed.
     *
     * @return the content of the password reset email
     * */
    private String createResetPasswordEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "To reset your password, follow this link and change it:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + clientUrl + newPasswordUrl + "?token=" + token + lineBreak + after;
    }

}
