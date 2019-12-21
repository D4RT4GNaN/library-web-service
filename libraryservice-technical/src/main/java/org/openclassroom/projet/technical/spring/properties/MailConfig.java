package org.openclassroom.projet.technical.spring.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public Properties customProperties() {
        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", env.getProperty("mail.protocol"));
        props.setProperty("mail.smtp.host", env.getProperty("mail.host"));
        props.setProperty("mail.smtp.auth", env.getProperty("mail.auth"));
        props.setProperty("mail.smtp.port", env.getProperty("mail.port"));
        props.setProperty("mail.smtp.socketFactory.port", env.getProperty("mail.port"));
        props.setProperty("mail.smtp.socketFactory.class", env.getProperty("mail.socket"));
        props.setProperty("mail.user", env.getProperty("mail.user"));
        props.setProperty("mail.password", env.getProperty("mail.password"));

        // Unsecured method only for development - Add certificate for production
        props.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.host"));

        return props;
    }
}
