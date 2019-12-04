package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Usager user = (Usager) obj;
        return user.getPassword().equals(user.getConfirmPassword())
                || bCryptPasswordEncoder.matches(user.getConfirmPassword(), user.getPassword());
    }

}
