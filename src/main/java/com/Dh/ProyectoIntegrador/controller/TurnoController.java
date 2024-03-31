package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDomicilioDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOName;
import com.Dh.ProyectoIntegrador.dto.turnos.TurnoDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.request.TurnoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.turnos.response.TurnoResponseDTO;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.entity.Turno;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTO;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//##########################################################################################
// Controlador para manejar las operaciones relacionadas con los Turnos
@RestController
@RequestMapping("/turnos")
@Slf4j
public class TurnoController {

	private IService<TurnoDTO> turnoIService;
	private IServiceHQL<TurnoDTO> turnoIServiceHQL;
	private IServiceDTO<TurnoResponseDTO> turnoIServiceDTO;

	@Autowired
	public TurnoController(IService<TurnoDTO> turnoIService, IServiceHQL<TurnoDTO> turnoIServiceHQL, IServiceDTO<TurnoResponseDTO> turnoIServiceDTO) {
		this.turnoIService = turnoIService;
		this.turnoIServiceHQL = turnoIServiceHQL;
		this.turnoIServiceDTO = turnoIServiceDTO;
	}

	//##########################################################################################
	// Método para buscar turnos por parámetros personalizados
	@GetMapping("/buscar")
	public ResponseEntity<TurnoDTO> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {

		Optional<List<TurnoDTO>> turnoBuscar = turnoIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);
		log.info("Busqueda personalizada de Turnos con valor: " + valor + " y tipo de busqueda: " + tipoDeBusqueda);

		return new ResponseEntity(turnoBuscar.get(), HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para registrar un nuevo turno
	@PostMapping("/registrar")
	public ResponseEntity<TurnoDTO> guardar(@RequestBody TurnoRequestDTO turnoRequestDTO) {

		TurnoDTO turno = null;

		turno = turnoIService.guardar(turnoRequestDTO);
		log.info("Guardando el Turno");

		return new ResponseEntity(turno, HttpStatus.CREATED);
	}

	//##########################################################################################
	// Método para actualizar un turno
	@PutMapping("/actualizar")
	public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoRequestDTO turnoRequestDTO) {

		TurnoDTO turnoActualizar = turnoIService.buscarPorId(turnoRequestDTO.getId());

		turnoIService.actualizar(turnoRequestDTO);
		log.info("Actualizando el Turno con ID:" + turnoRequestDTO.getId());

		return new ResponseEntity(turnoActualizar, HttpStatus.OK);
	}

	//##########################################################################################
	// Método para eliminar un turno por su ID
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) {

		turnoIService.eliminar(id);
		log.info("Eliminando el Turno con ID:" + id);

		return new ResponseEntity(HttpStatus.OK);
	}

	//##########################################################################################
	// Método para buscar un turno por su ID
	@GetMapping("{id}")
	public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) {

		TurnoDTO turnoEncontrado = turnoIService.buscarPorId(id);
		log.info("Buscando el Turno con ID:" + id);

		return new ResponseEntity(turnoEncontrado, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para listar todos los turnos
	@GetMapping("/listar")

	public ResponseEntity<List<TurnoDTO>> listarTodos() {

		List<TurnoDTO> listaTurnos = this.turnoIService.listarTodos();
		log.info("Listando todos los Turnos");
		return new ResponseEntity(listaTurnos, HttpStatus.FOUND);

	}

	//##########################################################################################
	// Método para listar todos los turnos en formato DTO
	@GetMapping("/listarDTO")

	public ResponseEntity<List<TurnoDTO>> listarTodosDTO() {

		Optional<List<TurnoResponseDTO>> listaTurnos = null;

		listaTurnos = this.turnoIServiceDTO.listarTodosIDNombre();
		log.info("Listando los PacientesDTO");

		return new ResponseEntity(listaTurnos, HttpStatus.FOUND);
	}
	//##########################################################################################
}
