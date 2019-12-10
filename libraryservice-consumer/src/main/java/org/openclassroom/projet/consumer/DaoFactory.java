package org.openclassroom.projet.consumer;

import org.openclassroom.projet.consumer.repository.UsagerRepository;

public interface DaoFactory {
    UsagerRepository getUsagerRepository();
    //RoleRepository getRoleRepository();
}
