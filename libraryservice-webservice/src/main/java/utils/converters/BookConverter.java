package utils.converters;

import org.openclassroom.projet.model.database.library.Book;

import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    public static generated.libraryservice.Book fromDatabase(Book book) {
        generated.libraryservice.Book generatedBook = new generated.libraryservice.Book();

        generatedBook.setReference(book.getReference());
        generatedBook.setImageUrl(book.getImageUrl());
        generatedBook.setTitle(book.getTitle());
        generatedBook.setAuthor(book.getAuthor());
        generatedBook.setSynopsis(book.getSynopsis());
        generatedBook.setCategory(book.getCategory());
        generatedBook.setPublisher(book.getPublisher());
        generatedBook.setLanguage(book.getLanguage());

        return generatedBook;
    }

    public static List<generated.libraryservice.Book> fromDatabase(List<Book> books) {
        List<generated.libraryservice.Book> generatedBooks = new ArrayList<>();

        for (Book book : books) {
            generatedBooks.add(fromDatabase(book));
        }

        return generatedBooks;
    }

}
