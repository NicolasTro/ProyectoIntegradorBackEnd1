package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface IService<T> {

	T guardar(T t) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	T buscarPorId(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	void eliminar (Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	void actualizar(T t) throws OdontologoException, DomicilioException, PacienteException, TurnoException;
	List<T> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException;



}
