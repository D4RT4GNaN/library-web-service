package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.BorrowingService;
import org.openclassroom.projet.model.database.service.Borrowing;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.enums.BorrowingStatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service implementation of the business module for the object {@link Borrowing}.
 * */
@Service
public class BorrowingServiceImpl extends AbstractService implements BorrowingService {

    // ==================== Attributes ====================
    private static String RETURNED = BorrowingStatusEnum.RETURNED.name();



    // ==================== Public Methods ====================
    @Override
    public void addNewBorrowing(Borrowing newBorrowing) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(
                newBorrowing.getBorrowingId().getUsager().getEmail()
        );
        Borrowing dbBorrowing = getDaoFactory().getBorrowingRepository()
                .findByBorrowingId_BorrowingDateAndBorrowingId_Library_NumberRefAndBorrowingId_Usager_IdAndBorrowingId_Book_ReferenceAndStatusNot(
                        newBorrowing.getBorrowingId().getBorrowingDate(),
                        newBorrowing.getBorrowingId().getLibrary().getNumberRef(),
                        usager.getId(),
                        newBorrowing.getBorrowingId().getBook().getReference(),
                        RETURNED
                );

        newBorrowing.getBorrowingId().setUsager(usager);

        if (dbBorrowing != null) {
            newBorrowing.setQuantity(dbBorrowing.getQuantity() + 1);
        }

        getDaoFactory().getBorrowingRepository().save(newBorrowing);
    }



    @Override
    public List<Borrowing> getBorrowingsFor(int userID) {
        List<Borrowing> borrowings = getDaoFactory().getBorrowingRepository().findByBorrowingId_UsagerId(userID);
        for (Borrowing borrowing : borrowings) { borrowing.isValid(); }
        return borrowings;
    }



    @Override
    public boolean extendBookBorrowed(Date borrowingDate, int libraryId, String bookReference, int usagerId) throws Exception {
        Borrowing dbBorrowing = getDaoFactory().getBorrowingRepository().findByBorrowingId_BorrowingDateAndBorrowingId_Library_NumberRefAndBorrowingId_Usager_IdAndBorrowingId_Book_ReferenceAndStatusNot(
                borrowingDate,
                libraryId,
                usagerId,
                bookReference,
                RETURNED
        );

        if (dbBorrowing.getExtended()) { throw new Exception("This borrowing is already extended !"); }
        if (dbBorrowing.isValid()) { throw new Exception("This borrowing does not need to be extended."); }

        dbBorrowing.extendBorrowing();
        getDaoFactory().getBorrowingRepository().save(dbBorrowing);

        return controlBorrowing(dbBorrowing);
    }



    @Override
    public int stopBorrowing(Date borrowingDate, int libraryId, String bookReference, int usagerId) {
        Borrowing dbBorrowing = getDaoFactory().getBorrowingRepository()
                .findByBorrowingId_BorrowingDateAndBorrowingId_Library_NumberRefAndBorrowingId_Usager_IdAndBorrowingId_Book_ReferenceAndStatusNot(
                        borrowingDate,
                        libraryId,
                        usagerId,
                        bookReference,
                        RETURNED
                );

        dbBorrowing.setStatus(RETURNED);
        getDaoFactory().getBorrowingRepository().save(dbBorrowing);

        return dbBorrowing.getQuantity();
    }



    @Override
    public List<Borrowing> checkExpiration() {
        List<Borrowing> borrowings = getDaoFactory().getBorrowingRepository().findByStatusNot(RETURNED);
        List<Borrowing> overdueBorrowings = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            if (!borrowing.isValid()) {
                int usagerID = borrowing.getBorrowingId().getUsager().getId();
                Usager usager = getDaoFactory().getUsagerRepository().findById(usagerID);
                getMailService().sendReminderEmail(usager);
                overdueBorrowings.add(borrowing);
            }
        }

        return overdueBorrowings;
    }



    // ==================== Private Methods ====================
    /**
     * Check if the {@link Borrowing borrowing} has been registered.
     *
     * @param dbBorrowing - {@link Borrowing} object that contains the information to be tested.
     *
     * @return If the borrowing has been registered.
     * */
    private boolean controlBorrowing(Borrowing dbBorrowing) {
        Borrowing borrowing = getDaoFactory().getBorrowingRepository().findByBorrowingId_BorrowingDateAndBorrowingId_Library_NumberRefAndBorrowingId_Usager_IdAndBorrowingId_Book_ReferenceAndStatusNot(
                dbBorrowing.getBorrowingId().getBorrowingDate(),
                dbBorrowing.getBorrowingId().getLibrary().getNumberRef(),
                dbBorrowing.getBorrowingId().getUsager().getId(),
                dbBorrowing.getBorrowingId().getBook().getReference(),
                RETURNED
        );

        return borrowing != null;
    }

}
