package com.Dh.ProyectoIntegrador.excepciones;

public class PacienteNotSavedException extends RuntimeException{
    public PacienteNotSavedException(String message) {
        super(message);
    }
}
