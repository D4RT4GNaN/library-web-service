package org.openclassroom.projet.model.security.validators;

import com.google.common.base.Joiner;
import org.openclassroom.projet.model.security.annotations.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Validates a password according to certain constraints.
 * */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    // ==================== Methods ====================
    /**
     * Override method for initialize attributes.
     * Not used here.
     * */
    @Override
    public void initialize(ValidPassword arg0) {}



    /**
     * Override validation method.
     * Check whether the password contains 3 lower case minimum, 1 upper case minimum,
     * 5 digits maximum or special character and no white space.
     *
     * @param password - The password to test.
     * @param context - {@link ConstraintValidatorContext}
     *
     * @return If the email is valid according to the constraints.
     * */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterCharacteristicsRule(
                        3,
                        new CharacterRule(EnglishCharacterData.LowerCase, 3),
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit),
                        new CharacterRule(EnglishCharacterData.Special)
                ),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }

}
