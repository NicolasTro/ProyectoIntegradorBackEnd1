package com.Dh.ProyectoIntegrador.service;


import java.util.List;
import java.util.Optional;

public interface IServiceDTO<T> {

	Optional<List<T>> listarTodosIDNombre();
}
