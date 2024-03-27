package com.Dh.ProyectoIntegrador.service;

import java.util.List;
import java.util.Optional;


public interface IServiceHQL<T> {

    Optional<List<T>> buscarDatosCompletos(Integer  tipoDeBusqueda, String valor);

    Optional<List<T>> listarTodosIDNombre();


}
