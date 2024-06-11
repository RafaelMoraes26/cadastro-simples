package br.com.cadastro.simples.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class CustomConstraintViolationException extends ConstraintViolationException {

    private final Object invalidObject;

    public <T> CustomConstraintViolationException(Set<ConstraintViolation<T>> constraintViolations, T object) {
        super(constraintViolations);
        this.invalidObject = object;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder("Validation failed for object: ");
        message.append(invalidObject).append("\n");
        message.append("Violations: ");
        getConstraintViolations().forEach(violation ->
            message.append(violation.getPropertyPath())
                .append(" ")
                .append(violation.getMessage())
                .append(", ")
        );
        return message.toString();
    }
}
