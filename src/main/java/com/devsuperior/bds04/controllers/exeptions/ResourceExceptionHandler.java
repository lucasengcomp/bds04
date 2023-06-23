package com.devsuperior.bds04.controllers.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();
        patternMessage(error, status, e.getMessage(), request);

        fieldWithErrors(e, error);

        return ResponseEntity.status(status).body(error);
    }

    private static void fieldWithErrors(MethodArgumentNotValidException e, ValidationError error) {
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    private void patternMessage(StandardError error, HttpStatus status, String e, HttpServletRequest request) {
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(Utils.ERROR_UNPROCESABLE_ENTITY);
        error.setMessage(e);
        error.setPath(request.getRequestURI());
    }
}
