package org.openclassroom.projet.business.services.contract;

/**
 * Business module interface
 * */
public interface ServiceFactory {

    /**
     * Getter for the {@link UserService}
     *
     * @return An instance of the {@link UserService}
     * */
    UserService getUserService();



    /**
     * Getter for the {@link BookService}
     *
     * @return An instance of the {@link BookService}
     * */
    BookService getBookService();



    /**
     * Getter for the {@link LibraryService}
     *
     * @return An instance of the {@link LibraryService}
     * */
    LibraryService getLibraryService();



    /**
     * Getter for the {@link BorrowingService}
     *
     * @return An instance of the {@link BorrowingService}
     * */
    BorrowingService getBorrowingService();



    /**
     * Getter for the {@link StockService}
     *
     * @return An instance of the {@link StockService}
     * */
    StockService getStockService();



    /**
     * Getter for the {@link CommentService}
     *
     * @return An instance of the {@link CommentService}
     * */
    CommentService getCommentService();

}
