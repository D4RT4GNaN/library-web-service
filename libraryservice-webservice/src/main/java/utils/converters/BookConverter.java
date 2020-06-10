package utils.converters;

import generated.libraryservice.GeneratedBook;
import org.openclassroom.projet.model.database.library.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows conversion between {@link GeneratedBook} and {@link Book}.
 * */
public class BookConverter {

    /**
     * Converts a {@link Book} object from the database to a {@link GeneratedBook} object.
     *
     * @param book - {@link Book} object from database.
     *
     * @return A {@link GeneratedBook} object for sending to the client via WSDL.
     * */
    public static GeneratedBook fromDatabase(Book book) {
        GeneratedBook generatedBook = new GeneratedBook();

        generatedBook.setReference(book.getReference());
        generatedBook.setImageUrl(book.getImageUrl());
        generatedBook.setTitle(book.getTitle());
        generatedBook.setAuthor(book.getAuthor());
        generatedBook.setSynopsis(book.getSynopsis());
        generatedBook.setCategory(book.getCategory());
        generatedBook.setPublisher(book.getPublisher());
        generatedBook.setLanguage(book.getLanguage());
        generatedBook.setMark(book.getMark());

        return generatedBook;
    }



    /**
     * Converts a list of {@link Book} object from the database to a list of {@link GeneratedBook} object.
     *
     * @param books - A list of {@link Book} object from database.
     *
     * @return A list of {@link GeneratedBook} object for sending to the client via WSDL.
     * */
    public static List<GeneratedBook> fromDatabase(List<Book> books) {
        List<GeneratedBook> generatedBooks = new ArrayList<>();

        for (Book book : books) {
            generatedBooks.add(fromDatabase(book));
        }

        return generatedBooks;
    }

}
