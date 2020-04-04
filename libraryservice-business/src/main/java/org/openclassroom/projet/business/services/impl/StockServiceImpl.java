package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.StockService;
import org.openclassroom.projet.model.database.library.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends AbstractService implements StockService {

    @Override
    public void updateStock(int libraryId, String referenceBook, int value) {
        Stock stock = getDaoFactory().getStockRepository().findByStockIdNumberRefLibraryAndStockIdReferenceBook(libraryId, referenceBook);
        stock.setQuantityLoaned(stock.getQuantityLoaned() + value);
        getDaoFactory().getStockRepository().save(stock);
    }

}
