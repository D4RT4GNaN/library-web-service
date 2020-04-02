package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;
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
    public void sendReminderEmail(Usager usager) {
        String toAddress = usager.getEmail();
        String title = "Reminder : Loan expired !";
        String content = "Your loan has expired please extend it if it's not already done or return quickly your book !";

        sendMailSMTP(toAddress, title, content, true);
    }

    public void sendConfirmationEmail(Usager usager, String token) {
        sendMailSMTP(usager.getEmail(), "Confirm your account", createVerificationEmailContent(token) ,true);
    }

    public void resendConfirmationEmail(Usager usager, String newToken) {
        sendMailSMTP(usager.getEmail(), "Resend : Confirm your account", createVerificationEmailContent(newToken), true);
    }

    public void sendResetPasswordEmail(Usager usager, String token) {
        sendMailSMTP(usager.getEmail(), "Forgotten password", createResetPasswordEmailContent(token), true);
    }



    // ==================== Private Methods ====================
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

    private String createVerificationEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "Thanks for signing up on our service! You must follow this link to activate your account:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + clientUrl + confirmEmailUrl + "?token=" + token + lineBreak + after;
    }

    private String createResetPasswordEmailContent(String token) {
        String lineBreak = "\n\n";
        String before = "To reset your password, follow this link and change it:";
        String after = "Have fun, and don't hesitate to contact us with your feedback." + lineBreak
                + "The Library Team";

        return before + lineBreak + clientUrl + newPasswordUrl + "?token=" + token + lineBreak + after;
    }

}
