package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.LoanService;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.enums.LoanStatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl extends AbstractService implements LoanService {

    private static String RETURNED = LoanStatusEnum.RETURNED.name();

    @Override
    public void addNewLoan(Loan newLoan) {
        getDaoFactory().getLoanRepository().save(newLoan);
    }

    @Override
    public String getStatusLoan(String bookReference, int userID) {
        Loan loan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, RETURNED);
        if (loan != null) {
            return loan.getStatus();
        } else {
            throw new RuntimeException("There is no loan for this reference : " + bookReference);
        }
    }

    @Override
    public List<Loan> getLoansFor(int userID) {
        return getDaoFactory().getLoanRepository().findByLoanId_UsagerId(userID);
    }

    @Override
    public boolean extendLoan(Loan loan) {
        String bookReference = loan.getLoanId().getReferenceBook();
        int userID = loan.getLoanId().getUsagerId();
        Loan dbLoan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, RETURNED);

        if (dbLoan.getExtended()) { throw new RuntimeException("This loan is already extended !"); }
        if (dbLoan.isValid()) { throw new RuntimeException("This loan does not need to be extended."); }

        dbLoan.extendLoan();
        getDaoFactory().getLoanRepository().save(dbLoan);

        return true;
    }

    @Override
    public String closeLoan(Loan loan) {
        String bookReference = loan.getLoanId().getReferenceBook();
        int userID = loan.getLoanId().getUsagerId();
        Loan dbLoan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, RETURNED);

        dbLoan.setStatus(RETURNED);
        getDaoFactory().getLoanRepository().save(dbLoan);

        return "SUCCESS";
    }

    @Override
    public List<Loan> checkExpiration() {
        List<Loan> loans = getDaoFactory().getLoanRepository().findByStatusNot(RETURNED);
        List<Loan> loansOverdue = new ArrayList<>();

        for (Loan loan : loans) {
            if (!loan.isValid()) {
                int usagerID = loan.getLoanId().getUsagerId();
                Usager usager = getDaoFactory().getUsagerRepository().findById(usagerID);
                getMailService().sendReminderEmail(usager);
                loansOverdue.add(loan);
            }
        }

        return loansOverdue;
    }

}
