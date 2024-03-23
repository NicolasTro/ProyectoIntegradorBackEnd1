package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

	private IService<Turno> turnoIService;
	private IServiceHQL<Turno> turnoIServiceHQL;

	@Autowired
	public TurnoController(IService<Turno> turnoIService, IServiceHQL<Turno> turnoIServiceHQL) {
		this.turnoIService = turnoIService;
		this.turnoIServiceHQL = turnoIServiceHQL;
	}
	@GetMapping("/buscar")
	public ResponseEntity<Turno> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
		ResponseEntity response =  null;
		try {
			Optional<List<Turno>> turnoBuscar = turnoIServiceHQL.buscar(tipoDeBusqueda, valor);

			if (turnoBuscar != null) {
				response = new ResponseEntity(turnoBuscar, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		}  catch (Exception  e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
		}
		return response;
	}

	@PostMapping("/registrar")
	public ResponseEntity guardar(@RequestBody Turno turno) {
		ResponseEntity response = null;
		try {
			turno = turnoIService.guardar(turno);
			if (turno != null) {
				response = new ResponseEntity(turno, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping("/actualizar")
	public ResponseEntity actualizar(@RequestBody Turno turno) {
		ResponseEntity response = null;
		try {
			turnoIService.actualizar(turno);
			Turno nuevoTurno = turnoIService.buscarPorId(turno.getId());
			if(nuevoTurno.equals(turno)){
			response = new ResponseEntity(HttpStatus.ACCEPTED);
			}else{
			response = new ResponseEntity("No se pudieron modificar los registros", HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@DeleteMapping("/eliminar/{id}")

	public ResponseEntity eliminar(@PathVariable Long id) {
		ResponseEntity response = null;
		try {
			turnoIService.eliminar(id);
			response = new ResponseEntity("Registro eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@GetMapping("{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		ResponseEntity response = null;
		try {
			Turno turnoEncontrado = turnoIService.buscarPorId(id);
			if (turnoEncontrado != null) {
				response = new ResponseEntity(turnoEncontrado, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity("No se encontro el registro", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/listar")

	public ResponseEntity listarTodos() {
		ResponseEntity response = null;
		try {
			List<Turno> listaTurnos = this.turnoIService.listarTodos();
			if (!listaTurnos.isEmpty()) {
				response = new ResponseEntity(listaTurnos, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity("No se encontraron registros", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
