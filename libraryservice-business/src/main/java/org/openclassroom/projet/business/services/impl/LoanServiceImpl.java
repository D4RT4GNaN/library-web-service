package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.LoanService;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.enums.LoanStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl extends AbstractService implements LoanService {

    @Override
    public void addNewLoan(Loan newLoan) {
        getDaoFactory().getLoanRepository().save(newLoan);
    }

    @Override
    public String getStatusLoan(String bookReference, int userID) {
        Loan loan = getDaoFactory().getLoanRepository().findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(bookReference, userID, LoanStatusEnum.RETURNED.name());
        if (loan != null) {
            return loan.getStatus();
        } else {
            throw new RuntimeException("There is no loan for this reference : " + bookReference);
        }
    }

    @Override
    public List<Loan> getLoansFor(int userID) {
        return getDaoFactory().getLoanRepository().findById_UsagerId(userID);
    }

}
