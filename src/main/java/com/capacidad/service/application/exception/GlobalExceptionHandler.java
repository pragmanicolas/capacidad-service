package com.capacidad.service.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCapacidadDataException.class)
    public ResponseEntity<String> handleInvalidCapacidadDataException(InvalidCapacidadDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CapacidadAlreadyExistsException.class)
    public ResponseEntity<String> handleCapacidadAlreadyExistsException(CapacidadAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CapacidadNotFoundException.class)
    public ResponseEntity<String> handleCapacidadNotFoundException(CapacidadNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Ha ocurrido un error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
