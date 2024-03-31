package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.request.OdontologoRequestDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.response.OdontologoResponseDTOFull;
import com.Dh.ProyectoIntegrador.dto.odontologos.response.OdontologoResponseDTOName;
import com.Dh.ProyectoIntegrador.entity.Odontologo;
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
// Controlador para manejar las operaciones relacionadas con los Odontologos
@RestController
@RequestMapping("odontologos")
@Slf4j
public class OdontologoController {

	private IService<OdontologoDTO> odontologoIService;
	private IServiceHQL<OdontologoDTO> odontologoIServiceHQL;
	private IServiceDTO<OdontologoDTO> odontologoIServiceDTO;

	@Autowired
	public OdontologoController(IService<OdontologoDTO> odontologoIService, IServiceHQL<OdontologoDTO> odontologoIServiceHQL, IServiceDTO<OdontologoDTO> odontologoIServiceDTO) {
		this.odontologoIService = odontologoIService;
		this.odontologoIServiceHQL = odontologoIServiceHQL;
		this.odontologoIServiceDTO = odontologoIServiceDTO;
	}

	//##########################################################################################
	// Método para buscar odontólogos por parámetros
	@GetMapping("/buscar")
	public ResponseEntity<List<OdontologoDTO>> buscar(@RequestParam("valor") String valor, @RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {

		Optional<List<OdontologoDTO>> odontologoBuscar = odontologoIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);
		log.info("Buscando Odontologo con valor: " + valor + " y tipo de Busqueda: " + tipoDeBusqueda);

		return new ResponseEntity(odontologoBuscar, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para registrar un nuevo odontólogo
	@PostMapping("/registrar")
	public ResponseEntity<OdontologoDTO> guardar(@RequestBody OdontologoRequestDTO odontologo) {

		OdontologoDTO odontologoGuardado = this.odontologoIService.guardar(odontologo);
		log.info("Guardando Odontologo.");

		return new ResponseEntity(odontologoGuardado, HttpStatus.CREATED);
	}

	//##########################################################################################
	// Método para buscar un odontólogo por su ID
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoResponseDTOFull> buscarPorId(@PathVariable Long id) {

		OdontologoDTO odontologoEncontrado = null;

		odontologoEncontrado = this.odontologoIService.buscarPorId(id);
		log.info("Buscando Odontologo con ID: "+ id);

		return new ResponseEntity(odontologoEncontrado, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para actualizar un odontólogo
	@PutMapping("/actualizar")
	public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoRequestDTO odontologo) {

		this.odontologoIService.actualizar(odontologo);

		OdontologoDTO actualizarOdontologo = odontologoIService.buscarPorId(odontologo.getId());
		log.info("Actualizando Odontologo.");

		return new ResponseEntity(actualizarOdontologo, HttpStatus.OK);
	}

	//##########################################################################################
	// Método para listar todos los odontólogos
	@GetMapping("/listar")
	public ResponseEntity<List<OdontologoDTO>> listarTodos() {
		List<OdontologoDTO> listaOdontologos = null;

		listaOdontologos = this.odontologoIService.listarTodos();

		Optional<List<OdontologoDTO>> lista = Optional.of(listaOdontologos);
		log.info("Listando Odontologos");

		return new ResponseEntity(listaOdontologos, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para listar todos los odontólogos en formato DTO
	@GetMapping("/listarDTO")
	public ResponseEntity<List<OdontologoDTO>> listarTodosDTO() {

		Optional<List<OdontologoDTO>> listaOdontologos = this.odontologoIServiceDTO.listarTodosIDNombre();
		log.info("Listando OdontologosDTO");

		return new ResponseEntity(listaOdontologos, HttpStatus.FOUND);
	}

	//##########################################################################################
	// Método para eliminar un odontólogo por su ID
	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) {

		this.odontologoIService.eliminar(id);
		log.info("Eliminando Odontologo con ID:" + id);

		return new ResponseEntity(HttpStatus.OK);
	}
	//##########################################################################################
}
