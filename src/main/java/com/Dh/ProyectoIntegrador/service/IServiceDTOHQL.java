package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.dto.response.OdontologoDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceDTOHQL<T> {


	Optional<List<T>> listarTodosIDNombre();


}
