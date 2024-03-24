package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;

import java.util.List;

//@Service
public interface IService<T> {

	T guardar(T t);

	T buscarPorId(Long id);

	void eliminar(Long id);

	void actualizar(T t);

	List<T> listarTodos();


}
