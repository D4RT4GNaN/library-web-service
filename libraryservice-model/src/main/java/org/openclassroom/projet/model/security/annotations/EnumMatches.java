package org.openclassroom.projet.model.security.annotations;

import org.openclassroom.projet.model.security.validators.EnumMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Verifies that the field value matches one of the values in the enum file.
 * */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumMatchesValidator.class)
@Documented
public @interface EnumMatches {

    Class<? extends Enum<?>> enumClass();
    String message() default "Token type is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
