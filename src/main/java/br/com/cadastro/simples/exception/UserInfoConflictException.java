package br.com.cadastro.simples.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // HTTP 409
public class UserInfoConflictException extends RuntimeException {
    public UserInfoConflictException(String message) {
        super(message);
    }
}
