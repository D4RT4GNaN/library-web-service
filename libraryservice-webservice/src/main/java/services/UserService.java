package services;

import org.openclassroom.projet.model.database.usager.Usager;

public interface UserService {

    void save (Usager usager);
    Usager findByUsername(String username);

}
