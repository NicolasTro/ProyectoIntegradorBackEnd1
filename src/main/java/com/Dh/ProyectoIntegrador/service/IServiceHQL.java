package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.entity.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public interface IServiceHQL<T> {

    Optional<T> buscar(Integer  tipoDeBusqueda, String valor);
}
