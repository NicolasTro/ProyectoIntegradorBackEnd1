package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dto.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.request.OdontologoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.response.OdontologoResponseDTO;
import com.Dh.ProyectoIntegrador.entity.Odontologo;

import com.Dh.ProyectoIntegrador.repository.IOdontologoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTOHQL;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OdontologoService implements IService<OdontologoDTO>, IServiceHQL<OdontologoDTO>, IServiceDTOHQL<OdontologoDTO> {

	private IOdontologoRepository odontologoRepository;

	@Autowired
	public OdontologoService(IOdontologoRepository odontologoRepository) {
		this.odontologoRepository = odontologoRepository;
	}


	public Optional<List<OdontologoDTO>> buscarDatosCompletos(Integer tipoDeBusqueda, String valor) {
		Optional<List<OdontologoDTO>> odontologoOptional = null;
		switch (tipoDeBusqueda) {

			case 1:
				Long id = Long.parseLong(valor);
				Optional<Odontologo> odontologoEncontrado = this.odontologoRepository.findById(id);
				if (odontologoEncontrado.isPresent()) {

					ObjectMapper mapper = new ObjectMapper();
					OdontologoDTO odontologoResponseDTO = mapper.convertValue(odontologoEncontrado.get(), OdontologoDTO.class);
					List<OdontologoDTO> listaOdontologo = new ArrayList<>();
					listaOdontologo.add(odontologoResponseDTO);

					odontologoOptional = Optional.of(listaOdontologo); // Envuelve la lista en un Optional
				}
				break;
			case 2:
				odontologoOptional = odontologoRepository.findByNombre(valor);
				break;
			case 3:
				odontologoOptional = odontologoRepository.findByApellido(valor);
				break;
			case 4:
				odontologoOptional = odontologoRepository.findByMatricula(valor);
				break;
			default:
				break;
		}
		return odontologoOptional;
	}

@Override
	public OdontologoResponseDTO guardar(OdontologoDTO odontologoRequestDTO) {
		ObjectMapper mapper = new ObjectMapper();
		Odontologo odontologo = mapper.convertValue(odontologoRequestDTO, Odontologo.class);
		log.info("odontologo creado", odontologo);
		Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
		return mapper.convertValue(odontologoGuardado, OdontologoResponseDTO.class);
	}

	public void eliminar(Long id) {
		this.odontologoRepository.deleteById(id);
	}
@Override
	public void actualizar(OdontologoDTO odontologoRequestDTO) {
		ObjectMapper mapper = new ObjectMapper();
		Odontologo odontologoAActualizar = mapper.convertValue(odontologoRequestDTO, Odontologo.class);


		this.odontologoRepository.save(odontologoAActualizar);
	}

	public OdontologoResponseDTO buscarPorId(Long id) {
		//ESTO INGRESA id DEVUELVE
		Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

		if (odontologoOptional.isPresent()) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(odontologoOptional.get(), OdontologoResponseDTO.class);
		} else {
			return null;
		}
	}

@Override
	public List<OdontologoDTO> listarTodos() {
		List<Odontologo> listaOdontologos = odontologoRepository.findAll();
		List<OdontologoDTO> listaDTOOdontologos = new ArrayList<>();
		listaOdontologos.forEach(odontologo -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoResponseDTO.class);
			listaDTOOdontologos.add(odontologoDTO);
		});
		return listaDTOOdontologos;
	}


	public Optional<List<OdontologoDTO>> listarTodosIDNombre() {

		List<Odontologo> listaOdontologos = this.odontologoRepository.findAll();
		List<OdontologoDTO> listaOdontologosDTO = new ArrayList<>();


		listaOdontologos.forEach(odontologo -> {

					ObjectMapper mapper = new ObjectMapper();
					mapper.registerModule(new JavaTimeModule());
					OdontologoResponseDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoResponseDTO.class);
					listaOdontologosDTO.add(odontologoDTO);
				}


		);


		return Optional.of(listaOdontologosDTO);
	}


}