package org.openclassroom.projet.business.services.impl;

import org.openclassroom.projet.business.services.AbstractService;
import org.openclassroom.projet.business.services.contract.UserService;
import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Named("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(Usager usager) {
        Set<ConstraintViolation<Usager>> vViolations = getConstraintValidator().validate(usager);
        if (!vViolations.isEmpty()) {
            throw new ConstraintViolationException(vViolations);
        }

        usager.setPassword(bCryptPasswordEncoder.encode(usager.getPassword()));
        //usager.setRoles(new HashSet<>(getDaoFactory().getRoleRepository().findAll()));

        getDaoFactory().getUsagerRepository().save(usager);
    }

}
