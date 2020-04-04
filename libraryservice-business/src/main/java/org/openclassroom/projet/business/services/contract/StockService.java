package org.openclassroom.projet.business.services.contract;

public interface StockService {

    void updateStock(int libraryId, String bookReference, int value);

}
