package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.model.Domicilio;

import java.util.List;

public interface IDomicilioService {



	Domicilio guardar(Domicilio domicilio);

	Domicilio buscarPorId(Integer id);

	void eliminar(Integer id);
	void actualizar(Domicilio domicilio);

	List<Domicilio> listarTodos();


}
