package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.usager.Usager;

public interface UserService {

    void save(Usager usager) throws Exception;
    Usager login(String username, String password);

}
