package com.Dh.ProyectoIntegrador.dao;

import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    T buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(T t) throws OdontologoException;
    List<T> listarTodos();

}
