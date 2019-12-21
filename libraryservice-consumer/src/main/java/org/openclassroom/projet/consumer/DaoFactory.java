package org.openclassroom.projet.consumer;

import org.openclassroom.projet.consumer.repository.UsagerRepository;
import org.openclassroom.projet.consumer.repository.VerificationTokenRepository;

public interface DaoFactory {
    UsagerRepository getUsagerRepository();
    //RoleRepository getRoleRepository();
    VerificationTokenRepository getVerificationTokenRepository();
}
