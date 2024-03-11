package com.Dh.ProyectoIntegrador.dao;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;

import java.util.List;

public interface IDao<T> {
    T guardar(T t) throws OdontologoException, DomicilioException, PacienteException;
    T buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException;
    void eliminar(Integer id) throws OdontologoException, DomicilioException, PacienteException;
    void actualizar(T t) throws OdontologoException, DomicilioException, PacienteException;
    List<T> listarTodos() throws OdontologoException, DomicilioException, PacienteException;

}
