package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteController {

	private IService<Paciente> pacienteService;
	private IServiceHQL<Paciente>pacienteIServiceHQL;
	@Autowired
	public PacienteController(IService<Paciente> pacienteService, IServiceHQL<Paciente> pacienteIServiceHQL) {
		this.pacienteService = pacienteService;
		this.pacienteIServiceHQL = pacienteIServiceHQL;
	}

	// IServiceHQL
	@GetMapping("/buscar")
	public ResponseEntity<Paciente> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
		ResponseEntity response =  null;
		try {
			Optional<List<Paciente>> pacienteBuscar = pacienteIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);

			if (pacienteBuscar != null) {
				response = new ResponseEntity(pacienteBuscar.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);

			}
		}  catch (Exception  e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
		}
		return response;
	}
	@PostMapping("/registrar")
	public ResponseEntity guardar(@RequestBody Paciente paciente) {
		ResponseEntity response = null;
		try {
			paciente = pacienteService.guardar(paciente);
			if (paciente != null) {
				response = new ResponseEntity(paciente, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) throws OdontologoException, DomicilioException {
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
		return new ResponseEntity(paciente, HttpStatus.OK);
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
	public ResponseEntity<List<Paciente>> listarTodos() {
		ResponseEntity response = null;
		List<Paciente> listaPacientes = null;
		try {
			listaPacientes = this.pacienteService.listarTodos();
			if (listaPacientes.size() > 0) {
				log.info("tira la lista", listaPacientes);
				response = new ResponseEntity(listaPacientes, HttpStatus.OK);
			} else {
				response = new ResponseEntity("No se encontraron Pacientes", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.info("aca que sucede", e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
