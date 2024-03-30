package com.Dh.ProyectoIntegrador.service.implementacion;


import com.Dh.ProyectoIntegrador.dto.turnos.TurnoDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.request.TurnoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.response.TurnoResponseDTO;
import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.excepciones.OdontologoNotFoundException;
import com.Dh.ProyectoIntegrador.excepciones.TurnoNotFoundException;
import com.Dh.ProyectoIntegrador.excepciones.TurnoNotSavedException;
import com.Dh.ProyectoIntegrador.repository.ITurnoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTO;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TurnoService implements IService<TurnoDTO>, IServiceHQL<TurnoDTO>, IServiceDTO<TurnoResponseDTO> {
	private ITurnoRepository turnoRepository;

	@Autowired
	public TurnoService(ITurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
	}


	// Metodo de busqueda para el endpoint /turno/buscar
	// #################################################
	@Override
	public Optional<List<TurnoDTO>> buscarDatosCompletos(Integer tipoDeBusqueda, String valor) {
		Optional<List<TurnoDTO>> turnoOptional = null;
		switch (tipoDeBusqueda) {
			case 1:

				Long id = Long.parseLong(valor);
				Optional<Turno> turnoEncontrado = this.turnoRepository.findById(id);
				if (turnoEncontrado.isPresent()) {
					List<TurnoDTO> listaTurnosDTO = new ArrayList<>();
					listaTurnosDTO.add(mapeadorResponse(turnoEncontrado.get()));
					turnoOptional = Optional.of(listaTurnosDTO);
				}

				break;
			case 2:
				turnoOptional = Optional.of(mapearRegistros(turnoRepository.findByOdontologo(valor).get()));
				break;
			case 3:
				turnoOptional = Optional.of(mapearRegistros(turnoRepository.findByPacientes(valor).get()));
				break;
			case 4:
				String valorDatetimeLocal = valor;

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime fechaHora = LocalDateTime.parse(valorDatetimeLocal, formatter);


				turnoOptional = Optional.of(mapearRegistros(turnoRepository.findByFecha(fechaHora).get()));
				break;

			default:
				break;
		}
		return turnoOptional;
	}

	public Optional<List<TurnoResponseDTO>> listarTodosIDNombre() {
//		List<Turno> listaTurnos = this.turnoRepository.findAll();
//		List<TurnoResponseDTO> listaTurnosDTO = new ArrayList<>();
//		listaTurnos.forEach(turno -> {
//			listaTurnosDTO.add(mapeador(turno, TurnoResponseDTO.class ));
//		});

		return null;
	}

	@Override
	public TurnoDTO guardar(TurnoDTO turnoRequestDTO) {
		Turno turno = this.turnoRepository.save(mapeadorRequest((TurnoRequestDTO) turnoRequestDTO));
		if (turno != null) {
			return mapeadorResponse(turno);
		} else {
			throw new TurnoNotSavedException("No se pudo guardar el Turno." );
		}
	}

	public TurnoDTO buscarPorId(Long id) throws OdontologoNotFoundException {
		Optional<Turno> turnoOptional = turnoRepository.findById(id);
		if (turnoOptional.isPresent()) {
			return mapeadorResponse(turnoOptional.get());
		} else {
			throw new TurnoNotFoundException("No existe Turno con el ID especificado." + id);
		}
	}

	public void eliminar(Long id) {
		if (!turnoRepository.existsById(id)) {
			throw new TurnoNotFoundException("No se puede eliminar Turno" + id);
		}
		this.turnoRepository.deleteById(id);
	}


	@Override
	public void actualizar(TurnoDTO turnoRequestDTO) {

		if (turnoRequestDTO != null) {
			this.turnoRepository.save(mapeadorRequest(turnoRequestDTO));
		} else {
			throw  new TurnoNotSavedException("No se pudo actualizar el Turno con el ID" + turnoRequestDTO.getId());
		}
	}


	public List<TurnoDTO> listarTodos() {
		List<Turno> turnos = turnoRepository.findAll();
		if (!turnos.isEmpty()) {
			return mapearRegistros(turnos);
		}
		throw new TurnoNotFoundException("No se pueden listar los turnos.")
	}


	//	METODO MAPEAR REGISTROS
	//	########################################################################################
	private List<TurnoDTO> mapearRegistros(List<Turno> listaTurnos) {
		if (!listaTurnos.isEmpty()) {
			List<TurnoDTO> listaTurnosDTO = new ArrayList<>();
			listaTurnos.forEach(turno -> {
				listaTurnosDTO.add(mapeadorResponse(turno));
			});
			return listaTurnosDTO;
		}
		return null;
	}

	//	METODO MAPEADOR
	private static <T> T mapeador(Object objetoAMapear, Class<T> clase) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		return mapper.convertValue(objetoAMapear, clase);
	}
	//	#############################################################################
	private static TurnoDTO mapeadorResponse(Turno turnoAMapear) {

		TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
		turnoResponseDTO.setId(turnoAMapear.getId());
		turnoResponseDTO.setOdontologoNombre(turnoAMapear.getOdontologo().getNombre());
		turnoResponseDTO.setOdontologoApellido(turnoAMapear.getOdontologo().getApellido());
		turnoResponseDTO.setOdontologo_Id(turnoAMapear.getOdontologo().getId());
		turnoResponseDTO.setPacienteNombre(turnoAMapear.getPaciente().getNombre());
		turnoResponseDTO.setPacienteApellido(turnoAMapear.getPaciente().getApellido());
		turnoResponseDTO.setPaciente_Id(turnoAMapear.getPaciente().getId());
		turnoResponseDTO.setFechaYHora(turnoAMapear.getFechaYHora());
		return turnoResponseDTO;
	}

	//	MAPEADOR TURNO REQUEST DTO
	//###########################################################################################
	private static Turno mapeadorRequest(TurnoDTO turnoRequestDTO) {

		Turno turno = new Turno();
		Odontologo odontologo = new Odontologo();
		Paciente paciente = new Paciente();

		turno.setId(turnoRequestDTO.getId());
		odontologo.setId(turnoRequestDTO.getOdontologo_Id());
		turno.setOdontologo(odontologo);
		paciente.setId(turnoRequestDTO.getPaciente_Id());
		turno.setPaciente(paciente);
		turno.setFechaYHora(turnoRequestDTO.getFechaYHora());

		return turno;
	}


}
