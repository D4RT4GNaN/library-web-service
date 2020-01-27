package utils.converters;

import generated.libraryservice.Library;
import org.openclassroom.projet.model.database.library.Stock;

public class StockConverter {

    public static generated.libraryservice.Stock fromDatabase(Stock stock, Library library) {
        generated.libraryservice.Stock generatedStock = new generated.libraryservice.Stock();

        generatedStock.setLibrary(library);
        generatedStock.setReferenceBook(stock.getStockId().getReferenceBook());
        generatedStock.setQuantity(stock.getQuantity() - stock.getQuantityLoaned());

        return generatedStock;
    }

//    public static List<generated.libraryservice.Book> fromDatabase(List<Book> books) {
//        List<generated.libraryservice.Book> generatedBooks = new ArrayList<>();
//
//        for (Book book : books) {
//            generatedBooks.add(fromDatabase(book));
//        }
//
//        return generatedBooks;
//    }

}
