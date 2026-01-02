package com.omidmohebbise.springgeneralpractice.beanvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidHobbiesValidator implements ConstraintValidator<ValidHobbies, List<String>> {
    private static final Set<String> ALLOWED_HOBBIES = new HashSet<>(Arrays.asList(
            "music", "workout", "party"
    ));

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) return true; // @NotEmpty will handle null/empty
        for (String hobby : value) {
            if (!ALLOWED_HOBBIES.contains(hobby)) {
                return false;
            }
        }
        return true;
    }
}

