package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TurnoDaoMemoria implements IDao<Turno> {
	private static final Logger LOGGER = Logger.getLogger(TurnoDaoMemoria.class);
	private List<Turno> listaTurnos = null;
	private static Integer contador = 0;

	public TurnoDaoMemoria() {
		this.listaTurnos = new ArrayList<>();
		;
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

	@Override
	public void eliminar(Integer id) throws OdontologoException, DomicilioException {
		if (id > 0) {
			Turno turnoEliminado = null;
			for (int i = 0; i < listaTurnos.size(); i++) {
				if (listaTurnos.get(i).getId().equals(id)) {
					turnoEliminado = listaTurnos.remove(i);
				}
				if (turnoEliminado != null) {
					LOGGER.info("Turno eliminado: " + turnoEliminado);
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
