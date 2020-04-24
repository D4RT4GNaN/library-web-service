package libraryservice;

import generated.libraryservice.*;
import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.service.LoanId;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import utils.converters.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService extends AbstractWebInterface implements generated.libraryservice.LibraryService {

    // ---------------------- Batch -------------------------
    @Override
    public List<generated.libraryservice.Loan> checkExpiration() {
        List<Loan> loans = getServiceFactory().getLoanService().checkExpiration();
        return LoanConverter.fromDatabase(loans);
    }



    // ---------------------- Loan ------------------------
    @WebMethod
    public String addNewLoan(int libraryId, String bookReference, generated.libraryservice.Usager generatedUsager) {
        Book book = getServiceFactory().getBookFactory().getBooks(bookReference).get(0);
        org.openclassroom.projet.model.database.library.Library library = getServiceFactory().getLibraryService().getLibrary(libraryId);
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);

        LoanId loanId = new LoanId(book, library, new Usager(usagerDto));

        Loan loan = new Loan(loanId);

        getServiceFactory().getLoanService().addNewLoan(loan);
        getServiceFactory().getStockService().updateStock(libraryId, bookReference, 1);

        return "SUCCESS";
    }

    @WebMethod
    public boolean extendLoan(XMLGregorianCalendar borrowingDate, int libraryId, String bookReference, generated.libraryservice.Usager generatedUsager) throws LoanExtensionException {
        Date borrowingUtilDate = this.XMLGregorianCalendarToDate(borrowingDate);
        int usagerId = generatedUsager.getId();

        try {
            getServiceFactory().getLoanService().extendLoan(borrowingUtilDate, libraryId, bookReference, usagerId);
        } catch (Exception e) {
            throw new LoanExtensionException(e.getMessage(), new UnspecifiedFault());
        }

        return true;
    }

    @WebMethod
    public String returnBook(XMLGregorianCalendar borrowingDate, int libraryId, String bookReference, generated.libraryservice.Usager generatedUsager) {
        Date borrowingUtilDate = this.XMLGregorianCalendarToDate(borrowingDate);
        int usagerId = generatedUsager.getId();

        int quantity = getServiceFactory().getLoanService().closeLoan(borrowingUtilDate, libraryId, bookReference, usagerId);
        getServiceFactory().getStockService().updateStock(libraryId, bookReference, -quantity);

        return "SUCCESS";
    }

    @WebMethod
    public List<generated.libraryservice.Loan> getLoansFor(int userID) {
        List<Loan> loans = getServiceFactory().getLoanService().getLoansFor(userID);
        return LoanConverter.fromDatabase(loans);
    }



    // ---------------------- Book ------------------------
    @WebMethod
    public List<generated.libraryservice.Stock> getBookAvailability(String bookReference) {
        List<generated.libraryservice.Stock> generatedStocks = new ArrayList<>();

        List<Library> libraries = LibraryConverter.fromDatabase(getServiceFactory().getLibraryService().getLibraries());
        for (Library library : libraries) {
            Stock stockBook = getServiceFactory().getBookFactory().getStockForBook(library.getNumberRef(), bookReference);
            if ((stockBook.getQuantity() - stockBook.getQuantityLoaned()) > 0) { generatedStocks.add(StockConverter.fromDatabase(stockBook, library)); }
        }

        return generatedStocks;
    }

    @WebMethod
    public List<generated.libraryservice.Book> getBooksWithKeyword(String keyword) {
        List<Book> result = getServiceFactory().getBookFactory().getBooks(keyword);
        List<generated.libraryservice.Book> generatedBooks = BookConverter.fromDatabase(result);

        for (int i = 0; i <= generatedBooks.size() - 1; i++) {
            String bookReference = generatedBooks.get(i).getReference();
            List<org.openclassroom.projet.model.database.service.Comment> comments = getServiceFactory().getCommentService().getCommentsFor(bookReference);
            List<Comment> generatedComments = CommentConverter.fromDatabase(comments);
            generatedBooks.get(i).getComments().addAll(generatedComments);
        }

        return generatedBooks;
    }

    @WebMethod
    public void addComment(Comment comment) {
        getServiceFactory().getCommentService().addComment(CommentConverter.fromClient(comment));
    }



    // ---------------------- Usager ------------------------
    @WebMethod
    public String addUser(generated.libraryservice.Usager generatedUsager) throws RegisterException {
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);
        try {
            getServiceFactory().getUserService().save(usagerDto);
        } catch (Exception ex) {
            UsagerUnspecifiedFault fault = new UsagerUnspecifiedFault();
            fault.setUsager(generatedUsager);
            throw new RegisterException(ex.getMessage(), fault);
        }
        return "SUCCESS";
    }

    @WebMethod
    public generated.libraryservice.Usager connectUser(String identifier, String password) throws LoginException {
        Usager usager;
        try {
            usager = getServiceFactory().getUserService().login(identifier, password);
        } catch (Exception ex) {
            throw new LoginException(ex.getMessage(), new UnspecifiedFault());
        }
        return UsagerConverter.fromDatabase(usager);
    }

    @WebMethod
    public String validEmailWith(String token) {
        VerificationToken vToken = getServiceFactory().getUserService().verifyEmailFrom(token);
        getServiceFactory().getUserService().validAccountFor(vToken);
        return "SUCCESS";
    }

    @WebMethod
    public String resendVerificationEmail(String email) throws EmailSendingException {
        try {
            getServiceFactory().getUserService().resendVerificationEmail(email);
        } catch (Exception ex) {
            throw new EmailSendingException(ex.getMessage(), new UnspecifiedFault());
        }
        return "SUCCESS";
    }

    @WebMethod
    public String requestPasswordReset(String email) throws ForgotPasswordException {
        try {
            getServiceFactory().getUserService().sendEmailToResetPasswordFor(email);
        } catch (Exception e) {
            throw new ForgotPasswordException(e.getMessage(), new UnspecifiedFault());
        }
        return "SUCCESS";
    }

    @WebMethod
    public String resetPassword(String token, String newPassword, String confirmNewPassword) {
        getServiceFactory().getUserService().createNewPasswordForUsagerWith(token, newPassword, confirmNewPassword);
        return "SUCCESS";
    }

    @WebMethod
    public String updatePassword(String email, String newPassword, String confirmNewPassword) {
        getServiceFactory().getUserService().changeUserPassword(email, newPassword, confirmNewPassword);
        return "SUCCESS";
    }

    @WebMethod
    public String updateUserInfos(String email, generated.libraryservice.Usager usager) throws UpdateUserException {
        UsagerDto usagerDto = UsagerConverter.fromClient(usager);
        Usager convertedUsager = new Usager(usagerDto);
        try {
            getServiceFactory().getUserService().updateUsagerInfos(email, convertedUsager);
        } catch (Exception ex) {
            throw new UpdateUserException(ex.getMessage(), new UnspecifiedFault());
        }
        return "SUCCESS";
    }


}