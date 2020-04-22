package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.LoanService;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.enums.LoanStatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl extends AbstractService implements LoanService {

    private static String RETURNED = LoanStatusEnum.RETURNED.name();

    @Override
    public void addNewLoan(Loan newLoan) {
        Usager usager = getDaoFactory().getUsagerRepository().findByEmail(
                newLoan.getLoanId().getUsager().getEmail()
        );
        Loan dbLoan = getDaoFactory().getLoanRepository()
                .findByLoanId_BorrowingDateAndLoanId_Library_NumberRefAndLoanId_Usager_IdAndLoanId_Book_ReferenceAndStatusNot(
                        newLoan.getLoanId().getBorrowingDate(),
                        newLoan.getLoanId().getLibrary().getNumberRef(),
                        usager.getId(),
                        newLoan.getLoanId().getBook().getReference(),
                        RETURNED
                );

        newLoan.getLoanId().setUsager(usager);

        if (dbLoan != null) {
            newLoan.setQuantity(dbLoan.getQuantity() + 1);
        }

        getDaoFactory().getLoanRepository().save(newLoan);
    }

    /*@Override
    public String getStatusLoan(String bookReference, int userID) {
        Loan loan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, RETURNED);
        if (loan != null) {
            return loan.getStatus();
        } else {
            throw new RuntimeException("There is no loan for this reference : " + bookReference);
        }
    }*/

    @Override
    public List<Loan> getLoansFor(int userID) {
        return getDaoFactory().getLoanRepository().findByLoanId_UsagerId(userID);
    }

    /*@Override
    public boolean extendLoan(Loan loan) {
        String bookReference = loan.getLoanId().getBook().getReference();
        int userID = loan.getLoanId().getUsager().getId();
        Loan dbLoan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, RETURNED);

        if (dbLoan.getExtended()) { throw new RuntimeException("This loan is already extended !"); }
        if (dbLoan.isValid()) { throw new RuntimeException("This loan does not need to be extended."); }

        dbLoan.extendLoan();
        getDaoFactory().getLoanRepository().save(dbLoan);

        return true;
    }*/

    @Override
    public int closeLoan(Date borrowingDate, int libraryId, String bookReference, int usagerId) {
        Loan dbLoan = getDaoFactory().getLoanRepository()
                .findByLoanId_BorrowingDateAndLoanId_Library_NumberRefAndLoanId_Usager_IdAndLoanId_Book_ReferenceAndStatusNot(
                        borrowingDate,
                        libraryId,
                        usagerId,
                        bookReference,
                        RETURNED
                );

        dbLoan.setStatus(RETURNED);
        getDaoFactory().getLoanRepository().save(dbLoan);

        return dbLoan.getQuantity();
    }

    @Override
    public List<Loan> checkExpiration() {
        List<Loan> loans = getDaoFactory().getLoanRepository().findByStatusNot(RETURNED);
        List<Loan> loansOverdue = new ArrayList<>();

        for (Loan loan : loans) {
            if (!loan.isValid()) {
                int usagerID = loan.getLoanId().getUsager().getId();
                Usager usager = getDaoFactory().getUsagerRepository().findById(usagerID);
                getMailService().sendReminderEmail(usager);
                loansOverdue.add(loan);
            }
        }

        return loansOverdue;
    }

}
