package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;

import java.util.List;

public interface IService<T> {

	T guardar(T t) throws OdontologoException;
	T buscarPorId(Integer id) throws OdontologoException;
	void eliminar (Integer id) throws OdontologoException;



	void actualizar(T t) throws OdontologoException;


	List<T> listarTodos() throws OdontologoException;



}
