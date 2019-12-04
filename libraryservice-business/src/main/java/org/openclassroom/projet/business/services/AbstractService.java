package org.openclassroom.projet.business.services;

import org.openclassroom.projet.consumer.DaoFactory;

import javax.inject.Inject;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class AbstractService {

    @Inject
    private DaoFactory daoFactory;

    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }
    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    protected Validator getConstraintValidator() {
        Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
        ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
        Validator vValidator = vFactory.getValidator();
        return vValidator;
    }
}
