package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDomicilioDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOFull;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOName;
import com.Dh.ProyectoIntegrador.entity.Domicilio;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.repository.IPacienteRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTO;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PacienteService implements IService<PacienteDomicilioDTO>, IServiceHQL<PacienteDomicilioDTO>, IServiceDTO<PacienteResponseDTOName> {


	private IPacienteRepository pacienteRepository;

	@Autowired
	public PacienteService(IPacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	public PacienteDomicilioDTO guardar(PacienteDomicilioDTO pacienteRequestDTO) {

		Paciente pacienteGuardado = pacienteRepository.save(mapearPacienteEntidad(pacienteRequestDTO));
		return mapeadorResponse(pacienteGuardado);


	}

	public PacienteDomicilioDTO buscarPorId(Long id) {
		Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
		if (pacienteOptional.isPresent()) {
			return this.mapeadorResponse(pacienteOptional.get());
		}
		return null;
	}




	private Paciente mapearPacienteEntidad(PacienteDomicilioDTO pacienteDomicilioDTO){
		Paciente pacienteAGuardarEntity = mapeador(pacienteDomicilioDTO, Paciente.class);
		Domicilio domicilioPaciente = new Domicilio();
		domicilioPaciente.setCalle(pacienteDomicilioDTO.getCalle());
		domicilioPaciente.setLocalidad(pacienteDomicilioDTO.getLocalidad());
		domicilioPaciente.setNumero(pacienteDomicilioDTO.getNumero());
		domicilioPaciente.setProvincia(pacienteDomicilioDTO.getProvincia());
		pacienteAGuardarEntity.setDomicilio(domicilioPaciente);
		return pacienteAGuardarEntity;


	}
	public void eliminar(Long id) {
		this.pacienteRepository.deleteById(id);
	}

	@Override
	public void actualizar(PacienteDomicilioDTO pacienteRequestDTO) {











		this.pacienteRepository.save(mapearPacienteEntidad(pacienteRequestDTO));
	}
@Override
	public List<PacienteDomicilioDTO> listarTodos() {

		return mapearRegistros(this.pacienteRepository.findAll());
	}

	@Override
	public Optional<List<PacienteDomicilioDTO>> buscarDatosCompletos(Integer tipoDeBusqueda, String valor) {

		Optional<List<PacienteDomicilioDTO>> pacienteOptional = null;
		switch (tipoDeBusqueda) {
			case 1:
				Long id = Long.parseLong(valor);
				Optional<Paciente> pacienteEncontrado = this.pacienteRepository.findById(id);
//				if (pacienteEncontrado.isPresent()) {
				List<PacienteDomicilioDTO> listaPaciente = new ArrayList<>();
				listaPaciente.add(mapeador(pacienteEncontrado.get(), PacienteDomicilioDTO.class));
				pacienteOptional = Optional.of(listaPaciente); // Envuelve la lista en un Optional
//				}
				break;
			case 2:
				pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByNombre(valor).get()));
				break;
			case 3:
				pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByApellido(valor).get()));
				break;
			case 4:
				Date fechaValor = Date.valueOf(valor);



				pacienteOptional = Optional.of(mapearRegistros(pacienteRepository.findByFecha(fechaValor).get()));
				break;
			default:
				break;
		}
		return pacienteOptional;
	}

	@Override
	public Optional<List<PacienteResponseDTOName>> listarTodosIDNombre() {
		List<Paciente> listaPacientes = this.pacienteRepository.findAll();
		List<PacienteResponseDTOName> listaPacientesDTO = new ArrayList<>();
		listaPacientes.forEach(paciente -> {
			listaPacientesDTO.add(mapeador(paciente, PacienteResponseDTOName.class));
		});
		return Optional.of(listaPacientesDTO);
	}

	private List<PacienteDomicilioDTO> mapearRegistros(List<Paciente> listaPacientes) {
		if (!listaPacientes.isEmpty()) {
			List<PacienteDomicilioDTO> listaPacientesDTO = new ArrayList<>();
			listaPacientes.forEach(paciente -> {
				listaPacientesDTO.add(mapeadorResponse(paciente));
			});
			return listaPacientesDTO;
		}
		return null;
	}

	private static <T> T mapeador(Object objetoAMapear, Class<T> clase) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		return mapper.convertValue(objetoAMapear, clase);
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