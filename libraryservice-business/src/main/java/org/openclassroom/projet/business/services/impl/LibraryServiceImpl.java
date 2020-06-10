package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.LibraryService;
import org.openclassroom.projet.model.database.library.Library;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation of the business module for the object {@link Library}.
 * */
@Service
public class LibraryServiceImpl extends AbstractService implements LibraryService {

    // ==================== Public Methods ====================
    @Override
    public Library getLibrary(int numberReference) {
        return getDaoFactory().getLibraryRepository().findByNumberRef(numberReference);
    }

    @Override
    public List<Library> getLibraries() {
        return getDaoFactory().getLibraryRepository().findAll();
    }

}
