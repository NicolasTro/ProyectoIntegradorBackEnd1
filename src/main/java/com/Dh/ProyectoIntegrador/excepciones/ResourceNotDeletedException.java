package com.Dh.ProyectoIntegrador.excepciones;

public class ResourceNotDeletedException extends RuntimeException{
    public ResourceNotDeletedException(String message) {
        super(message);
    }
}
