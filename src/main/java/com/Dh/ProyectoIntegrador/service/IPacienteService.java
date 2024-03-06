package com.Dh.ProyectoIntegrador.service;

import com.Dh.ProyectoIntegrador.model.Paciente;

import java.util.List;

public interface IPacienteService {


	Paciente guardar(Paciente paciente);

	void eliminar(Integer id);

	void actualizar(Paciente paciente);

	Paciente buscarPorId(Integer id);

	List<Paciente> listarTodos();


}
