package utils.converters;

import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.library.Stock;

public class LibraryConverter {

    public static generated.libraryservice.Library fromDatabase(Library library) {
        generated.libraryservice.Library generatedLibrary = new generated.libraryservice.Library();

        generatedLibrary.setNumberRef(library.getNumberRef());
        generatedLibrary.setName(library.getName());
        generatedLibrary.setAddress(library.getAddress());

        return generatedLibrary;
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
