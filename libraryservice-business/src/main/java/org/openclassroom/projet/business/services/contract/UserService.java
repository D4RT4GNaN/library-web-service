package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;

public interface UserService {

    void save(Usager usager);
    Usager login(String username, String password);
    VerificationToken verifyEmailFrom(String token);
    void validAccountFor(VerificationToken vToken);

}
