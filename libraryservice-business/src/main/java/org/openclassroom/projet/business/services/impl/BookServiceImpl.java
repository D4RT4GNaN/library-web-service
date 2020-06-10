package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.BookService;
import org.openclassroom.projet.model.database.library.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service implementation of the business module for the object {@link Book}.
 * */
@Service
public class BookServiceImpl extends AbstractService implements BookService {

    // ==================== Public Methods ====================
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

}
