package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Integer> {
    Usager findByUsername(String username);
}
