package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.LoanService;
import org.openclassroom.projet.model.database.service.Loan;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl extends AbstractService implements LoanService {

    @Override
    public void addNewLoan(Loan newLoan) {
        getDaoFactory().getLoanRepository().save(newLoan);
    }

}
