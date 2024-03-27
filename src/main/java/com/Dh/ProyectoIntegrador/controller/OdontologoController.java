package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.Excepciones.PacienteException;
import com.Dh.ProyectoIntegrador.Excepciones.TurnoException;
import com.Dh.ProyectoIntegrador.dto.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.request.OdontologoRequestDTO;
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

	private IService<OdontologoDTO> odontologoIService;
	private IServiceHQL<OdontologoDTO> odontologoIServiceHQL;
	private IServiceDTOHQL<OdontologoDTO> odontologoIServiceDTOHQL;
	@Autowired
	public OdontologoController(IService<OdontologoDTO> odontologoIService, IServiceHQL<OdontologoDTO> odontologoIServiceHQL, IServiceDTOHQL<OdontologoDTO> odontologoIServiceDTOHQL) {
		this.odontologoIService = odontologoIService;
		this.odontologoIServiceHQL = odontologoIServiceHQL;
		this.odontologoIServiceDTOHQL = odontologoIServiceDTOHQL;
	}

	//TODO Preguntarle a la profe sobre los AutoWirdes y si hay dos como en este acso, es nececsario ponerle a ambos?

	@GetMapping("/buscar")
	public ResponseEntity<OdontologoResponseDTO> buscar(@RequestParam("valor") String valor,@RequestParam("tipoDeBusqueda") Integer tipoDeBusqueda) {
		ResponseEntity response =  null;
		try {
			Optional<List<OdontologoDTO>> odontologoBuscar = odontologoIServiceHQL.buscarDatosCompletos(tipoDeBusqueda, valor);

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
	public ResponseEntity<OdontologoDTO> guardar(@RequestBody OdontologoRequestDTO odontologo) {
		ResponseEntity response = null;
		try {
			OdontologoDTO odontologoGuardado = this.odontologoIService.guardar(odontologo);
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
	public ResponseEntity<OdontologoResponseDTO> buscarPorId(@PathVariable Long id) {
		ResponseEntity response = null;
		OdontologoDTO odontologoEncontrado = null;
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

	@PutMapping("/actualizar")
	public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoRequestDTO odontologo)  {
		ResponseEntity response = null;

			if(odontologo != null) {
				this.odontologoIService.actualizar(odontologo);

				OdontologoDTO actualizarOdontologo = odontologoIService.buscarPorId(odontologo.getId());
				log.info("estamos logueando actualizar" + actualizarOdontologo);



				} else {
					return new ResponseEntity( HttpStatus.NOT_FOUND);
				}


		return  new ResponseEntity(odontologo, HttpStatus.OK);
	}

	//TODO METODO ACTUALIZAR CON VOID? Y EXCEPTION O CAMBIAR EL VOID??




	@GetMapping("/listar")

	public ResponseEntity<List<OdontologoDTO>> listarTodos() {
		ResponseEntity response = null;
		List<OdontologoDTO> listaOdontologos = null;
		try {
			listaOdontologos = this.odontologoIService.listarTodos();
//			if (listaOdontologos.size() > 0) {
			Optional<List<OdontologoDTO>> lista = Optional.of(listaOdontologos);
				response = new ResponseEntity(lista, HttpStatus.FOUND);
//			} else {
//				response = new ResponseEntity<List<Odontologo>>(  HttpStatus.NOT_FOUND);
//			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}



	@GetMapping("/listarDTO")

	public ResponseEntity<List<OdontologoDTO>> listarTodosDTO() {
		ResponseEntity response = null;
		Optional<List<OdontologoDTO>> listaOdontologos = null;
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
	public ResponseEntity<String> eliminar(@PathVariable Long id) {
		ResponseEntity response = null;
		try {
			this.odontologoIService.eliminar(id);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Odontologo eliminado correctamente", HttpStatus.OK);
	}

}
