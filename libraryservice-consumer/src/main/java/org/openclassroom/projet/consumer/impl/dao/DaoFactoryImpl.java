package org.openclassroom.projet.consumer.impl.dao;

import org.openclassroom.projet.consumer.DaoFactory;
import org.openclassroom.projet.consumer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.inject.Named;

@Named("daoFactory")
@EnableJpaRepositories(basePackages = "org.openclassroom.projet.consumer.repository")
public class DaoFactoryImpl implements DaoFactory {

    @Autowired
    private UsagerRepository usagerRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public UsagerRepository getUsagerRepository() {
        return usagerRepository;
    }

    @Override
    public VerificationTokenRepository getVerificationTokenRepository() {
        return verificationTokenRepository;
    }

    @Override
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Override
    public StockRepository getStockRepository() { return stockRepository; }

    @Override
    public LibraryRepository getLibraryRepository() { return libraryRepository; }

}
