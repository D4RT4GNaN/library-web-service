package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for the {@link Book} object.
 * */
public interface BookRepository extends JpaRepository<Book, String> {

    /**
     * Allows you to find a {@link Book book's} list from its reference.
     * */
    List<Book> findBooksByReferenceIgnoreCaseContaining(String reference);



    /**
     * Allows you to find a {@link Book book's} list from its title.
     * */
    List<Book> findBooksByTitleIgnoreCaseContaining(String title);



    /**
     * Allows you to find a {@link Book book's} list from its author.
     * */
    List<Book> findBooksByAuthorIgnoreCaseContaining(String author);



    /**
     * Allows you to find a {@link Book book's} list from its category.
     * */
    List<Book> findBooksByCategoryIgnoreCaseContaining(String category);



    /**
     * Allows you to find a {@link Book book's} list from its publisher.
     * */
    List<Book> findBooksByPublisherIgnoreCaseContaining(String publisher);



    /**
     * Allows you to find a {@link Book book's} list from its language.
     * */
    List<Book> findBooksByLanguageIgnoreCaseContaining(String language);

}
