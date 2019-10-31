package org.openclassroom.projet.consumer;

import org.openclassroom.projet.consumer.contract.dao.UsagerDao;

public interface DaoFactory {
    UsagerDao getUsagerDao();
}
