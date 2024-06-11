package br.com.cadastro.simples.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.simples.exception.CustomConstraintViolationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class ValidationService {

    private final Validator validator;

    @Autowired
    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new CustomConstraintViolationException(violations, object);
        }
    }
}
