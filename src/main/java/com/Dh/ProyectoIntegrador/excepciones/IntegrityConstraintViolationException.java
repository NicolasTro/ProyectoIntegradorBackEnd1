package com.Dh.ProyectoIntegrador.excepciones;

public class IntegrityConstraintViolationException extends RuntimeException{
    public IntegrityConstraintViolationException(String message) {
        super(message);
    }

}
