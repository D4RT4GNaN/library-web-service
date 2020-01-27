package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.contract.BookService;
import org.openclassroom.projet.business.services.contract.LibraryService;
import org.openclassroom.projet.business.services.contract.ServiceFactory;
import org.openclassroom.projet.business.services.contract.UserService;
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
}
