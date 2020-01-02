package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);
    VerificationToken findByUsager(Usager usager);

}
