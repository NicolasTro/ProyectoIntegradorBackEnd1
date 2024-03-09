package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
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

	public Domicilio guardar(Domicilio domicilio) {
		return iDao.guardar(domicilio);
	}

	public Domicilio buscarPorId(Integer id){
		return this.iDao.buscarPorId(id);
	}

	public void eliminar(Integer id){
		this.iDao.eliminar(id);
	}
	public void actualizar(Domicilio domicilio) throws OdontologoException {
		this.iDao.actualizar(domicilio);
	}

	public List<Domicilio> listarTodos() {
		return iDao.listarTodos();
	}
}
