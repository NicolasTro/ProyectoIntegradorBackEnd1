package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;

import java.util.List;

public interface IService<T> {

	T guardar(T t) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	T buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	void eliminar (Integer id) throws OdontologoException, DomicilioException, PacienteException;



	void actualizar(T t) throws OdontologoException, DomicilioException, PacienteException, TurnoException;


	List<T> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException;



}
