package org.example.metadata.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.metadata.chronable.Chronable;

public class ChronableConstraintValidator implements ConstraintValidator<ValidChronable, Object> {


    @Override
    public void initialize(ValidChronable constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!(o instanceof Chronable chronable)) {
            return false;
        }

        return !chronable.getStartDate().isAfter(chronable.getEndDate());


    }
}
