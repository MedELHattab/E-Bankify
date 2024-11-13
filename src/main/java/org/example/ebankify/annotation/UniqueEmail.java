package org.example.ebankify.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.ebankify.validation.UniqueEmailValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
