package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDaoMemoria implements IDao<Turno> {
	private List<Turno> listaTurnos = new ArrayList<>();
	private static Integer contador =0;

	@Override
	public Turno guardar(Turno turno) throws OdontologoException, DomicilioException {
		if (turno != null) {
			contador+=1;
			turno.setId(contador);
			listaTurnos.add(turno);
		}
		return turno;
	}

	@Override
	public Turno buscarPorId(Integer id) throws OdontologoException, DomicilioException {
		for (int i = 0; i < listaTurnos.size(); i++) {
			if(listaTurnos.get(i).getOdontologo().getId().equals(id)){


			}
			
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) throws OdontologoException, DomicilioException {

	}

	@Override
	public void actualizar(Turno turno) throws OdontologoException, DomicilioException {

	}

	@Override
	public List<Turno> listarTodos() throws OdontologoException, DomicilioException {
		return null;
	}
}
