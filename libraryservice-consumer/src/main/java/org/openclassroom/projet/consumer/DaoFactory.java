package org.openclassroom.projet.consumer;

import org.openclassroom.projet.consumer.repository.*;

public interface DaoFactory {

    UsagerRepository getUsagerRepository();
    VerificationTokenRepository getVerificationTokenRepository();
    BookRepository getBookRepository();
    StockRepository getStockRepository();
    LibraryRepository getLibraryRepository();
    LoanRepository getLoanRepository();

}
