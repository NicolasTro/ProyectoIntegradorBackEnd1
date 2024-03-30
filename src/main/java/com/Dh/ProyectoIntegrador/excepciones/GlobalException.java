package com.Dh.ProyectoIntegrador.excepciones;

import jakarta.persistence.EntityNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> recursoNoEncontrado(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotSavedException.class)
    public ResponseEntity<String> recursoNoGuardado(ResourceNotSavedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotUpdatedException.class)
    public ResponseEntity<String> recursoNoActualizado(ResourceNotUpdatedException e) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotDeletedException.class)
    public ResponseEntity<String> recursoNoEliminado(ResourceNotDeletedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entidadNoEncontrada(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<String> violacionDeIntegridadReferencial(IntegrityConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

}