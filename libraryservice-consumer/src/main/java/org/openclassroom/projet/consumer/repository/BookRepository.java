package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findBooksByReferenceIgnoreCaseContaining(String reference);
    List<Book> findBooksByTitleIgnoreCaseContaining(String title);
    List<Book> findBooksByAuthorIgnoreCaseContaining(String author);
    List<Book> findBooksByCategoryIgnoreCaseContaining(String category);
    List<Book> findBooksByPublisherIgnoreCaseContaining(String publisher);
    List<Book> findBooksByLanguageIgnoreCaseContaining(String language);

}
