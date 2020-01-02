package org.openclassroom.projet.model.security.validators;

import org.openclassroom.projet.model.security.annotations.EnumMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnumMatchesValidator implements ConstraintValidator<EnumMatches, String> {

    private List<String> valueList = null;

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

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(value.toUpperCase());
    }
}
