package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.security.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates an email according to certain constraints.
 * */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // ==================== Attributes ====================
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";



    // ==================== Methods ====================
    /**
     * Override method for initialize attributes.
     * Not used here.
     * */
    @Override
    public void initialize(ValidEmail constraintAnnotation) {}



    /**
     * Override validation method.
     *
     * @param email - The email to test.
     * @param context - {@link ConstraintValidatorContext}
     *
     * @return If the email is valid according to the constraints.
     * */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return (validateEmail(email));
    }



    // ==================== Private Methods ====================
    /**
     * Compares an email with a pattern in regex format.
     *
     * @param email - The email to test.
     *
     * @return If the email matches the pattern.
     * */
    private boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
