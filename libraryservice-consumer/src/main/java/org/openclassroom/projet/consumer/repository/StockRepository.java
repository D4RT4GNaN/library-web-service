package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.library.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for the {@link Stock} object.
 * */
@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {

    /**
     * Allows you to find a {@link Stock stock} from the nunber of library and the reference of book associated.
     * */
    Stock findByStockId_LibaryNumberRefAndStockId_BookReference(int libraryNumberRef, String bookReference);

}
