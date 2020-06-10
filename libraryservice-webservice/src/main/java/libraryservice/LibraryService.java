package libraryservice;

import generated.libraryservice.*;
import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.service.Borrowing;
import org.openclassroom.projet.model.database.service.BorrowingId;
import org.openclassroom.projet.model.database.service.Comment;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import org.openclassroom.projet.model.enums.BorrowingStatusEnum;
import utils.converters.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Endpoint of the Web Service
 * */
@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService extends AbstractWebInterface implements generated.libraryservice.LibraryService {

    // ---------------------- Batch -------------------------
    /**
     * Checks the validity of the {@link GeneratedBorrowing borrowings} in the database
     *
     * @return The list of overdue {@link GeneratedBorrowing borrowings}.
     * */
    @Override
    public List<GeneratedBorrowing> checkExpiration() {
        List<Borrowing> borrowings = getServiceFactory().getBorrowingService().checkExpiration();
        return BorrowingConverter.fromDatabase(borrowings);
    }



    // ---------------------- Borrowing ------------------------
    /**
     * Adds the borrowing of a book from a user
     *
     * @param libraryId - The library's ID number.
     * @param bookReference - The reference of the borrowed book.
     * @param generatedUsager - The {@link GeneratedUsager user} who borrowed the book.
     *
     * @return The new {@link GeneratedBorrowing borrowing}.
     * */
    @WebMethod
    public GeneratedBorrowing borrowABook(int libraryId, String bookReference, GeneratedUsager generatedUsager) {
        Book book = getServiceFactory().getBookService().getBooks(bookReference).get(0);
        Library library = getServiceFactory().getLibraryService().getLibrary(libraryId);
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);

        BorrowingId borrowingId = new BorrowingId(book, library, new Usager(usagerDto));
        Borrowing borrowing = new Borrowing(borrowingId);

        getServiceFactory().getBorrowingService().addNewBorrowing(borrowing);
        getServiceFactory().getStockService().updateStock(libraryId, bookReference, 1);

        return BorrowingConverter.fromDatabase(borrowing);
    }

    /**
     * Increases the validity of a borrowing
     *
     * @param borrowingDate - The date the book was borrowed.
     * @param libraryId - The library's ID number.
     * @param bookReference - The reference of the borrowed book.
     * @param generatedUsager - The {@link GeneratedUsager user} who borrowed the book.
     *
     * @return If the {@link GeneratedBorrowing borrowing} was indeed extended.
     *
     * @exception BookBorrowingExtensionException - If the borrowing is still in progress or if it has already been extended.
     * */
    @WebMethod
    public boolean extendBookBorrowing(XMLGregorianCalendar borrowingDate, int libraryId, String bookReference, GeneratedUsager generatedUsager) throws BookBorrowingExtensionException {
        Date borrowingUtilDate = this.XMLGregorianCalendarToDate(borrowingDate);
        int usagerId = generatedUsager.getId();

        try {
            return getServiceFactory().getBorrowingService().extendBookBorrowed(borrowingUtilDate, libraryId, bookReference, usagerId);
        } catch (Exception e) {
            throw new BookBorrowingExtensionException(e.getMessage(), new UnspecifiedFault());
        }
    }

    /**
     * Returns a borrowed book
     *
     * @param borrowingDate - The date the book was borrowed.
     * @param libraryId - The library's ID number.
     * @param bookReference - The reference of the borrowed book.
     * @param generatedUsager - The {@link GeneratedUsager user} who borrowed the book.
     *
     * @return The status of the borrowing.
     * */
    @WebMethod
    public String returnBook(XMLGregorianCalendar borrowingDate, int libraryId, String bookReference, GeneratedUsager generatedUsager) {
        Date borrowingUtilDate = this.XMLGregorianCalendarToDate(borrowingDate);
        int usagerId = generatedUsager.getId();

        int quantity = getServiceFactory().getBorrowingService().stopBorrowing(borrowingUtilDate, libraryId, bookReference, usagerId);
        getServiceFactory().getStockService().updateStock(libraryId, bookReference, -quantity);

        return quantity != 0 ? BorrowingStatusEnum.RETURNED.name() : "NOT RETURNED";
    }

    /**
     * Retrieves the list of borrowings of a user
     *
     * @param userID - The user ID.
     *
     * @return The list of {@link GeneratedBorrowing borrowings} of a user
     * */
    @WebMethod
    public List<GeneratedBorrowing> getBorrowingFor(int userID) {
        List<Borrowing> borrowings = getServiceFactory().getBorrowingService().getBorrowingsFor(userID);
        return BorrowingConverter.fromDatabase(borrowings);
    }



    // ---------------------- Book ------------------------
    /**
     * Retrieves a list of the availability of a book in the libraries.
     *
     * @param bookReference - The reference of the book you want to know the availability of.
     *
     * @return The {@link GeneratedStock stock} list corresponding to the book.
     * */
    @WebMethod
    public List<GeneratedStock> getBookAvailability(String bookReference) {
        List<GeneratedStock> generatedStocks = new ArrayList<>();

        List<GeneratedLibrary> libraries = LibraryConverter.fromDatabase(getServiceFactory().getLibraryService().getLibraries());
        for (GeneratedLibrary library : libraries) {
            Stock stockBook = getServiceFactory().getStockService().getStockForBook(library.getNumberRef(), bookReference);
            if ((stockBook.getQuantity() - stockBook.getQuantityBorrowed()) > 0) { generatedStocks.add(StockConverter.fromDatabase(stockBook, library)); }
        }

        return generatedStocks;
    }

    /**
     * Retrieves the list of books corresponding to the keywords.
     *
     * @param keyword - The character string entered by the user in the search bar.
     *
     * @return An list of {@link GeneratedBook books}.
     * */
    @WebMethod
    public List<GeneratedBook> getBooksWithKeyword(String keyword) {
        List<Book> result = getServiceFactory().getBookService().getBooks(keyword);
        List<GeneratedBook> generatedBooks = BookConverter.fromDatabase(result);

        for (int i = 0; i <= generatedBooks.size() - 1; i++) {
            String bookReference = generatedBooks.get(i).getReference();
            List<Comment> comments = getServiceFactory().getCommentService().getCommentsFor(bookReference);
            List<GeneratedComment> generatedComments = CommentConverter.fromDatabase(comments);
            Collections.reverse(generatedComments);
            generatedBooks.get(i).getComments().addAll(generatedComments);
        }

        return generatedBooks;
    }

    /**
     * Adds a user's comment to a book.
     *
     * @param comment - {@link GeneratedComment} object that contains the comment and his author.
     *
     * @return The newly created {@link GeneratedComment comment}.
     * */
    @WebMethod
    public GeneratedComment addComment(GeneratedComment comment) {
        Comment returnedComment = getServiceFactory().getCommentService().addComment(CommentConverter.fromClient(comment));
        return CommentConverter.fromDatabase(returnedComment);
    }



    // ---------------------- Usager ------------------------
    /**
     * Allows you to create a new user.
     *
     * @param generatedUsager - The {@link GeneratedUsager} object that contains the user's information.
     *
     * @return The newly created {@link GeneratedUsager user}.
     *
     * @exception RegisterException - If one of the fields does not match the restrictions.
     * */
    @WebMethod
    public String addUser(GeneratedUsager generatedUsager) throws RegisterException {
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);
        try {
            return getServiceFactory().getUserService().save(usagerDto);
        } catch (Exception ex) {
            UsagerUnspecifiedFault fault = new UsagerUnspecifiedFault();
            fault.setUsager(generatedUsager);
            throw new RegisterException(ex.getMessage(), fault);
        }
    }

    /**
     * Allows a user to log in.
     *
     * @param identifier - The username, here it corresponds to the email.
     * @param password - The user's password.
     *
     * @return The {@link GeneratedUsager user} corresponding to the identifier/password pair.
     *
     * @exception LoginException - Wrong identifier/password pair or the identifier does not exist.
     * */
    @WebMethod
    public GeneratedUsager connectUser(String identifier, String password) throws LoginException {
        Usager usager;
        try {
            usager = getServiceFactory().getUserService().login(identifier, password);
        } catch (Exception ex) {
            throw new LoginException(ex.getMessage(), new UnspecifiedFault());
        }
        return UsagerConverter.fromDatabase(usager);
    }

    /**
     * Validate an email with the token that was sent to it.
     *
     * @param token - The string in the link sent by email to be able to identify the user.
     *
     * @return If the user's email has been validated.
     * */
    @WebMethod
    public boolean validEmailWith(String token) {
        VerificationToken vToken = getServiceFactory().getUserService().verifyEmailFrom(token);
        return getServiceFactory().getUserService().validAccountFor(vToken);
    }

    /**
     * Resend a verification email containing the verification link with the token.
     *
     * @param email - The email to which the new verification link should be sent.
     *
     * @return If the email was indeed sent.
     *
     * @exception EmailSendingException -
     * */
    @WebMethod
    public boolean resendVerificationEmail(String email) throws EmailSendingException {
        try {
            return getServiceFactory().getUserService().resendVerificationEmail(email);
        } catch (Exception ex) {
            throw new EmailSendingException(ex.getMessage(), new UnspecifiedFault());
        }
    }

    /**
     * Send an email with a link to change your password when you have lost it.
     *
     * @param email - The email of the user who has lost his password.
     *
     * @return If the email was indeed sent.
     *
     * @exception ForgotPasswordException - Only if the account is not yet activated.
     * */
    @WebMethod
    public String requestPasswordReset(String email) throws ForgotPasswordException {
        try {
            return getServiceFactory().getUserService().sendEmailToResetPasswordFor(email);
        } catch (Exception e) {
            throw new ForgotPasswordException(e.getMessage(), new UnspecifiedFault());
        }
    }

    /**
     * Change a user's password using a token that has been sent to them.
     *
     * @param token - The string in the link sent by email to be able to identify the user.
     * @param newPassword - The new password chosen by th user.
     * @param confirmNewPassword - Confirmation of the password.
     *
     * @return if the password has been updated.
     * */
    @WebMethod
    public boolean resetPassword(String token, String newPassword, String confirmNewPassword) {
        return getServiceFactory().getUserService().createNewPasswordForUsagerWith(token, newPassword, confirmNewPassword);
    }

    /**
     * Changes a user's password when he is logged in.
     *
     * @param email - The email of the user who wishes to change his password.
     * @param newPassword - The new password chosen by th user.
     * @param confirmNewPassword - Confirmation of the password.
     *
     * @return if the password has been updated.
     * */
    @WebMethod
    public boolean updatePassword(String email, String newPassword, String confirmNewPassword) {
        return getServiceFactory().getUserService().changeUserPassword(email, newPassword, confirmNewPassword);
    }

    /**
     * Allows you to change some user information.
     *
     * @param email - The email of the user to whom the modified information belongs.
     * @param generatedUsager - {@link GeneratedUsager} object that contains the changed information.
     *
     * @return if the information has been updated.
     *
     * @exception UpdateUserException - Only if there is a problem with the update of the user's email.
     * */
    @WebMethod
    public boolean updateUserInfos(String email, GeneratedUsager generatedUsager) throws UpdateUserException {
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);
        Usager usager = new Usager(usagerDto);
        try {
            return getServiceFactory().getUserService().updateUsagerInfos(email, usager);
        } catch (Exception ex) {
            throw new UpdateUserException(ex.getMessage(), new UnspecifiedFault());
        }
    }
}