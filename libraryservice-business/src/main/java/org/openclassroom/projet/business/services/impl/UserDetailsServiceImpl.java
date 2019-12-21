package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.consumer.repository.UsagerRepository;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsagerRepository usagerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usager user = usagerRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with mail : " + username);
        } else {
            if (!user.isEnabled()) {
                throw new RuntimeException("Email not valid ! Check your email before !");
            }
            return user;
        }
    }

}
