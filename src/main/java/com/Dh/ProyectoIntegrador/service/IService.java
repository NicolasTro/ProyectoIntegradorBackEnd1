package com.Dh.ProyectoIntegrador.service;


import java.util.List;

public interface IService<T> {

	T guardar(T t);
	T buscarPorId(Integer id);
	void eliminar (Integer id);
	void actualizar(T t);
	List<T> listarTodos();



}
