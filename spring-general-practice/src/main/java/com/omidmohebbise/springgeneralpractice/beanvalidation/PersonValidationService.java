package com.omidmohebbise.springgeneralpractice.beanvalidation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
public class PersonValidationService implements CommandLineRunner {
    private final Validator validator;

    @Autowired
    public PersonValidationService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void run(String... args) {
        PersonEO validPerson = new PersonEO();
        validPerson.setName("John Doe");
        validPerson.setAge(30);
        validPerson.setEmail("john.doe@example.com");

        PersonEO invalidPerson = new PersonEO();
        invalidPerson.setName(""); // Invalid: blank name
        invalidPerson.setAge(-5);   // Invalid: age < 0
        invalidPerson.setEmail("not-an-email"); // Invalid: not an email

        validateAndPrint(validPerson);
        validateAndPrint(invalidPerson);
    }

    private void validateAndPrint(@Valid PersonEO person) {
        try {
            var violations = validator.validate(person);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            System.out.println("Valid person: " + person.getName());
        } catch (ConstraintViolationException e) {
            System.out.println("Validation failed for person: " + person.getName());
            e.getConstraintViolations().forEach(v ->
                System.out.println(v.getPropertyPath() + ": " + v.getMessage())
            );
        }
    }
}
