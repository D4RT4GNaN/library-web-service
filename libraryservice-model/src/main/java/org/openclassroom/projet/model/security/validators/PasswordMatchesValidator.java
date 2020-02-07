package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.database.usager.Usager;
import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.security.annotations.PasswordMatches;
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
        UsagerDto user = (UsagerDto) obj;
        if (user.getConfirmPassword() != null) {
            System.out.println("\n\n\n Validation \n\n\n");
            return user.getPassword().equals(user.getConfirmPassword())
                    || passwordEncoder.matches(user.getConfirmPassword(), user.getPassword());
        } else {
            return true;
        }

    }

}
