package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.BookService;
import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.library.StockId;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl extends AbstractService implements BookService {

    @Override
    public List<Book> getBooks(String keyword) {
        if (keyword.isEmpty())
            return getDaoFactory().getBookRepository().findAll();

        List<Book> booksByReference = getDaoFactory().getBookRepository().findBooksByReferenceIgnoreCaseContaining(keyword);
        List<Book> booksByTitle = getDaoFactory().getBookRepository().findBooksByTitleIgnoreCaseContaining(keyword);
        List<Book> booksByAuthor = getDaoFactory().getBookRepository().findBooksByAuthorIgnoreCaseContaining(keyword);
        List<Book> booksByCategory = getDaoFactory().getBookRepository().findBooksByCategoryIgnoreCaseContaining(keyword);
        List<Book> booksByPublisher = getDaoFactory().getBookRepository().findBooksByPublisherIgnoreCaseContaining(keyword);
        List<Book> booksByLanguage = getDaoFactory().getBookRepository().findBooksByLanguageIgnoreCaseContaining(keyword);

        Set<Book> result = new HashSet<>(booksByReference);
        result.addAll(booksByTitle);
        result.addAll(booksByAuthor);
        result.addAll(booksByCategory);
        result.addAll(booksByPublisher);
        result.addAll(booksByLanguage);

        return new ArrayList<>(result);
    }

    @Override
    public Stock getStockForBook(int libraryId, String bookReference) {
        Optional<Stock> stockOptional = getDaoFactory().getStockRepository().findById(new StockId(libraryId, bookReference));
        return stockOptional.isPresent() ? stockOptional.get() : null;
    }

}
