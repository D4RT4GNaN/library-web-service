package org.openclassroom.projet.business.services.contract;

public interface ServiceFactory {

    UserService getUserService();
    BookService getBookFactory();
    LibraryService getLibraryService();
    LoanService getLoanService();

}
