package org.openclassroom.projet.business.services.contract;

import org.openclassroom.projet.model.database.library.Library;

public interface LibraryService {

    Library getLibrary(int numberReference);

}
