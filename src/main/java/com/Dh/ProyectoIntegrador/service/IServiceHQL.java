package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.entity.Odontologo;

import java.util.List;
import java.util.Optional;

//@Service
public interface IServiceHQL<T> {

    Optional<List<T>> buscar(Integer  tipoDeBusqueda, String valor);
}
