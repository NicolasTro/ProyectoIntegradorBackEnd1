package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.model.Turno;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component

public class TurnoDaoMemoria implements IDao<Turno> {
	private static final Logger LOGGER = Logger.getLogger(TurnoDaoMemoria.class);
	private List<Turno> listaTurnos = null;
	private static Integer contador = 0;

	@Autowired
	public TurnoDaoMemoria() {
		this.listaTurnos = listaTurnos;
	}



	@Override
	public Turno guardar(Turno turno) throws OdontologoException, DomicilioException, TurnoException, PacienteException {
		if (turno != null) {
			contador += 1;
			turno.setId(contador);
			OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
			PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
			turno.setOdontologo(odontologoDaoH2.buscarPorId(turno.getOdontologo().getId()));
			turno.setPaciente(pacienteDaoH2.buscarPorId(turno.getPaciente().getId()));
			listaTurnos.add(turno);
			return turno;
		} else {
			throw new TurnoException("No se pudo registrar el turno");
		}
	}

	@Override
	public Turno buscarPorId(Integer id) throws OdontologoException, DomicilioException, TurnoException {
		Turno turnoEncontrado = null;
		if (id > 0) {
			for (int i = 0; i < listaTurnos.size(); i++) {
				if (listaTurnos.get(i).getOdontologo().getId().equals(id)) {
					turnoEncontrado = listaTurnos.get(i);
				}
			}
		} else {
			throw new TurnoException("Turno no encontrado");
		}
		return turnoEncontrado;
	}


	//TODO DEVOLVER ALGO SI ESTA CORRECTO?
	@Override
	public void eliminar(Integer id) throws OdontologoException, DomicilioException, TurnoException {
		if (id > 0) {
			Turno turnoAEliminar = null;
			turnoAEliminar = this.buscarPorId(id);
			if (turnoAEliminar != null) {
				if (this.listaTurnos.remove(turnoAEliminar)) {

					LOGGER.info("Turno eliminado: " + turnoAEliminar);
				} else {
					LOGGER.info("Turno NO eliminado: " + turnoAEliminar);
				}
			}
		}
	}

	@Override
	public void actualizar(Turno nuevoTurno) throws OdontologoException, DomicilioException, TurnoException {
		if (nuevoTurno != null) {
			Turno turnoAModificar = this.buscarPorId(nuevoTurno.getId());
			turnoAModificar.setOdontologo(nuevoTurno.getOdontologo());
			turnoAModificar.setPaciente(nuevoTurno.getPaciente());
			turnoAModificar.setFechaYHora(nuevoTurno.getFechaYHora());
			LOGGER.info("Turno actualizado correctamente");
		} else {
			throw new TurnoException("No se ingreso turno para actualizar");
		}
	}

	@Override
	public List<Turno> listarTodos() throws OdontologoException, DomicilioException, TurnoException {
		if (!this.listaTurnos.isEmpty()) {
			return this.listaTurnos;
		} else {
			throw new TurnoException("No se encontraron turnos registrados");
		}
	}
}
