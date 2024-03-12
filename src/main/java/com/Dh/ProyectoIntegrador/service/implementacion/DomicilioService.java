package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.implementacion.DomicilioDaoH2;

import com.Dh.ProyectoIntegrador.model.Domicilio;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DomicilioService implements IService<Domicilio> {
	private IDao<Domicilio> iDao;

	public DomicilioService() {
		this.iDao = new DomicilioDaoH2();
	}

	public Domicilio guardar(Domicilio domicilio) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.guardar(domicilio);
	}

	public Domicilio buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id) throws OdontologoException, DomicilioException, PacienteException {
		this.iDao.eliminar(id);
	}
	public void actualizar(Domicilio domicilio) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.iDao.actualizar(domicilio);
	}

	public List<Domicilio> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.listarTodos();
	}
}
