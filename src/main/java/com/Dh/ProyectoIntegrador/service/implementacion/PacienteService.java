package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dao.IDao;

import com.Dh.ProyectoIntegrador.dao.implementacion.PacienteDaoH2;

import com.Dh.ProyectoIntegrador.model.Paciente;
import com.Dh.ProyectoIntegrador.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

	private IDao<Paciente> iDao;

	public PacienteService() {
		this.iDao = new PacienteDaoH2();
	}

	public Paciente guardar(Paciente paciente) {
		return iDao.guardar(paciente);
	}

	public Paciente buscarPorId(Integer id){
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id){
		this.iDao.eliminar(id);
	}

	public void actualizar(Paciente paciente){
		this.iDao.actualizar(paciente);
	}

	public List<Paciente> listarTodos() {
		return iDao.listarTodos();
	}

}
