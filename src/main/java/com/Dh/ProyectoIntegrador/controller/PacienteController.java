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


	@GetMapping("/buscar")
	public ResponseEntity<List<PacienteDTO>> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
		ResponseEntity response =  null;
		try {
			Optional<List<PacienteDomicilioDTO>> pacienteBuscar = pacienteIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);
log.info("asdasd"+pacienteBuscar);
			if (pacienteBuscar.isPresent()) {
				response = new ResponseEntity(pacienteBuscar.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);

			}
		}  catch (Exception  e) {

			return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
		}
		return response;
	}
	@PostMapping("/registrar")
	public ResponseEntity<PacienteDTO> guardar(@RequestBody PacienteDomicilioDTO paciente) {
		ResponseEntity<PacienteDTO> response = null;
		try {
			PacienteDomicilioDTO pacienteGuardado = this.pacienteService.guardar(paciente);
			if (pacienteGuardado != null) {
				response = new ResponseEntity(pacienteGuardado, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponseDTOFull> buscarPorId(@PathVariable Long id){
		ResponseEntity<PacienteResponseDTOFull> response = null;
		PacienteDTO pacienteEnontrado = null;
		try {
			pacienteEnontrado = this.pacienteService.buscarPorId(id);
			if (pacienteEnontrado!=null) {
				response = new ResponseEntity(pacienteEnontrado, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@PutMapping("/actualizar")
	public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDomicilioDTO paciente) {
		ResponseEntity response = null;
		if(paciente != null) {
			this.pacienteService.actualizar(paciente);

			PacienteDTO actualizarPaciente = pacienteService.buscarPorId(paciente.getId());
			log.info("estamos logueando actualizar" + actualizarPaciente);
			return new ResponseEntity(paciente, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
	}
//TODO agarrar la exception de Odontologo y Paciente que tengan un turno agendado.
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id)  {
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
	public ResponseEntity<List<PacienteDTO>> listarTodos() {
		ResponseEntity response = null;
		List<PacienteDomicilioDTO> listaPacientes = null;
		try {
			listaPacientes = this.pacienteService.listarTodos();
			if (listaPacientes.size() > 0) {
				log.info("tira la lista", listaPacientes);

				response = new ResponseEntity(listaPacientes, HttpStatus.OK);
			} else {
				response = new ResponseEntity("No se encontraron Pacientes", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.info("aca que sucede"+ e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/listarDTO")

	public ResponseEntity<List<PacienteDomicilioDTO>> listarTodosDTO() {
		ResponseEntity response = null;
		Optional<List<PacienteResponseDTOName>> listaPacientes = null;
		try {

			listaPacientes = this.pacienteIServiceDTO.listarTodosIDNombre();

			if (!listaPacientes.isEmpty()) {
				response = new ResponseEntity(listaPacientes.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity<List<Paciente>>( HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
