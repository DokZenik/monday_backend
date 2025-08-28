package org.example.metadata.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChronableConstraintValidator.class)
public @interface ValidChronable {
    String message() default "Start date must not be after end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
