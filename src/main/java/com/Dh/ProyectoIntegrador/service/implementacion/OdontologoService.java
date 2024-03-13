package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.implementacion.OdontologoDaoH2;
import com.Dh.ProyectoIntegrador.model.Odontologo;

import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IService<Odontologo> {
	private IDao<Odontologo> iDao;

	@Autowired
	public OdontologoService(IDao<Odontologo> iDao) {
		this.iDao = iDao;
	}


	public Odontologo guardar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.guardar(odontologo);
	}

	public void eliminar(Integer id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.iDao.eliminar(id);
	}

	public void actualizar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.iDao.actualizar(odontologo);
	}

	public Odontologo buscarPorId(Integer id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return this.iDao.buscarPorId(id);
	}

	public List<Odontologo> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return iDao.listarTodos();
	}
}