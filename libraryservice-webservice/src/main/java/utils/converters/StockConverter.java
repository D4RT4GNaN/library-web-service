package utils.converters;

import generated.libraryservice.GeneratedLibrary;
import generated.libraryservice.GeneratedStock;
import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.library.Stock;

/**
 * Allows conversion between {@link GeneratedStock}, {@link Library} and {@link Stock}.
 * */
public class StockConverter {

    /**
     * Pack a {@link Library} object and a {@link Stock} object into a {@link GeneratedStock} object.
     *
     * @param stock - {@link Stock} object from database that contains the book's availability.
     * @param library - {@link Library} object from database.
     *
     * @return A {@link GeneratedStock} object for sending to the client via WSDL.
     * */
    public static GeneratedStock fromDatabase(Stock stock, GeneratedLibrary library) {
        GeneratedStock generatedStock = new GeneratedStock();

        generatedStock.setLibrary(library);
        generatedStock.setReferenceBook(stock.getStockId().getBookReference());
        generatedStock.setQuantity(stock.getQuantity() - stock.getQuantityBorrowed());

        return generatedStock;
    }

}
