package org.openclassroom.projet.technical.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Application security configuration class.
 * */
@Configuration
@EnableWebSecurity
@ComponentScan("org.openclassroom.projet")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // ==================== Attributes ====================
    @Autowired
    private CustomAuthenticationProvider authProvider;



    // ==================== Methods ====================
    /**
     * Tells the application to use the custom class of {@link org.springframework.security.authentication.AuthenticationProvider}.
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }



    // ==================== Beans ====================
    /**
     * Tells the application to use the custom class of {@link org.springframework.security.authentication.AuthenticationManager}.
     * */
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
