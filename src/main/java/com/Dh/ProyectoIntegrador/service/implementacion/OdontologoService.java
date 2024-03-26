package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.dto.response.OdontologoDTO;
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
public class OdontologoService implements IService<Odontologo>, IServiceHQL<Odontologo>, IServiceDTOHQL<OdontologoDTO> {

	private IOdontologoRepository odontologoRepository;

	@Autowired
	public OdontologoService( IOdontologoRepository odontologoRepository) {
		this.odontologoRepository = odontologoRepository;
	}
	//  IServiceHQL
	@Override
	public Optional<List<Odontologo>> buscar(Integer tipoDeBusqueda, String valor) {
		Optional<List<Odontologo>> odontologoOptional = null;
		switch (tipoDeBusqueda){

			case 1:
				Long id = Long.parseLong(valor);
				Optional<Odontologo> odontologoEncontrado = this.odontologoRepository.findById(id);
//				if (odontologoEncontrado.isPresent()) {
					List<Odontologo> listaOdontologo = new ArrayList<>();
					listaOdontologo.add(odontologoEncontrado.get());
					odontologoOptional = Optional.of(listaOdontologo); // Envuelve la lista en un Optional
//				}
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






	public Odontologo guardar(Odontologo odontologo)  {
		return odontologoRepository.save(odontologo);
	}

	public void eliminar(Long id)  {
		this.odontologoRepository.deleteById(id);
	}

	public void actualizar(Odontologo odontologo)  {
		this.odontologoRepository.save(odontologo);
	}

	public Odontologo buscarPorId(Long id)  {
		Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
		if(odontologoOptional.isPresent()) {
			return odontologoOptional.get();
		} else {
			return null;
		}
	}

	public List<Odontologo> listarTodos()  {
		return odontologoRepository.findAll();
	}

	@Override
	public Optional<List<OdontologoDTO>> listarTodosIDNombre() {

		List<Odontologo> listaOdontologos =this.odontologoRepository.findAll();
		List<OdontologoDTO> listaOdontologosDTO = new ArrayList<>();


		listaOdontologos.forEach(odontologo ->{



				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
		listaOdontologosDTO.add(odontologoDTO);
				}


		);



		return Optional.of(listaOdontologosDTO);
	}






}