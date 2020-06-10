package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Stock;

/**
 * Business module interface for the {@link org.openclassroom.projet.model.database.library.Stock} object
 * */
public interface StockService {

    /**
     * Updates part of the inventory.
     *
     * @param libraryId - The library in which the stocks are updated.
     * @param bookReference - The reference of the book whose stocks are being updated.
     * @param value - The quantity to be added (positive number) or removed (negative number).
     * */
    void updateStock(int libraryId, String bookReference, int value);



    /**
     * Retrieves the available quantity of a book in a library.
     *
     * @param libraryId -
     * @param bookReference -
     *
     * @return A {@link Stock} object containing the {@link org.openclassroom.projet.model.database.library.Book book} quantity.
     * */
    Stock getStockForBook(int libraryId, String bookReference);

}
