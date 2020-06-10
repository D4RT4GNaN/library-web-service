package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Book;

import java.util.List;

/**
 * Business module interface for the {@link Book} object
 * */
public interface BookService {

    /**
     * Search for a {@link Book} list based on a keyword.
     *
     * @param keyword - The keyword searched by the user.
     *
     * @return A list of {@link Book books} matching the keyword.
     * */
    List<Book> getBooks(String keyword);
}
