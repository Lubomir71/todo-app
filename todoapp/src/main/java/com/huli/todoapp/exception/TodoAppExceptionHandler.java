package com.huli.todoapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TodoAppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException (NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
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
//        String message = HttpStatus.NOT_FOUND.value()==404 ? "Page not found!" : ex.getMessage();
//        message = HttpStatus.UNAUTHORIZED.value()==401 ? "Unauthorized!" : ex.getMessage();
        return new ResponseEntity<>(new ErrorDTO(message, HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

}
