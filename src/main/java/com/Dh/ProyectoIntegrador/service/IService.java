package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;

import java.util.List;

public interface IService<T> {

	T guardar(T t) throws OdontologoException, DomicilioException;
	T buscarPorId(Integer id) throws OdontologoException, DomicilioException;
	void eliminar (Integer id) throws OdontologoException, DomicilioException;



	void actualizar(T t) throws OdontologoException, DomicilioException;


	List<T> listarTodos() throws OdontologoException, DomicilioException;



}
