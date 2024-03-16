package com.Dh.ProyectoIntegrador.controller;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	private IService<Paciente> pacienteService;
@Autowired
	public PacienteController(IService<Paciente> pacienteService) {
		this.pacienteService = pacienteService;
	}

	@PostMapping("/registrar")
	public ResponseEntity guardar(@RequestBody Paciente paciente) {
		ResponseEntity response = null;
		try {
			paciente = pacienteService.guardar(paciente);
			if (paciente != null) {
				response = new ResponseEntity("Paciente registrado con exito", HttpStatus.CREATED);
			} else {
				response = new ResponseEntity("Paciente registrado con exito", HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) throws OdontologoException, DomicilioException {
		ResponseEntity response = null;
		try {
			Paciente pacienteEncontrado = pacienteService.buscarPorId(id);
			if (pacienteEncontrado != null) {
				response = new ResponseEntity(pacienteEncontrado, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity("No se encontro el paciente", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@PutMapping("/actualizar")
	public ResponseEntity actualizar(@RequestBody Paciente paciente) {
		ResponseEntity response = null;
		try {
			this.pacienteService.actualizar(paciente);
		} catch (Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
			return response;
		}
		return new ResponseEntity("Actualizacion correcta de Paciente", HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity eliminar(@PathVariable Long id) throws OdontologoException, DomicilioException {
		ResponseEntity response = null;
		try {
			this.pacienteService.eliminar(id);
		} catch (Exception e) {
			response = new ResponseEntity("Error al eliminar Paciente.", HttpStatus.BAD_REQUEST);
			return response;
		}
		return new ResponseEntity("Paciente eliminado correctamente.", HttpStatus.OK);
	}


	@GetMapping("/listar")
	public ResponseEntity listarTodos() {
		ResponseEntity response = null;
		List<Paciente> listaPacientes = null;
		try {
			listaPacientes = this.pacienteService.listarTodos();
			if (listaPacientes.size() > 0) {
				response = new ResponseEntity(listaPacientes, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity("No se encontraron odontologos", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
