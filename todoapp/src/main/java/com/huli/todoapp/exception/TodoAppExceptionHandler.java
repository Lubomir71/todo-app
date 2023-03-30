package com.huli.todoapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TodoAppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException (NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        String message;
        switch (status.value()) {
            case 401:
                message="Unauthorized!";
                break;
            case 404:
                message="Page not found!";
                break;
            default:
                message=ex.getMessage();
        }
        return new ResponseEntity<>(new ErrorDTO(message, HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new ErrorDTO(error.getField() + ": " + error.getDefaultMessage(),
                    HttpStatus.BAD_REQUEST.value()));
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorDTO> invalidArgumentHandler(IllegalArgumentException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }






}
