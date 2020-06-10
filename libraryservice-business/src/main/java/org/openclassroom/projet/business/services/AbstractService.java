package org.openclassroom.projet.business.services;

import org.openclassroom.projet.business.services.impl.MailService;
import org.openclassroom.projet.consumer.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Abstract class providing the DAO manager and the mail manager.
 * */
public abstract class AbstractService {

    // ==================== Attributes ====================
    @Inject
    private DaoFactory daoFactory;

    @Autowired
    private MailService mailService;



    // ==================== Getters/Setters ====================
    /**
     * Getter of the DAO Factory to access the different repositories of the database.
     * */
    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }



    /**
     * Setter of the DAO Factory to setup it with the {@link javax.sql.DataSource}
     * */
    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }



    /**
     * Getter of the {@link Validator} object necessary for the validation of the different models before registration in the database.
     * */
    protected Validator getConstraintValidator() {
        Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
        ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
        Validator vValidator = vFactory.getValidator();
        return vValidator;
    }



    /**
     * Getter of the MailService which controls the sending of emails from the application.
     * */
    protected MailService getMailService() {
        return mailService;
    }
}
