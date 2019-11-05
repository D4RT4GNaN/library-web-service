package org.openclassroom.projet.consumer.impl.dao;

import org.openclassroom.projet.consumer.DaoFactory;
import org.openclassroom.projet.consumer.contract.dao.UsagerDao;
import org.openclassroom.projet.model.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("daoFactory")
@EnableJpaRepositories(basePackages = "org.openclassroom.projet.consumer.contract.dao")
public class DaoFactoryImpl implements DaoFactory {

    @Autowired
    private UsagerDao usagerDao;

    @Override
    public UsagerDao getUsagerDao() {
        return usagerDao;
    }
}
