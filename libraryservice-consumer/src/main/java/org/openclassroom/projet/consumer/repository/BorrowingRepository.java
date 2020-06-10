package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.service.Borrowing;
import org.openclassroom.projet.model.database.service.BorrowingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * JPA Repository for the {@link Borrowing} object.
 * */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, BorrowingId> {

    /**
     * Allows you to find a {@link Borrowing borrowing} from the reference of {@link org.openclassroom.projet.model.database.library.Book book} associated.
     * */
    Borrowing findByBorrowingId_BorrowingDateAndBorrowingId_Library_NumberRefAndBorrowingId_Usager_IdAndBorrowingId_Book_ReferenceAndStatusNot(Date borrowingDate, int libraryId, int usagerId, String bookReference, String status);



    /**
     * Allows you to find the {@link org.openclassroom.projet.model.database.usager.Usager user's} {@link Borrowing borrowing} list.
     * */
    List<Borrowing> findByBorrowingId_UsagerId(int usagerId);



    /**
     * Allows you to find a list of {@link Borrowing borrowing} whose status does not correspond to the one selected.
     * */
    List<Borrowing> findByStatusNot(String status);

}
