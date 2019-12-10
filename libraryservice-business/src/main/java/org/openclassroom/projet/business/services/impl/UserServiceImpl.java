package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Named("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    @Override
    public void save(Usager usager) throws Exception {
        Set<ConstraintViolation<Usager>> vViolations = getConstraintValidator().validate(usager);
        if (!vViolations.isEmpty()) {
            throw new ConstraintViolationException(vViolations);
        }

        if (emailExist(usager.getEmail())) {
            usager.setPassword(bCryptPasswordEncoder.encode(usager.getPassword()));

            // Think to add Role when user is created
            //usager.setRoles(new HashSet<>(getDaoFactory().getRoleRepository().findAll()));

            getDaoFactory().getUsagerRepository().save(usager);
        } else {
            throw new Exception("There is already user with this email!");
        }
    }

    @Override
    public Usager login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            //SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return getDaoFactory().getUsagerRepository().findByEmail(username);
        }

        return null;
    }

    private Boolean emailExist(String email) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(email);

        if (usager != null) {
            return false;
        }

        return true;
    }

}
