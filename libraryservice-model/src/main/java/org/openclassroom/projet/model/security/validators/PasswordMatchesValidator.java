package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.database.usager.UsagerDto;
import org.openclassroom.projet.model.security.annotations.PasswordMatches;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Compares the equivalence of 2 passwords .
 * */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    // ==================== Attributes ====================
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    // ==================== Methods ====================
    /**
     * Override method for initialize attributes.
     * Not used here.
     * */
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}



    /**
     * Override validation method.
     * Compare the password and its confirmation.
     *
     * @param obj - The user object that contains the two password to test.
     * @param context - {@link ConstraintValidatorContext}
     *
     * @return If the password and its confirmation match.
     * */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UsagerDto user = (UsagerDto) obj;
        if (user.getConfirmPassword() != null) {
            return user.getPassword().equals(user.getConfirmPassword())
                    || passwordEncoder.matches(user.getConfirmPassword(), user.getPassword());
        } else {
            return true;
        }

    }

}
