package libraryservice;

import generated.libraryservice.Book;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService implements generated.libraryservice.LibraryService {

    @Inject
    private UserService userService;

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

    @Override
    public String addUser(generated.libraryservice.Usager usager) {
        Usager user = new Usager();

        user.setUsername(usager.getUsername());
        user.setPassword(usager.getPassword());
        user.setConfirmPassword(usager.getConfirmPassword());
        user.setFirstName(usager.getFirstname());
        user.setLastName(usager.getLastname());
        user.setMail(usager.getMail());
        user.setAdress(usager.getAdress());
        user.setRole("USER");

        userService.save(user);

        return "Compte créer avec succès";
    }

    @WebMethod
    public String connectUser(String identifier, String password) {
        return null;
    }

}