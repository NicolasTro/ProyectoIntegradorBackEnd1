package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.implementacion.TurnoDaoH2;
import com.Dh.ProyectoIntegrador.model.Turno;

import java.util.List;

public class TurnoService {


	private IDao<Turno> iDao;

	public TurnoService() {
		this.iDao = new TurnoDaoH2();
	}

	public Turno guardar(Turno turno) {
		return iDao.guardar(turno);
	}


	public Turno buscarPorId(Integer id){
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id){
		this.iDao.eliminar(id);
	}

	public void actualizar(Turno turno ){
		this.iDao.actualizar(turno);
	}


	public List<Turno> listarTodos() {
		return iDao.listarTodos();
	}
}
