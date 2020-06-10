package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.consumer.repository.UsagerRepository;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of Spring security's {@link UserDetailsService} interface.
 * */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // ==================== Attributes ====================
    @Autowired
    private UsagerRepository usagerRepository;



    // ==================== Public Methods ====================
    /**
     * Override method to retrieve a database {@link Usager user} from his username.
     *
     * @param username - The username of the {@link Usager user} to be retrieved.
     *
     * @return A {@link UserDetails} object containing the searched {@link Usager user}.
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usager user = usagerRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user present with mail : " + username);
        }

        return user;
    }

}
