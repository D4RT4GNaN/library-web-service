package utils.converters;

import org.openclassroom.projet.model.database.library.Library;
import org.openclassroom.projet.model.database.library.Stock;

import java.util.ArrayList;
import java.util.List;

public class LibraryConverter {

    public static generated.libraryservice.Library fromDatabase(Library library) {
        generated.libraryservice.Library generatedLibrary = new generated.libraryservice.Library();

        generatedLibrary.setNumberRef(library.getNumberRef());
        generatedLibrary.setName(library.getName());
        generatedLibrary.setAddress(library.getAddress());

        return generatedLibrary;
    }

    public static List<generated.libraryservice.Library> fromDatabase(List<Library> libraries) {
        List<generated.libraryservice.Library> generatedLibraries = new ArrayList<>();

        for (Library library : libraries) {
            generatedLibraries.add(fromDatabase(library));
        }

        return generatedLibraries;
    }

}
