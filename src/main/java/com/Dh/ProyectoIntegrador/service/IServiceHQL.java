package com.Dh.ProyectoIntegrador.service;

import java.util.List;
import java.util.Optional;


public interface IServiceHQL<T> {

    // Este service es para el Endpoint de busqueda personalizada
    Optional<List<T>> buscarDatosCompletos(Integer  tipoDeBusqueda, String valor);




}
