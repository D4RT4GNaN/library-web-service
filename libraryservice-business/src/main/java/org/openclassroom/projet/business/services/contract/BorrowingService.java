package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.service.Borrowing;

import java.util.Date;
import java.util.List;

/**
 * Business module interface for the {@link Borrowing} object
 * */
public interface BorrowingService {

    /**
     * Adds a user's new {@link Borrowing borrowing} to the database.
     *
     * @param newBorrowing - The new {@link Borrowing borrowing} to be added.
     * */
    void addNewBorrowing(Borrowing newBorrowing);



    /**
     * Allows you to retrieve the list of {@link Borrowing borrowings} of a user.
     *
     * @param userID - The ID of the {@link org.openclassroom.projet.model.database.usager.Usager user}
     *               from whom you want to retrieve the list of {@link Borrowing borrowings}.
     *
     * @return A list of {@link Borrowing borrowings}
     * */
    List<Borrowing> getBorrowingsFor(int userID);



    /**
     * Extends the validity of a borrowing.
     *
     * @param borrowingDate - The borrowing date.
     * @param libraryId - The identifier of the {@link org.openclassroom.projet.model.database.library.Library library}
     *                  where the borrowing was made.
     * @param bookReference - The reference of the borrowed {@link org.openclassroom.projet.model.database.library.Book book}.
     * @param usagerId - The identifier of the user who made the {@link Borrowing borrowing}.
     *
     * @return If the {@link Borrowing borrowing} was indeed extended.
     * */
    boolean extendBookBorrowed(Date borrowingDate, int libraryId, String bookReference, int usagerId) throws Exception;



    /**
     * Allows you to return a {@link org.openclassroom.projet.model.database.library.Book book}
     * and close a {@link Borrowing borrowing}.
     *
     * @param borrowingDate - The borrowing date.
     * @param libraryId - The identifier of the {@link org.openclassroom.projet.model.database.library.Library library}
     *                  where the borrowing was made.
     * @param bookReference - The reference of the borrowed {@link org.openclassroom.projet.model.database.library.Book book}.
     * @param usagerId - The identifier of the user who made the {@link Borrowing borrowing}.
     *
     * @return The amount of {@link org.openclassroom.projet.model.database.library.Book books} returned.
     * */
    int stopBorrowing(Date borrowingDate, int libraryId, String bookReference, int usagerId);



    /**
     * Checks that all outstanding borrowings have not exceeded their due date,
     * otherwise they are returned to a list and their status is changed.
     *
     * @return The list of overdue {@link Borrowing}
     * */
    List<Borrowing> checkExpiration();

}
