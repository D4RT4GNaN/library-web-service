package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Stock;

import java.util.List;

public interface BookService {
    List<Book> getBooks(String keyword);
    Stock getStockForBook(int libraryId, String bookReference);
}
