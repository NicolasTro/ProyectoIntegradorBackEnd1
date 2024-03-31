package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDomicilioDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOName;
import com.Dh.ProyectoIntegrador.entity.Domicilio;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.excepciones.*;
import com.Dh.ProyectoIntegrador.repository.IPacienteRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTO;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//##########################################################################################
// Implementación del servicio para operaciones relacionadas con los pacientes.
@Service
@Slf4j
public class PacienteService implements IService<PacienteDomicilioDTO>, IServiceHQL<PacienteDomicilioDTO>, IServiceDTO<PacienteResponseDTOName> {

	private IPacienteRepository pacienteRepository;

	@Autowired
	public PacienteService(IPacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	//##########################################################################################
	// Método para guardar un nuevo paciente con su domicilio
	public PacienteDomicilioDTO guardar(PacienteDomicilioDTO pacienteRequestDTO) {

		Paciente pacienteGuardado = pacienteRepository.save(mapearPacienteEntidad(pacienteRequestDTO));
		if (pacienteGuardado != null) {
			return mapeadorResponse(pacienteGuardado);
		} else {
			log.warn("Ha ocurrido un error al guardar Paciente");
			throw new ResourceNotSavedException("No se pudo guardar el paciente.");

		}
	}

	//##########################################################################################
	// Método para buscar un paciente por su ID
	public PacienteDomicilioDTO buscarPorId(Long id) {
		Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			return this.mapeadorResponse(pacienteOptional.get());
		} else {
			log.warn("Ha ocurrido un error buscando al Paciente con id:" + id);
			throw new ResourceNotFoundException("No se encuentra Paciente con el ID proporcionado: " + id);
		}
	}

	//##########################################################################################
	// Método para eliminar un paciente por su ID
	public void eliminar(Long id) {
		if (!pacienteRepository.existsById(id)) {
			throw new ResourceNotDeletedException("El Paciente no existe en la base de datos");
		} else {
			if (pacienteRepository.findByPacienteTurno(id) > 0) {
				log.warn("Ha ocurrido un error eliminando al Paciente con id:" + id);
				throw new IntegrityConstraintViolationException("No se puede eliminar el Paciente porque tiene un Turno agendado.");
			}
			this.pacienteRepository.deleteById(id);
		}
	}

	//##########################################################################################
	// Método para actualizar un paciente
	@Override
	public void actualizar(PacienteDomicilioDTO pacienteRequestDTO) {

		if (pacienteRequestDTO != null) {
			PacienteDomicilioDTO pacienteDomicilioDTO = this.buscarPorId(pacienteRequestDTO.getId());

			if (pacienteDomicilioDTO.getId() == pacienteDomicilioDTO.getId()) {
				this.pacienteRepository.save(mapearPacienteEntidad(pacienteRequestDTO));
			} else {
				log.warn("No se encuentra el paciente seleccionado");
				throw new ResourceNotFoundException("No se encuentra el paciente seleccionado" + pacienteRequestDTO.getId());
			}
		} else {
			log.warn("Ha ocurrido un error actualizando al Paciente.");
			throw new ResourceNotUpdatedException("No se pudo actualizar el paciente con el ID:" + pacienteRequestDTO.getId());
		}
	}

	//##########################################################################################
	// Método para listar todos los pacientes
	@Override
	public List<PacienteDomicilioDTO> listarTodos() {
		List<Paciente> pacientes = pacienteRepository.findAll();
		if (!pacientes.isEmpty()) {

			return mapearRegistros((pacientes));
		} else {
			log.warn("Ha ocurrido un error listando los Pacientes");
			throw new ResourceNotFoundException("No se pueden listar los pacientes.");
		}
	}

	//##########################################################################################
	// Método para buscar pacientes por parámetros personalizados
	@Override
	public Optional<List<PacienteDomicilioDTO>> buscarDatosCompletos(Integer tipoDeBusqueda, String valor) {
		if (valor.trim() != "") {


			Optional<List<PacienteDomicilioDTO>> pacienteOptional = null;
			switch (tipoDeBusqueda) {
				case 1:
					Long id = Long.parseLong(valor);
					Optional<Paciente> pacienteEncontrado = this.pacienteRepository.findById(id);
					if (pacienteEncontrado.isPresent()) {
						List<PacienteDomicilioDTO> listaPaciente = new ArrayList<>();
						listaPaciente.add(mapeador(pacienteEncontrado.get(), PacienteDomicilioDTO.class));
						pacienteOptional = Optional.of(listaPaciente); // Envuelve la lista en un Optional
					} else {
						pacienteOptional = Optional.empty();
					}
					break;
				case 2:
					pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByNombre(valor).get()));
					break;
				case 3:
					pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByApellido(valor).get()));
					break;
				case 4:
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate fechaHora = LocalDate.parse(valor, formatter);
					pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByFecha(fechaHora).get()));
					break;
				default:
					break;
			}
			if (pacienteOptional.isPresent() && !pacienteOptional.get().isEmpty()) {
				return pacienteOptional;
			} else {
				log.warn("Ha ocurrido un error en la busqueda personalizada de Pacientes");
				throw new ResourceNotFoundException("Error en la busqueda personalizada de Pacientes.");
			}

		} else {
			log.warn("Datos invalidos en la busqueda de paciente");
			throw new ResourceNotFoundException("Error en la busqueda personalizada de Pacientes.");

		}
	}

	//##########################################################################################
	// Método para listar todos los pacientes en formato DTO
	@Override
	public Optional<List<PacienteResponseDTOName>> listarTodosIDNombre() {
		List<Paciente> listaPacientes = this.pacienteRepository.findAll();
		if (listaPacientes == null || listaPacientes.isEmpty()) {
			log.warn("Ha ocurrido un error en listando PacientesDTO");
			throw new ResourceNotFoundException("Error en el metodo listarTodosIDNombre. No se pudo listar los Pacientes");
		}

		List<PacienteResponseDTOName> listaPacientesDTO = new ArrayList<>();
		listaPacientes.forEach(paciente -> {
			listaPacientesDTO.add(mapeador(paciente, PacienteResponseDTOName.class));
		});
		return Optional.of(listaPacientesDTO);
	}


	// Controladores/Mappers del Service.
	// ######################################################################################
	// Implementación de métodos privados de mapeo y controladores adicionales
	private List<PacienteDomicilioDTO> mapearRegistros(List<Paciente> listaPacientes) {
		if (!listaPacientes.isEmpty()) {
			List<PacienteDomicilioDTO> listaPacientesDTO = new ArrayList<>();
			listaPacientes.forEach(paciente -> {
				listaPacientesDTO.add(mapeadorResponse(paciente));
			});
			return listaPacientesDTO;
		}
		return Collections.emptyList();
	}

	private static <T> T mapeador(Object objetoAMapear, Class<T> clase) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		return mapper.convertValue(objetoAMapear, clase);
	}

	private Paciente mapearPacienteEntidad(PacienteDomicilioDTO pacienteDomicilioDTO) {
		Paciente pacienteAGuardarEntity = mapeador(pacienteDomicilioDTO, Paciente.class);
		Domicilio domicilioPaciente = new Domicilio();
		domicilioPaciente.setId(pacienteDomicilioDTO.getDomicilio_id());
		domicilioPaciente.setCalle(pacienteDomicilioDTO.getCalle());
		domicilioPaciente.setLocalidad(pacienteDomicilioDTO.getLocalidad());
		domicilioPaciente.setNumero(pacienteDomicilioDTO.getNumero());
		domicilioPaciente.setProvincia(pacienteDomicilioDTO.getProvincia());
		pacienteAGuardarEntity.setDomicilio(domicilioPaciente);
		return pacienteAGuardarEntity;


	}

	private static PacienteDomicilioDTO mapeadorResponse(Paciente pacienteAMapear) {

		PacienteDomicilioDTO pacienteResponseDTO = new PacienteDomicilioDTO();
		pacienteResponseDTO.setId(pacienteAMapear.getId());
		pacienteResponseDTO.setNombre(pacienteAMapear.getNombre());
		pacienteResponseDTO.setApellido(pacienteAMapear.getApellido());
		pacienteResponseDTO.setDni(pacienteAMapear.getDni());
		pacienteResponseDTO.setFechaIngreso(pacienteAMapear.getFechaIngreso());
		pacienteResponseDTO.setCalle(pacienteAMapear.getDomicilio().getCalle());
		pacienteResponseDTO.setNumero(pacienteAMapear.getDomicilio().getNumero());
		pacienteResponseDTO.setLocalidad(pacienteAMapear.getDomicilio().getLocalidad());
		pacienteResponseDTO.setProvincia(pacienteAMapear.getDomicilio().getProvincia());

		pacienteResponseDTO.setDomicilio_id(pacienteAMapear.getDomicilio().getId());

		return pacienteResponseDTO;
	}

}