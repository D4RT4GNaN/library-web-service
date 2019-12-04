package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.usager.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {



}
