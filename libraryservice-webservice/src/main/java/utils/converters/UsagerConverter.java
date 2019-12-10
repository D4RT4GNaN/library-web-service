package utils.converters;

import org.openclassroom.projet.model.database.usager.Usager;

import javax.inject.Named;

@Named("usagerConverter")
public class UsagerConverter {

    public generated.libraryservice.Usager fromDatabase(Usager usager) {
        generated.libraryservice.Usager generatedUsager = new generated.libraryservice.Usager();

        generatedUsager.setEmail(usager.getEmail());
        generatedUsager.setPassword(usager.getPassword());
        generatedUsager.setFirstname(usager.getFirstName());
        generatedUsager.setLastname(usager.getLastName());
        generatedUsager.setAdress(usager.getAddress());

        return generatedUsager;
    }

    public Usager fromClient(generated.libraryservice.Usager generatedUsager) {
        Usager usager = new Usager();

        usager.setEmail(generatedUsager.getEmail());
        usager.setPassword(generatedUsager.getPassword());
        usager.setConfirmPassword(generatedUsager.getConfirmPassword());
        usager.setFirstName(generatedUsager.getFirstname());
        usager.setLastName(generatedUsager.getLastname());
        usager.setAddress(generatedUsager.getAdress());

        return usager;
    }

}
