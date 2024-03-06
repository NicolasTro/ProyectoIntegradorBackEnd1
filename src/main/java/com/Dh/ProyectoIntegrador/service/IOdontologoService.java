package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.model.Odontologo;

import java.util.List;

public interface IOdontologoService {


	Odontologo guardar(Odontologo odontologo);

	void eliminar(Integer id);

	void actualizar(Odontologo odontologo);

	Odontologo buscarPorID(Integer id);

	List<Odontologo> listarTodos();


}
