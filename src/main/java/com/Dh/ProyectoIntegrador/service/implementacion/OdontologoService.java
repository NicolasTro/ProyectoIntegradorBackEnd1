package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.implementacion.OdontologoDaoH2;
import com.Dh.ProyectoIntegrador.model.Odontologo;

import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService implements IService<Odontologo> {
	private IDao<Odontologo> iDao;
	public OdontologoService() {
		this.iDao = new OdontologoDaoH2();
	}
	public Odontologo guardar(Odontologo odontologo) throws OdontologoException, DomicilioException {
		return iDao.guardar(odontologo);
	}
	public void eliminar(Integer id) throws OdontologoException, DomicilioException { this.iDao.eliminar(id); }

	public void actualizar(Odontologo odontologo) throws OdontologoException, DomicilioException { this.iDao.actualizar(odontologo); }

	public Odontologo buscarPorId(Integer  id) throws OdontologoException, DomicilioException { return this.iDao.buscarPorId(id); }

	public List<Odontologo> listarTodos() throws OdontologoException, DomicilioException {
		return iDao.listarTodos();
	}
}