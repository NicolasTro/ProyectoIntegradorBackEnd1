package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;

import java.util.List;

public interface IService<T> {


	T guardar(T t);

	T buscarPorId(Integer id);

	void eliminar (Integer id);
	void actualizar(T t) throws OdontologoException;

	List<T> listarTodos();



}
