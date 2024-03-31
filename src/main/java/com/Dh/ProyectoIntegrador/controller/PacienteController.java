package com.Dh.ProyectoIntegrador.controller;


import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDomicilioDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.request.PacienteRequestDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOFull;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOName;
import com.Dh.ProyectoIntegrador.entity.Paciente;
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
// Controlador para manejar las operaciones relacionadas con los Pacientes
@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteController {

	private IService<PacienteDomicilioDTO> pacienteService;
	private IServiceHQL<PacienteDomicilioDTO>pacienteIServiceHQL;
	private IServiceDTO<PacienteResponseDTOName> pacienteIServiceDTO;
	@Autowired
	public PacienteController(IService<PacienteDomicilioDTO> pacienteService, IServiceHQL<PacienteDomicilioDTO> pacienteIServiceHQL, IServiceDTO<PacienteResponseDTOName> pacienteIServiceDTO) {
		this.pacienteService = pacienteService;
		this.pacienteIServiceHQL = pacienteIServiceHQL;
		this.pacienteIServiceDTO = pacienteIServiceDTO;
	}

	//##########################################################################################
	// Método para registrar un nuevo paciente
	@PostMapping("/registrar")
	public ResponseEntity<PacienteDTO> guardar(@RequestBody PacienteDomicilioDTO paciente) {

		PacienteDomicilioDTO pacienteGuardado = this.pacienteService.guardar(paciente);
		log.info("Guardando Pacientes");

		return new ResponseEntity(pacienteGuardado, HttpStatus.CREATED);
	}

	//##########################################################################################
	// Método para buscar un paciente por su ID
	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDTOFull> buscarPorId(@PathVariable Long id){
		PacienteDTO pacienteEnontrado = null;

		pacienteEnontrado = this.pacienteService.buscarPorId(id);
		log.info("Buscando el Pacientes con ID:" + id);

		return new ResponseEntity(pacienteEnontrado, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para actualizar un paciente
	@PutMapping("/actualizar")
	public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDomicilioDTO paciente) {

		this.pacienteService.actualizar(paciente);

		PacienteDTO actualizarPaciente = pacienteService.buscarPorId(paciente.getId());
		log.info("Actualizando el Pacientes con ID:" + paciente.getId());

		return new ResponseEntity(actualizarPaciente, HttpStatus.OK);
	}

	//##########################################################################################
	// Método para eliminar un paciente por su ID
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id)  {

		this.pacienteService.eliminar(id);
		log.info("Eliminando el Pacientes con ID:" + id);

		return new ResponseEntity(HttpStatus.OK);
	}


	//##########################################################################################
	// Método para listar todos los pacientes
	@GetMapping("/listar")
	public ResponseEntity<List<PacienteDTO>> listarTodos() {

		List<PacienteDomicilioDTO> listaPacientes = null;

		listaPacientes = this.pacienteService.listarTodos();
		log.info("Listando todos los Pacientes");

		return new ResponseEntity(listaPacientes, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para listar todos los pacientes en formato DTO
	@GetMapping("/listarDTO")
	public ResponseEntity<List<PacienteDomicilioDTO>> listarTodosDTO() {
		Optional<List<PacienteResponseDTOName>> listaPacientes = null;

		listaPacientes = this.pacienteIServiceDTO.listarTodosIDNombre();
		log.info("Listando PacientesDTO");

		return new ResponseEntity(listaPacientes, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para buscar pacientes por parámetros personalizados
	@GetMapping("/buscar")
	public ResponseEntity<List<PacienteDTO>> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {

		Optional<List<PacienteDomicilioDTO>> pacienteBuscar = pacienteIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);
		log.info("Busqueda personalizada de  con valor: " + valor + " y tipo de busqueda: " + tipoDeBusqueda);

		return new ResponseEntity(pacienteBuscar, HttpStatus.FOUND);
	}
	//##########################################################################################
}
