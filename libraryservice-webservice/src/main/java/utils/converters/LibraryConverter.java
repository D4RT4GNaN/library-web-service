package utils.converters;

import generated.libraryservice.GeneratedLibrary;
import org.openclassroom.projet.model.database.library.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows conversion between {@link GeneratedLibrary} and {@link Library}.
 * */
public class LibraryConverter {

    /**
     * Converts a {@link Library} object from the database to a {@link GeneratedLibrary} object.
     *
     * @param library - {@link Library} object from database.
     *
     * @return A {@link GeneratedLibrary} object for sending to the client via WSDL.
     * */
    public static GeneratedLibrary fromDatabase(Library library) {
        GeneratedLibrary generatedLibrary = new GeneratedLibrary();

        generatedLibrary.setNumberRef(library.getNumberRef());
        generatedLibrary.setName(library.getName());
        generatedLibrary.setAddress(library.getAddress());

        return generatedLibrary;
    }



    /**
     * Converts a list of {@link Library} object from the database to a list of {@link GeneratedLibrary} object.
     *
     * @param libraries - A list of {@link Library} object from database.
     *
     * @return A list of {@link GeneratedLibrary} object for sending to the client via WSDL.
     * */
    public static List<GeneratedLibrary> fromDatabase(List<Library> libraries) {
        List<GeneratedLibrary> generatedLibraries = new ArrayList<>();

        for (Library library : libraries) {
            generatedLibraries.add(fromDatabase(library));
        }

        return generatedLibraries;
    }

}
