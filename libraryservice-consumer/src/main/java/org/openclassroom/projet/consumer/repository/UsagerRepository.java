package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the {@link Usager} object.
 * */
@Repository
public interface UsagerRepository extends JpaRepository<Usager, Integer> {

    /**
     * Allows you to find a {@link Usager user} from its email.
     * */
    Usager findByEmail(String email);



    /**
     * Allows you to find a {@link Usager user} from its id.
     * */
    Usager findById(int id);
}
