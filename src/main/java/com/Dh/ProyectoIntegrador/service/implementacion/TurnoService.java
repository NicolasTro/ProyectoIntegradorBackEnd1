package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;


import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.repository.ITurnoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements IService<Turno>, IServiceHQL<Turno> {
	private ITurnoRepository turnoRepository;
	@Autowired
	public TurnoService(ITurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
	}

	// IServiceHQL
@Override
public Optional<List<Turno>> buscar(Integer tipoDeBusqueda, String valor) {
	Optional<List<Turno>> turnoOptional = null;
	switch (tipoDeBusqueda){
		case 1:
			turnoOptional = turnoRepository.findByPacientes(valor);
			break;
		case 2:
			turnoOptional = turnoRepository.findByOdontologo(valor);
			break;
		case 3:
			turnoOptional = turnoRepository.findByFecha(valor);
			break;
	}
	return turnoOptional;
}


	public Turno guardar(Turno turno)  {
		return turnoRepository.save(turno);
	}

	public Turno buscarPorId(Long id)  {
		Optional<Turno> turnoOptional = turnoRepository.findById(id);
		if (turnoOptional.isPresent()) {
			return turnoOptional.get();
		}
		return null;
	}

	public void eliminar(Long id)  {
		this.turnoRepository.deleteById(id);
	}

	public void actualizar(Turno turno) {
		this.turnoRepository.save(turno);
	}


	public List<Turno> listarTodos() {
		return turnoRepository.findAll();
	}
}
