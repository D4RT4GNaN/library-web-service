package org.openclassroom.projet.consumer.contract.dao;

public interface UsagerDao {
    String addUser(String pUsername, String pPassword, String pFirstName, String pLastName, String pMail, String pAdress);
}
