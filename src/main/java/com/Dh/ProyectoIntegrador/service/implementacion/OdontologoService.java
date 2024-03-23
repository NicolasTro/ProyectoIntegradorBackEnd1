package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.entity.Odontologo;

import com.Dh.ProyectoIntegrador.repository.IOdontologoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IService<Odontologo>, IServiceHQL<Odontologo> {

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

    // IService

	public Odontologo guardar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return odontologoRepository.save(odontologo);
	}

	public void eliminar(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.odontologoRepository.deleteById(id);
	}

	public void actualizar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.odontologoRepository.save(odontologo);
	}

	public Odontologo buscarPorId(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
		if(odontologoOptional.isPresent()) {
			return odontologoOptional.get();
		} else {
			return null;
		}
	}
//	public Odontologo buscarPorId(Integer tipoDeBusqueda, String valor) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
//
//
//
//
//
//		if(odontologoOptional.isPresent()) {
//			return odontologoOptional.get();
//		} else {
//			return null;
//		}
//	}

	public List<Odontologo> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return odontologoRepository.findAll();
	}

}