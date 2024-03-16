package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;

import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.repository.IPacienteRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente> {


	private IPacienteRepository pacienteRepository;

	@Autowired
	public PacienteService(IPacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	public Paciente guardar(Paciente paciente) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return pacienteRepository.save(paciente);
	}

	public Paciente buscarPorId(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			return pacienteOptional.get();
		}
		return null;
	}

	public void eliminar(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.pacienteRepository.deleteById(id);
	}

	public void actualizar(Paciente paciente) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.pacienteRepository.save(paciente);
	}

	public List<Paciente> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return pacienteRepository.findAll();
	}

}
