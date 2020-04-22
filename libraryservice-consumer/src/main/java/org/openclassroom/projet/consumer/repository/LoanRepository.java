package org.openclassroom.projet.consumer.repository;

import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.service.LoanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, LoanId> {

    Loan findByLoanId_BorrowingDateAndLoanId_Library_NumberRefAndLoanId_Usager_IdAndLoanId_Book_ReferenceAndStatusNot(Date borrowingDate, int libraryId, int usagerId, String bookReference, String status);
    List<Loan> findByLoanId_UsagerId(int usagerId);
    List<Loan> findByStatusNot(String status);

}
