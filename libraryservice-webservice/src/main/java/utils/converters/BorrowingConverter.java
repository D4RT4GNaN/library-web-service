package utils.converters;

import generated.libraryservice.GeneratedBook;
import generated.libraryservice.GeneratedBorrowing;
import generated.libraryservice.GeneratedLibrary;
import generated.libraryservice.GeneratedUsager;
import org.openclassroom.projet.model.database.service.Borrowing;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows conversion between {@link GeneratedBorrowing} and {@link Borrowing}.
 * */
public class BorrowingConverter extends AbstractConverter {

    /**
     * Converts a {@link Borrowing} object from the database to a {@link GeneratedBorrowing} object.
     *
     * @param borrowing - {@link Borrowing} object from database.
     *
     * @return A {@link GeneratedBorrowing} object for sending to the client via WSDL.
     * */
    public static GeneratedBorrowing fromDatabase(Borrowing borrowing) {
        GeneratedBorrowing generatedBorrowing = new GeneratedBorrowing();

        try {
            GeneratedBook generatedBook = BookConverter.fromDatabase(borrowing.getBorrowingId().getBook());
            GeneratedUsager generatedUsager = UsagerConverter.fromDatabase(borrowing.getBorrowingId().getUsager());
            GeneratedLibrary generatedLibrary = LibraryConverter.fromDatabase(borrowing.getBorrowingId().getLibrary());

            generatedBorrowing.setBorrowingDate(toXMLGregorianCalendar(borrowing.getBorrowingId().getBorrowingDate()));
            generatedBorrowing.setBook(generatedBook);
            generatedBorrowing.setUsager(generatedUsager);
            generatedBorrowing.setLibrary(generatedLibrary);
            generatedBorrowing.setExpiryDate(toXMLGregorianCalendar(borrowing.getExpiryDate()));
            generatedBorrowing.setExtended(borrowing.getExtended());
            generatedBorrowing.setStatus(borrowing.getStatus());
            generatedBorrowing.setQuantity(borrowing.getQuantity());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return generatedBorrowing;
    }



    /**
     * Converts a list of {@link Borrowing} object from the database to a list of {@link GeneratedBorrowing} object.
     *
     * @param borrowings - A list of {@link Borrowing} object from database.
     *
     * @return A list of {@link GeneratedBorrowing} object for sending to the client via WSDL.
     * */
    public static List<GeneratedBorrowing> fromDatabase(List<Borrowing> borrowings) {
        List<GeneratedBorrowing> generatedBorrowings = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            generatedBorrowings.add(fromDatabase(borrowing));
        }

        return generatedBorrowings;
    }

}
