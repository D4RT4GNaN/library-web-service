package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.contract.ServiceFactory;
import org.openclassroom.projet.business.services.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named("serviceFactory")
public class ServiceFactoryImpl implements ServiceFactory {
    @Autowired
    private UserService userService;

    @Override
    public UserService getUserService() {
        return userService;
    }
}
