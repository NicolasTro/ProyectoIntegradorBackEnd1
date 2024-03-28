package com.Dh.ProyectoIntegrador.service.implementacion;


import com.Dh.ProyectoIntegrador.dto.turnos.TurnoDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.request.TurnoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.response.TurnoResponseDTO;
import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.repository.ITurnoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
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
public class TurnoService implements IService<TurnoDTO>, IServiceHQL<TurnoDTO> {
	private ITurnoRepository turnoRepository;

	@Autowired
	public TurnoService(ITurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
	}


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


	public Optional<List<TurnoDTO>> listarTodosIDNombre() {
		return Optional.empty();
	}

	@Override
	public TurnoDTO guardar(TurnoDTO turnoRequestDTO) {
		Turno turno = this.turnoRepository.save(mapeadorRequest((TurnoRequestDTO) turnoRequestDTO));

		return mapeadorResponse(turno);
	}


	public TurnoDTO buscarPorId(Long id) {
		Optional<Turno> turnoOptional = turnoRepository.findById(id);
		if (turnoOptional.isPresent()) {
			return mapeadorResponse(turnoOptional.get());
		}
		return null;
	}

	public void eliminar(Long id) {
		this.turnoRepository.deleteById(id);
	}


	@Override
	public void actualizar(TurnoDTO turnoRequestDTO) {








		this.turnoRepository.save(mapeadorRequest(turnoRequestDTO));
	}


	public List<TurnoDTO> listarTodos() {

		return mapearRegistros(this.turnoRepository.findAll());
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
