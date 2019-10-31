package libraryservice;

import generated.libraryservice.Book;
import org.openclassroom.projet.consumer.DaoFactory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService extends AbstractService implements generated.libraryservice.LibraryService {

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
    public String addUser(String username, String password, String firstname, String lastname, String mail, String adress) {
        return getDaoFactory().getUsagerDao().addUser(username, password, firstname, lastname, mail, adress);
    }

    @WebMethod
    public String connectUser(String identifier, String password) {
        return null;
    }
}
