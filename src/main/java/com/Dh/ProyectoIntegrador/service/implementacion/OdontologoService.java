package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.implementacion.OdontologoDaoH2;
import com.Dh.ProyectoIntegrador.model.Odontologo;
import com.Dh.ProyectoIntegrador.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService{
	private IDao<Odontologo> iDao;
	public OdontologoService(

	) {
		this.iDao = new OdontologoDaoH2();
	}
	public Odontologo guardar(Odontologo odontologo) {
		return iDao.guardar(odontologo);
	}
	public void eliminar(Integer id) { this.iDao.eliminar(id); }

	public void actualizar(Odontologo odontologo)  { this.iDao.actualizar(odontologo); }

	public Odontologo buscarPorID(Integer  id) { return this.iDao.buscarPorId(id); }

	public List<Odontologo> listarTodos() {
		return iDao.listarTodos();
	}
}