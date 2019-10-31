package org.openclassroom.projet.consumer.impl.dao;

import org.openclassroom.projet.consumer.contract.dao.UsagerDao;

import javax.inject.Named;

@Named("usagerDao")
public class UsagerDaoImpl implements UsagerDao {

    @Override
    public String addUser(String pUsername, String pPassword, String pFirstName, String pLastName, String pMail, String pAdress) {
        return "Test : " + pUsername + " - " + pMail;
    }
}
