package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        System.out.println(passwordEncoder);
        Usager user = (Usager) obj;
        if (!user.isEnabled()) {
            return user.getPassword().equals(user.getConfirmPassword())
                    || passwordEncoder.matches(user.getConfirmPassword(), user.getPassword());
        } else {
            return true;
        }

    }

}
