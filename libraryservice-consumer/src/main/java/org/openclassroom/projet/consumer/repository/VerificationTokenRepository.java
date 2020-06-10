package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the {@link VerificationToken} object.
 * */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    /**
     * Allows you to find a {@link VerificationToken verification token} from its token.
     * */
    VerificationToken findByToken(String token);



    /**
     * Allows you to find a {@link VerificationToken verification token} from its {@link Usager user}.
     * */
    VerificationToken findByUsager(Usager usager);

}
