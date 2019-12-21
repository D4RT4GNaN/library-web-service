package org.openclassroom.projet.consumer.impl.dao;

import org.openclassroom.projet.consumer.DaoFactory;
import org.openclassroom.projet.consumer.repository.UsagerRepository;
import org.openclassroom.projet.consumer.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.inject.Named;

@Named("daoFactory")
@EnableJpaRepositories(basePackages = "org.openclassroom.projet.consumer.repository")
public class DaoFactoryImpl implements DaoFactory {

    @Autowired
    private UsagerRepository usagerRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    /*@Autowired
    private RoleRepository roleRepository;*/

    @Override
    public UsagerRepository getUsagerRepository() {
        return usagerRepository;
    }

    @Override
    public VerificationTokenRepository getVerificationTokenRepository() {
        return verificationTokenRepository;
    }

    /*@Override
    public RoleRepository getRoleRepository() { return roleRepository; }*/

}
