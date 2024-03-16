package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;

import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.repository.ITurnoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IService<Turno> {
	private ITurnoRepository turnoRepository;
	@Autowired
	public TurnoService(ITurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
	}
//	public TurnoService() {
//		this.iDao = new TurnoDaoMemoria();
//	}

	public Turno guardar(Turno turno) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return turnoRepository.save(turno);
	}

	public Turno buscarPorId(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		Optional<Turno> turnoOptional = turnoRepository.findById(id);
		if (turnoOptional.isPresent()) {
			return turnoOptional.get();
		}
		return null;
	}

	public void eliminar(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.turnoRepository.deleteById(id);
	}

	public void actualizar(Turno turno) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.turnoRepository.save(turno);
	}


	public List<Turno> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return turnoRepository.findAll();
	}
}
