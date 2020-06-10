package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the {@link Library} object.
 * */
@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    /**
     * Allows you to find a {@link Library library} from its number.
     * */
    Library findByNumberRef(int numberRef);

}
