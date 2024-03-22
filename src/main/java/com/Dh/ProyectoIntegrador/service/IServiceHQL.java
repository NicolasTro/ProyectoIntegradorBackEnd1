package com.Dh.ProyectoIntegrador.service;

import java.util.Optional;

//@Service
public interface IServiceHQL<T> {

    Optional<T> buscar(Integer  tipoDeBusqueda, String valor);
}
