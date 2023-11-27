package com.reckfrost.omspractise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        String errorCode = "";
        errorCode = switch (exception.getResourceName()) {
            case "Product" -> "PRODUCT_NOT_FOUND";
            case "Location" -> "LOCATION_NOT_FOUND";
            case "Inventory" -> "INVENTORY_NOT_FOUND";
            default -> "NOT_FOUND";
        };

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                errorCode
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handlerResourceAlreadyExistsException(ResourceAlreadyExistsException exception, WebRequest webRequest){

        String errorCode = "";
        errorCode = switch (exception.getResourceName()) {
            case "Product" -> "PRODUCT_REF_ALREADY_EXISTS";
            case "Location" -> "LOCATION_REF_ALREADY_EXISTS";
            case "Inventory" -> "INVENTORY_REF_ALREADY_EXISTS";
            default -> "ENTITY_ALREADY_EXISTS";
        };

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                errorCode
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
