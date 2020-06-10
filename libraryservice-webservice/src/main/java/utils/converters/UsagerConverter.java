package utils.converters;

import generated.libraryservice.GeneratedLibrary;
import generated.libraryservice.GeneratedUsager;
import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;

/**
 * Allows conversion between {@link GeneratedUsager} and {@link Usager}.
 * */
public class UsagerConverter {

    /**
     * Converts a {@link Usager} object from the database to a {@link GeneratedUsager} object.
     *
     * @param usager - {@link Usager} object from database.
     *
     * @return A {@link GeneratedUsager} object for sending to the client via WSDL.
     * */
    public static GeneratedUsager fromDatabase(Usager usager) {
        GeneratedUsager generatedUsager = new GeneratedUsager();

        generatedUsager.setId(usager.getId());
        generatedUsager.setEmail(usager.getEmail());
        generatedUsager.setPassword(usager.getPassword());
        generatedUsager.setFirstname(usager.getFirstName());
        generatedUsager.setLastname(usager.getLastName());
        generatedUsager.setAdress(usager.getAddress());

        return generatedUsager;
    }



    /**
     * Converts a {@link GeneratedUsager} object from the client to a {@link UsagerDto} object.
     *
     * @param generatedUsager - The {@link GeneratedUsager} object from client.
     *
     * @return The {@link UsagerDto} transition object to validate it.
     * */
    public static UsagerDto fromClient(GeneratedUsager generatedUsager) {
        UsagerDto usager = new UsagerDto();

        usager.setEmail(generatedUsager.getEmail());
        usager.setPassword(generatedUsager.getPassword());
        usager.setConfirmPassword(generatedUsager.getConfirmPassword());
        usager.setFirstName(generatedUsager.getFirstname());
        usager.setLastName(generatedUsager.getLastname());
        usager.setAddress(generatedUsager.getAdress());

        return usager;
    }

}
