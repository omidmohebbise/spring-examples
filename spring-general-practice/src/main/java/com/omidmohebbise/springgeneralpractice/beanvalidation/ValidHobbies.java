package com.omidmohebbise.springgeneralpractice.beanvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidHobbiesValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidHobbies {
    String message() default "Invalid hobby found. Allowed hobbies: music, workout, party";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

