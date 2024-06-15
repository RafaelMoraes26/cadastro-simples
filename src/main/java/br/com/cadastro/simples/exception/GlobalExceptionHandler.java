package br.com.cadastro.simples.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.cadastro.simples.dto.error.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserInfoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserInfoNotFoundException(UserInfoNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUsernameException(DuplicateUsernameException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(), // HTTP 409
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserPostNotFoundException.class)
    public ResponseEntity<Object> handleUserPostNotFoundException(UserPostNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserInfoConflictException.class)
    public ResponseEntity<Object> handleUserInfoConflictException(UserInfoConflictException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
}
