package org.openclassroom.projet.business.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("customProperties")
    private Properties customMailProperties;

    public boolean sendMailSMTP(String toAddress, String title, String content, boolean debug) {
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
}
