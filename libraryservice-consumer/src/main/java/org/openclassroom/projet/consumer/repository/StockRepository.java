package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.library.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockId> {

}
