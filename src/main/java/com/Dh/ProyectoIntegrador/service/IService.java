package com.Dh.ProyectoIntegrador.service;

import java.util.List;
import java.util.Optional;

//@Service
public interface IService<T> {

	T guardar(T t);

	T buscarPorId(Long id);

	void eliminar(Long id);

	void actualizar(T t);

	List<T> listarTodos();

//	Optional<List<T>> buscar(Integer  tipoDeBusqueda, String valor);
//
//	Optional<List<T>> listarTodosIDNombre();


}
