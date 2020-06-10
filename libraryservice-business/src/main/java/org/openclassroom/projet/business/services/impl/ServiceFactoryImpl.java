package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.contract.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Implementation of Spring's control inversion for the Business module.
 * */
@Named("serviceFactory")
public class ServiceFactoryImpl implements ServiceFactory {

    // ==================== Attributes ====================
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private StockService stockService;

    @Autowired
    private CommentService commentService;



    // ==================== Public Methods ====================
    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public BookService getBookService() {
        return bookService;
    }

    @Override
    public LibraryService getLibraryService() { return libraryService; }

    @Override
    public BorrowingService getBorrowingService() { return borrowingService; }

    @Override
    public StockService getStockService() {
        return stockService;
    }

    @Override
    public CommentService getCommentService() { return commentService; }
}
