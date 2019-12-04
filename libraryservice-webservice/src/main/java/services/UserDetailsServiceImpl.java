package services;

import org.openclassroom.projet.consumer.repository.UsagerRepository;
import org.openclassroom.projet.model.database.usager.Role;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsagerRepository usagerRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usager usager = usagerRepository.findByUsername(username);

        Set grantedAuthorities = new HashSet<>();
        /*for (Object role : usager.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority((((Role) role).getName())));
        }*/

        return new org.springframework.security.core.userdetails.User(usager.getUsername(), usager.getPassword(), grantedAuthorities);
    }

}
