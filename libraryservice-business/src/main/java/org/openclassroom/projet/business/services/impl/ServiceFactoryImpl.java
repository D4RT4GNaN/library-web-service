package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.contract.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("serviceFactory")
public class ServiceFactoryImpl implements ServiceFactory {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private LoanService loanService;

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public BookService getBookFactory() {
        return bookService;
    }

    @Override
    public LibraryService getLibraryService() { return libraryService; }

    @Override
    public  LoanService getLoanService() { return loanService; }
}
