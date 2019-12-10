package libraryservice;

import generated.libraryservice.Book;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Role;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import utils.converters.UsagerConverter;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "generated.libraryservice.LibraryService")
public class LibraryService implements generated.libraryservice.LibraryService {

    @Inject
    private UserService userService;

    private UsagerConverter usagerConverter = new UsagerConverter();

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
    public String addUser(generated.libraryservice.Usager generatedUsager) {
        Usager usager = usagerConverter.fromClient(generatedUsager);

        userService.save(usager);

        return "Compte créer avec succès";
    }

    @WebMethod
    public generated.libraryservice.Usager connectUser(String identifier, String password) {
        Usager usager = userService.login(identifier, password);

        return usagerConverter.fromDatabase(usager);
    }

}