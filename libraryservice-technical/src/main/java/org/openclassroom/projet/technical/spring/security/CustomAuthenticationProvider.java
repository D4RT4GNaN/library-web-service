package org.openclassroom.projet.technical.spring.security;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Implementation of the {@link AuthenticationProvider} interface.
 * It is called when the user wants to log in.
 * */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    // ==================== Attributes ====================
    @Autowired
    private PasswordEncoder passwordEncoder;



    // ==================== Methods ====================
    /**
     * Sends the user's username and password to the server to log in.
     *
     * @param authentication - The {@link Authentication} object that contains the user's login information.
     *
     * @return A {@link UsernamePasswordAuthenticationToken} object only if the connection was successful otherwise a null is returned.
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Usager user = (Usager)authentication.getPrincipal();

        if (user.getEmail().equals(name) && passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        } else {
            throw new BadCredentialsException("email or password is wrong !");
        }
    }



    /**
     * The {@link Class} argument in this method is really Class extends {@link Authentication}.
     * It will only ever be asked if it supports something that will be passed into the authenticate() method.
     *
     * @param authentication - An {@link Authentication} instance.
     *
     * @return If a {@link ProviderManager} recognise a particular Authentication instance type otherwise it will be skipped.
     * */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
