package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;

public interface UserService {

    void save(UsagerDto usager);
    Usager login(String username, String password) throws Exception;
    VerificationToken verifyEmailFrom(String token);
    void validAccountFor(VerificationToken vToken);
    void resendVerificationEmail(String email);
    void sendEmailToResetPasswordFor(String email);
    void createNewPasswordForUsagerWith(String token, String newPassword, String confirmation);
    void changeUserPassword(String email, String newPassword, String confirmation);
    void updateUsagerInfos(String email, Usager usager);

}
