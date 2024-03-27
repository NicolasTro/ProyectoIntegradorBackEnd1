package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dto.response.OdontologoResponseDTO;
import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.service.IService;
import com.Dh.ProyectoIntegrador.service.IServiceDTOHQL;
import com.Dh.ProyectoIntegrador.service.IServiceHQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("odontologos")
@Slf4j
public class OdontologoController {

	private IService<Odontologo> odontologoIService;
	private IServiceHQL<Odontologo> odontologoIServiceHQL;
	private IServiceDTOHQL<OdontologoResponseDTO> odontologoIServiceDTOHQL;
	@Autowired
	public OdontologoController(IService<Odontologo> odontologoIService, IServiceHQL<Odontologo> odontologoIServiceHQL, IServiceDTOHQL<OdontologoResponseDTO> odontologoIServiceDTOHQL) {
		this.odontologoIService = odontologoIService;
		this.odontologoIServiceHQL = odontologoIServiceHQL;
		this.odontologoIServiceDTOHQL = odontologoIServiceDTOHQL;
	}

	//TODO Preguntarle a la profe sobre los AutoWirdes y si hay dos como en este acso, es nececsario ponerle a ambos?

	@GetMapping("/buscar")
	public ResponseEntity<Odontologo> buscar(@RequestParam("valor") String valor,@RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
		ResponseEntity response =  null;
		try {
			Optional<List<Odontologo>> odontologoBuscar = odontologoIServiceHQL.buscar(tipoDeBusqueda, valor);

			if (odontologoBuscar != null) {
				response = new ResponseEntity<>(odontologoBuscar.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		}  catch (Exception  e) {
			return new ResponseEntity( HttpStatus.I_AM_A_TEAPOT);
		}
		return response;
	}
	@PostMapping("/registrar")
	public ResponseEntity guardar(@RequestBody Odontologo odontologo) {
		ResponseEntity response = null;
		try {
			Odontologo odontologoGuardado = this.odontologoIService.guardar(odontologo);
			if (odontologoGuardado != null) {
				response = new ResponseEntity(odontologoGuardado, HttpStatus.CREATED);
				//TODO ES NECESARIO EL ELSE DEL RESPONSE ENTITY O NO?
			} else {
				response = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),  HttpStatus.NO_CONTENT);
		}
		return response;
	}


	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		ResponseEntity response = null;
		Odontologo odontologoEncontrado = null;
		try {
			odontologoEncontrado = this.odontologoIService.buscarPorId(id);
			if (odontologoEncontrado != null) {
				response = new ResponseEntity<>(odontologoEncontrado, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	//TODO CAMBIAR METODO ACTUALIZAR COMO EL DEL TURNO
	//TODO cuando se ve el ResponseEnntity?
	@PutMapping("/actualizar")
	public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) throws PacienteException, OdontologoException, DomicilioException, TurnoException {
		ResponseEntity response = null;

			if(odontologo != null) {
				this.odontologoIService.actualizar(odontologo);

				Odontologo actualizarOdontologo = odontologoIService.buscarPorId(odontologo.getId());
				log.info("estamos logueando actualizar" + actualizarOdontologo);



				} else {
					return new ResponseEntity( HttpStatus.NOT_FOUND);
				}


		return  new ResponseEntity(odontologo, HttpStatus.OK);
	}

	//TODO METODO ACTUALIZAR CON VOID? Y EXCEPTION O CAMBIAR EL VOID??




	@GetMapping("/listar")

	public ResponseEntity<List<Odontologo>> listarTodos() {
		ResponseEntity response = null;
		List<Odontologo> listaOdontologos = null;
		try {
			listaOdontologos = this.odontologoIService.listarTodos();
			if (listaOdontologos.size() > 0) {
				response = new ResponseEntity(listaOdontologos, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity<List<Odontologo>>(listaOdontologos,  HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}



	@GetMapping("/listarDTO")

	public ResponseEntity<List<OdontologoResponseDTO>> listarTodosDTO() {
		ResponseEntity response = null;
		Optional<List<OdontologoResponseDTO>> listaOdontologos = null;
		try {

			listaOdontologos = this.odontologoIServiceDTOHQL.listarTodosIDNombre();

			if (!listaOdontologos.isEmpty()) {
				response = new ResponseEntity(listaOdontologos.get(), HttpStatus.FOUND);
			} else {
				response = new ResponseEntity<List<Odontologo>>( HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}


	@DeleteMapping("eliminar/{id}")
	public ResponseEntity eliminar(@PathVariable Long id) {
		ResponseEntity response = null;
		try {
			this.odontologoIService.eliminar(id);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Odontologo eliminado correctamente", HttpStatus.OK);
	}

}
