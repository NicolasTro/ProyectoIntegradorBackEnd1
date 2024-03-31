package com.Dh.ProyectoIntegrador.excepciones;

public class ResourceNotSavedException extends RuntimeException{
    public ResourceNotSavedException(String message) {
        super(message);
    }
}
