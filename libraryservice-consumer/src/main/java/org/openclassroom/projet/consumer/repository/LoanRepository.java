package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.service.LoanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, LoanId> {

    Loan findByLoanId_ReferenceBookAndLoanId_UsagerIdAndStatusNot(String referenceBook, int usagerId, String status);
    List<Loan> findByLoanId_UsagerId(int usagerId);
    List<Loan> findByStatusNot(String status);

}
