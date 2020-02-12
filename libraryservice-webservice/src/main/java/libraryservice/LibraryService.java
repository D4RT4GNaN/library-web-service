package libraryservice;

import generated.libraryservice.Library;
import org.openclassroom.projet.model.database.library.Book;
import org.openclassroom.projet.model.database.library.Stock;
import org.openclassroom.projet.model.database.service.Loan;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import utils.converters.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService extends AbstractWebInterface implements generated.libraryservice.LibraryService {

    // ---------------------- Loan ------------------------
    @WebMethod
    public String addNewLoan(generated.libraryservice.Loan generatedLoan) {
        Loan loan = LoanConverter.fromClient(generatedLoan);
        getServiceFactory().getLoanService().addNewLoan(loan);
        return "SUCCESS";
    }

    @WebMethod
    public boolean extendLoan(XMLGregorianCalendar borrowingDate, String bookReference, int userID) {
        return false;
    }

    @WebMethod
    public String returnBook(XMLGregorianCalendar borrowingDate, String bookReference, int userID) {
        return null;
    }

    @WebMethod
    public String getStatusLoan(String bookReference, int userID) {
        return getServiceFactory().getLoanService().getStatusLoan(bookReference, userID);
    }

    @WebMethod
    public List<generated.libraryservice.Loan> getLoansFor(int userID) {
        List<Loan> loans = getServiceFactory().getLoanService().getLoansFor(userID);
        return LoanConverter.fromDatabase(loans);
    }



    // ---------------------- Book ------------------------
    @WebMethod
    public List<generated.libraryservice.Stock> getBookAvailability(List<Integer> libraryIds, String bookReference) {
        List<generated.libraryservice.Stock> generatedStocks = new ArrayList<>();

        for (int libraryId : libraryIds) {
            Library library = LibraryConverter.fromDatabase(getServiceFactory().getLibraryService().getLibrary(libraryId));
            Stock stockBook = getServiceFactory().getBookFactory().getStockForBook(libraryId, bookReference);
            generatedStocks.add(StockConverter.fromDatabase(stockBook, library));
        }

        return generatedStocks;
    }

    @WebMethod
    public List<generated.libraryservice.Book> getBooksWithKeyword(String keyword) {
        List<Book> result = getServiceFactory().getBookFactory().getBooks(keyword);
        return BookConverter.fromDatabase(result);
    }



    // ---------------------- Usager ------------------------
    @WebMethod
    public String addUser(generated.libraryservice.Usager generatedUsager) {
        UsagerDto usagerDto = UsagerConverter.fromClient(generatedUsager);
        getServiceFactory().getUserService().save(usagerDto);
        return "SUCCESS";
    }

    @WebMethod
    public generated.libraryservice.Usager connectUser(String identifier, String password) {
        Usager usager = getServiceFactory().getUserService().login(identifier, password);
        return UsagerConverter.fromDatabase(usager);
    }

    @WebMethod
    public String validEmailWith(String token) {
        VerificationToken vToken = getServiceFactory().getUserService().verifyEmailFrom(token);
        getServiceFactory().getUserService().validAccountFor(vToken);
        return "SUCCESS";
    }

    @WebMethod
    public String resendVerificationEmail(String email) {
        getServiceFactory().getUserService().resendVerificationEmail(email);
        return "SUCCESS";
    }

    @WebMethod
    public String requestPasswordReset(String email) {
        getServiceFactory().getUserService().sendEmailToResetPasswordFor(email);
        return "SUCCESS";
    }

    @WebMethod
    public String resetPassword(String token, String newPassword, String confirmNewPassword) {
        //getServiceFactory().getUserService().validatePassword(newPassword, confirmNewPassword);
        getServiceFactory().getUserService().createNewPasswordForUsagerWith(token, newPassword, confirmNewPassword);
        return "SUCCESS";
    }

    @WebMethod
    public String updatePassword(String email, String newPassword, String confirmNewPassword) {
        //getServiceFactory().getUserService().validatePassword(newPassword, confirmNewPassword);
        getServiceFactory().getUserService().changeUserPassword(email, newPassword, confirmNewPassword);
        return "SUCCESS";
    }

    @WebMethod
    public String updateUserInfos(String email, generated.libraryservice.Usager usager) {
        UsagerDto usagerDto = UsagerConverter.fromClient(usager);
        Usager convertedUsager = new Usager(usagerDto);
        getServiceFactory().getUserService().updateUsagerInfos(email, convertedUsager);
        return "SUCCESS";
    }


}