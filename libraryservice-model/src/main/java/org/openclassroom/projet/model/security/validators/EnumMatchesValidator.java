package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.security.annotations.EnumMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Validates a field according to certain constraints.
 * */
public class EnumMatchesValidator implements ConstraintValidator<EnumMatches, String> {

    // ==================== Attributes ====================
    private List<String> valueList = null;



    // ==================== Methods ====================
    /**
     * Override method for initialize attributes.
     * Fill the value list with the value of the enum class.
     *
     * @param constraintAnnotation - Object from which the enumeration values are retrieved.
     * */
    @Override
    public void initialize(EnumMatches constraintAnnotation) {
        valueList = new ArrayList<String>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }
    }



    /**
     * Override validation method.
     * Check whether the value is contained in the enum list.
     *
     * @param value - The value to test.
     * @param constraintValidatorContext - {@link ConstraintValidatorContext}
     *
     * @return
     * */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(value.toUpperCase());
    }
}
