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
	//TODO QUE PASA CON ESTO!!!!!!!!!!!!!!
	@GetMapping("/buscar")
	public ResponseEntity<TurnoDTO> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
	//	ResponseEntity response =  null;
	//	try {
			Optional<List<TurnoDTO>> turnoBuscar = turnoIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);

	//		if (turnoBuscar != null) {
	//			response = new ResponseEntity(turnoBuscar, HttpStatus.FOUND);
	//		} else {
	//			response = new ResponseEntity(HttpStatus.NOT_FOUND);
	//		}
	//	}  catch (Exception  e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
	//	}
		return new ResponseEntity(turnoBuscar, HttpStatus.FOUND);
	}

	@PostMapping("/registrar")
	public ResponseEntity<TurnoDTO> guardar(@RequestBody TurnoRequestDTO turnoRequestDTO) {
	//	ResponseEntity<TurnoDTO> response = null;
		TurnoDTO turno = null;
	//	try {
			turno = turnoIService.guardar(turnoRequestDTO);
	//		if (turno != null) {
	//			response = new ResponseEntity(turno, HttpStatus.OK);
	//		}
	//	} catch (Exception e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
		return new ResponseEntity(turno, HttpStatus.CREATED);
	}

	//TODO arreglar actualizar y envido de datos vacio.
	@PutMapping("/actualizar")
	public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoRequestDTO turnoRequestDTO) {
	//	ResponseEntity<TurnoDTO> response = null;
	//	try {
		TurnoDTO turnoActualizar = turnoIService.buscarPorId(turnoRequestDTO.getId());
			turnoIService.actualizar(turnoRequestDTO);
	//		response = new ResponseEntity(turnoRequestDTO, HttpStatus.ACCEPTED);

	//	} catch (Exception e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
		return new ResponseEntity(turnoActualizar, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")

	public ResponseEntity<TurnoDTO> eliminar(@PathVariable Long id) {
	//	ResponseEntity<TurnoDTO> response = null;
	//	try {
			turnoIService.eliminar(id);
	//		response = new ResponseEntity("Registro eliminado correctamente", HttpStatus.OK);
	//	} catch (Exception e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
		return new ResponseEntity("Turno eliminado", HttpStatus.OK);
	}


	@GetMapping("{id}")
	public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) {
	//	ResponseEntity<TurnoDTO> response = null;
	//	try {
			TurnoDTO turnoEncontrado = turnoIService.buscarPorId(id);
	//		if (turnoEncontrado != null) {
	//			response = new ResponseEntity(turnoEncontrado, HttpStatus.FOUND);
	//		} else {
	//			response = new ResponseEntity("No se encontro el registro", HttpStatus.NOT_FOUND);
	//		}
	//	} catch (Exception e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
		return new ResponseEntity(turnoEncontrado, HttpStatus.FOUND);
	}

	@GetMapping("/listar")

	public ResponseEntity<List<TurnoDTO>> listarTodos() {
	//	ResponseEntity<List<TurnoDTO>> response = null;
	//	try {
			List<TurnoDTO> listaTurnos = this.turnoIService.listarTodos();
	//		if (!listaTurnos.isEmpty()) {
	//			response = new ResponseEntity(listaTurnos, HttpStatus.FOUND);
	//		} else {
	//			response = new ResponseEntity("No se encontraron registros", HttpStatus.NOT_FOUND);
	//		}
	//	} catch (Exception e) {
	//		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
		return new ResponseEntity(listaTurnos, HttpStatus.FOUND);
	}@GetMapping("/listarDTO")

	public ResponseEntity<List<TurnoDTO>> listarTodosDTO() {
		ResponseEntity response = null;
		Optional<List<TurnoResponseDTO>> listaTurnos = null;
		try {

			listaTurnos = this.turnoIServiceDTO.listarTodosIDNombre();

			if (!listaTurnos.isEmpty()) {
				response = new ResponseEntity(listaTurnos.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity<List<Paciente>>( HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


}
