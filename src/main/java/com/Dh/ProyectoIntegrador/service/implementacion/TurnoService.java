package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dao.IDao;

import com.Dh.ProyectoIntegrador.dao.implementacion.TurnoDaoMemoria;
import com.Dh.ProyectoIntegrador.model.Turno;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements IService<Turno> {


	private IDao<Turno> iDao;

	public TurnoService() {
		this.iDao = new TurnoDaoMemoria();
	}

	public Turno guardar(Turno turno) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.guardar(turno);
	}


	public Turno buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id) throws OdontologoException, DomicilioException, PacienteException {
		this.iDao.eliminar(id);
	}

	public void actualizar(Turno turno ) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.iDao.actualizar(turno);
	}


	public List<Turno> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.listarTodos();
	}
}
