package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.request.OdontologoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.response.OdontologoResponseDTOFull;
import com.Dh.ProyectoIntegrador.dto.odontologos.response.OdontologoResponseDTOName;
import com.Dh.ProyectoIntegrador.entity.Odontologo;

import com.Dh.ProyectoIntegrador.excepciones.*;
import com.Dh.ProyectoIntegrador.repository.IOdontologoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTO;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OdontologoService implements IService<OdontologoDTO>, IServiceHQL<OdontologoDTO>, IServiceDTO<OdontologoDTO> {

	private IOdontologoRepository odontologoRepository;

	@Autowired
	public OdontologoService(IOdontologoRepository odontologoRepository) {
		this.odontologoRepository = odontologoRepository;
	}

	//METODOS CRUD

	//METODO GUARDAR
	/*###################################################################################################################*/
	@Override
	public OdontologoDTO guardar(OdontologoDTO odontologoRequestDTO) {
		Odontologo odontologoGuardado = odontologoRepository.save(mapeador(odontologoRequestDTO, Odontologo.class));
		if (odontologoGuardado != null) {
			return mapeador(odontologoGuardado, OdontologoResponseDTOFull.class);
		} else {
			log.warn("Ha ocurrido un error guardando Odontologo");
			throw new ResourceNotSavedException("No se pudo guardar el Odontologo");
		}
	}

	//METODO ELIMINAR
	/*###################################################################################################################*/
	public void eliminar(Long id) {
		if (!odontologoRepository.existsById(id)) {

			log.warn("Ha ocurrido un error eliminando el Odontologo con ID: " + id);
			throw new ResourceNotDeletedException("El Odontologo no existe en la Base de Datos.");
		} else {
			if (odontologoRepository.findByOdontologoTurno(id) > 0) {
				log.warn("El Odontologo no se puede eliminar porque tiene un Turno. ID del turno: " + id);
				throw new IntegrityConstraintViolationException("No se puede eliminar Odontologo porque tiene un Turno Agendado.");
			}
		}
		this.odontologoRepository.deleteById(id);
	}

	//METODO ACTUALIZAR
	/*###################################################################################################################*/
	@Override
	public void actualizar(OdontologoDTO odontologoRequestDTO) {
		if (odontologoRequestDTO != null) {

			OdontologoRequestDTO odontologoABuscar = (OdontologoRequestDTO) odontologoRequestDTO;

			OdontologoResponseDTOFull odontologBuscado = (OdontologoResponseDTOFull) this.buscarPorId(odontologoABuscar.getId());

			if (odontologBuscado.getId()==odontologoABuscar.getId()) {

				this.odontologoRepository.save(mapeador(odontologoRequestDTO, Odontologo.class));
			} else {
				log.warn("No se encuentra el registro para actualizar");
				throw new ResourceNotFoundException("No se pudo encontrar el Odontologo");
			}
		} else {
			log.warn("Ha ocurrido un error actualizando el Odontologo");
			throw new ResourceNotUpdatedException("No se pudo actualizar el Odontologo");
		}
	}
	//METODO BUSCAR POR ID
	/*###################################################################################################################*/

	public OdontologoDTO buscarPorId(Long id) {
		Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
		if (odontologoOptional.isPresent()) {
			return this.mapeador(odontologoOptional.get(), OdontologoResponseDTOFull.class);
		} else {
			log.warn("Ha ocurrido un error buscando el Odontologo con ID: " + id);
			throw new ResourceNotFoundException("No se encuentra Odontologo con el ID proporcionado: " + id);
		}
	}

	//METODO LISTAR
	/*###################################################################################################################*/
	@Override
	public List<OdontologoDTO> listarTodos() {
		List<Odontologo> odontologos = odontologoRepository.findAll();
		if (!odontologos.isEmpty()) {
			return mapearRegistros(odontologos);
		} else {
			log.warn("Ha ocurrido un error listando todos los Odontologos");
			throw new ResourceNotFoundException("No se pueden listar los Odontologos");
		}
	}
	/*###################################################################################################################*/

	public Optional<List<OdontologoDTO>> buscarDatosCompletos(Integer tipoDeBusqueda, String valor) {
		if(!valor.trim().equals("")){


		Optional<List<OdontologoDTO>> odontologoOptional = null;
		switch (tipoDeBusqueda) {

			case 1:
				Long id = Long.parseLong(valor);
				Optional<Odontologo> odontologoEncontrado = this.odontologoRepository.findById(id);
				if (odontologoEncontrado.isPresent()) {
					List<OdontologoDTO> listaOdontologo = new ArrayList<>();
					listaOdontologo.add(mapeador(odontologoEncontrado.get(), OdontologoResponseDTOFull.class));
					odontologoOptional = Optional.of(listaOdontologo); // Envuelve la lista en un Optional
				} else {
					odontologoOptional = Optional.empty();
				}
				break;
			case 2:
				odontologoOptional = Optional.of(mapearRegistros(odontologoRepository.findByNombre(valor).get()));
				break;
			case 3:
				odontologoOptional = Optional.of(mapearRegistros(odontologoRepository.findByApellido(valor).get()));
				break;
			case 4:
				odontologoOptional = Optional.of(mapearRegistros(odontologoRepository.findByMatricula(valor).get()));
				break;
			default:
				break;
		}


		if (odontologoOptional.isPresent()&& !odontologoOptional.get().isEmpty()) {
			return odontologoOptional;
		} else {
			log.warn("Ha ocurrido un error en la busqueda personalizada de los Odontologos");
			throw new ResourceNotFoundException("Error en la busqueda de Odontologos.");
		}

		}else{
			log.warn("Ha ocurrido un error en la busqueda personalizada de los Odontologos");
			throw new ResourceNotFoundException("Error en la busqueda de Odontologos.");


		}
	}

	public Optional<List<OdontologoDTO>> listarTodosIDNombre() {
		List<Odontologo> listaOdontologos = this.odontologoRepository.findAll();
		if (listaOdontologos == null || listaOdontologos.isEmpty()) {
			log.warn("Ha ocurrido un error listando los OdontologosDTO");
			throw new ResourceNotFoundException("Error en el metodo listarTodosIDNombre. No se pudo listar los Odontologos.");
		}
		List<OdontologoDTO> listaOdontologosDTO = new ArrayList<>();
		listaOdontologos.forEach(odontologo -> {
			listaOdontologosDTO.add(mapeador(odontologo, OdontologoResponseDTOName.class));
		});
		return Optional.of(listaOdontologosDTO);
	}


	private List<OdontologoDTO> mapearRegistros(List<Odontologo> listaOdontologos) {
		if (!listaOdontologos.isEmpty()) {
			List<OdontologoDTO> listaOdontologosDTO = new ArrayList<>();
			listaOdontologos.forEach(odontologo -> {
				listaOdontologosDTO.add(mapeador(odontologo, OdontologoResponseDTOFull.class));
			});
			return listaOdontologosDTO;
		}
		return Collections.emptyList();
	}

	private static <T> T mapeador(Object objetoAMapear, Class<T> clase) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.convertValue(objetoAMapear, clase);
	}

}