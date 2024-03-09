package com.Dh.ProyectoIntegrador.service;


import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;

import java.util.List;

public interface IService<T> {

	T guardar(T t);
	T buscarPorId(Integer id);
	void eliminar (Integer id);
<<<<<<< HEAD
	void actualizar(T t);
=======
	void actualizar(T t) throws OdontologoException;

>>>>>>> e8b143eda05d742e63b28e0d5f3638cbbe3703f4
	List<T> listarTodos();



}
