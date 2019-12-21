package libraryservice;

import generated.libraryservice.Book;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.VerificationToken;
import utils.converters.UsagerConverter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService extends AbstractWebInterface implements generated.libraryservice.LibraryService {

    @WebMethod
    public int getBookAvailability(String bookReference) {
        return 0;
    }

    @WebMethod
    public String addNewLoan(XMLGregorianCalendar borrowingDate, String bookReference, int userID) {
        return null;
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
    public String getStatusLoan(XMLGregorianCalendar borrowingDate, String bookReference, int userID) {
        return null;
    }

    @WebMethod
    public List<Book> getListBook(String keyword, String author, String publisher, String language) {
        return null;
    }

    @WebMethod
    public String addUser(generated.libraryservice.Usager generatedUsager) {
        Usager usager = UsagerConverter.fromClient(generatedUsager);
        getServiceFactory().getUserService().save(usager);
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

}