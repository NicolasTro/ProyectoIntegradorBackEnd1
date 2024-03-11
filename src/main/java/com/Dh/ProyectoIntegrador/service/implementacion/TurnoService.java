package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.dao.IDao;

import com.Dh.ProyectoIntegrador.dao.implementacion.TurnoDaoMemoria;
import com.Dh.ProyectoIntegrador.model.Turno;
import com.Dh.ProyectoIntegrador.service.IService;

import java.util.List;

public class TurnoService implements IService<Turno> {


	private IDao<Turno> iDao;

	public TurnoService() {
		this.iDao = new TurnoDaoMemoria();
	}

	public Turno guardar(Turno turno) throws OdontologoException, DomicilioException, PacienteException {
		return iDao.guardar(turno);
	}


	public Turno buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException {
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id) throws OdontologoException, DomicilioException, PacienteException {
		this.iDao.eliminar(id);
	}

	public void actualizar(Turno turno ) throws OdontologoException, DomicilioException, PacienteException {
		this.iDao.actualizar(turno);
	}


	public List<Turno> listarTodos() throws OdontologoException, DomicilioException, PacienteException {
		return iDao.listarTodos();
	}
}
