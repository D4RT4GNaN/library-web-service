package org.openclassroom.projet.consumer.impl.dao;

import org.openclassroom.projet.consumer.DaoFactory;
import org.openclassroom.projet.consumer.contract.dao.UsagerDao;

import javax.inject.Inject;
import javax.inject.Named;

@Named("daoFactory")
public class DaoFactoryImpl implements DaoFactory {

    @Inject
    private UsagerDao usagerDao;

    @Override
    public UsagerDao getUsagerDao() {
        return usagerDao;
    }
}
