package com.Dh.ProyectoIntegrador.service;


import java.util.List;
import java.util.Optional;

public interface IServiceDTO<T> {

	// Service para listar los DTO de las entidades.
	Optional<List<T>> listarTodosIDNombre();
}
