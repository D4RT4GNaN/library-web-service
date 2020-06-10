package org.openclassroom.projet.consumer;

import org.openclassroom.projet.consumer.repository.*;

/**
 * Interface giving access to the DAO module using Spring's control inversion method.
 * */
public interface DaoFactory {

    /**
     * Access to the user part of the database.
     * */
    UsagerRepository getUsagerRepository();



    /**
     * Access to the verification token part of the database.
     * */
    VerificationTokenRepository getVerificationTokenRepository();



    /**
     * Access to the book part of the database.
     * */
    BookRepository getBookRepository();



    /**
     * Access to the stock part of the database.
     * */
    StockRepository getStockRepository();



    /**
     * Access to the library part of the database.
     * */
    LibraryRepository getLibraryRepository();



    /**
     * Access to the borrowing part of the database.
     * */
    BorrowingRepository getBorrowingRepository();



    /**
     * Access to the comment part of the database.
     * */
    CommentRepository getCommentRepository();

}
