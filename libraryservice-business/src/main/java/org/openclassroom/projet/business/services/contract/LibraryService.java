package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Library;

import java.util.List;

/**
 * Business module interface for the {@link Library} object
 * */
public interface LibraryService {

    /**
     * Getter of the {@link Library} object by its reference number.
     *
     * @param numberReference - The library identifier.
     *
     * @return The {@link Library} associated with the number passed in parameter.
     * */
    Library getLibrary(int numberReference);



    /**
     * Retrieves the list of {@link Library libraries} covered by the application.
     *
     * @return A list of {@link Library}
     * */
    List<Library> getLibraries();
}
