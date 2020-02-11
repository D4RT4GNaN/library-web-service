package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.service.Loan;

import java.util.Date;

public interface LoanService {

    void addNewLoan(Loan newLoan);
    String getStatusLoan(String bookReference, int userID);

}
