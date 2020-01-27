package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library findByNumberRef(int numberRef);

}
