package com.Dh.ProyectoIntegrador.service.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.entity.Odontologo;

import com.Dh.ProyectoIntegrador.repository.IOdontologoRepository;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IService<Odontologo> {

	private IOdontologoRepository odontologoRepository;

	@Autowired
	public OdontologoService(IOdontologoRepository odontologoRepository) {
		this.odontologoRepository = odontologoRepository;
	}


	public Odontologo guardar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return odontologoRepository.save(odontologo);
	}

	public void eliminar(Long id) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.odontologoRepository.deleteById(id);
	}

	public void actualizar(Odontologo odontologo) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		this.odontologoRepository.save(odontologo);
	}

	public Odontologo buscarPorId(Integer tipoDeBusqueda, String valor) throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		switch (tipoDeBusqueda){

			case 1:
				Long id = Long.parseLong(valor);
		Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

				break;
			case 2:
				Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);

		}





		if(odontologoOptional.isPresent()) {
			return odontologoOptional.get();
		} else {
			return null;
		}
	}

	public List<Odontologo> listarTodos() throws OdontologoException, DomicilioException, PacienteException, TurnoException {
		return odontologoRepository.findAll();
	}
}