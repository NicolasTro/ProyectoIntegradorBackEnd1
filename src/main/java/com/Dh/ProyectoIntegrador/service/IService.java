package com.Dh.ProyectoIntegrador.service;

import java.util.List;

public interface IService<T> {

	T guardar(T t);

	T buscarPorId(Long id);

	void eliminar(Long id);

	void actualizar(T t);

	List<T> listarTodos();


}
