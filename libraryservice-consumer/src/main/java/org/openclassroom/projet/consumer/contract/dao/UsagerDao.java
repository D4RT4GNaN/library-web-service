package org.openclassroom.projet.consumer.contract.dao;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagerDao extends JpaRepository<Usager, Integer> {
    Usager findByUsernameAndPassword(String username, String password);
}
