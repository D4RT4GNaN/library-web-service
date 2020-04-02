package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Library;

import java.util.List;

public interface LibraryService {

    Library getLibrary(int numberReference);

    List<Library> getLibraries();
}
