package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.StockService;
import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.library.StockId;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation of the business module for the object {@link Stock}.
 * */
@Service
public class StockServiceImpl extends AbstractService implements StockService {

    // ==================== Public Methods ====================
    @Override
    public void updateStock(int libraryId, String referenceBook, int value) {
        Stock stock = getDaoFactory().getStockRepository().findByStockId_LibaryNumberRefAndStockId_BookReference(libraryId, referenceBook);
        stock.setQuantityBorrowed(stock.getQuantityBorrowed() + value);
        getDaoFactory().getStockRepository().save(stock);
    }

    @Override
    public Stock getStockForBook(int libraryId, String bookReference) {
        Optional<Stock> stockOptional = getDaoFactory().getStockRepository().findById(new StockId(libraryId, bookReference));
        return stockOptional.isPresent() ? stockOptional.get() : null;
    }

}
