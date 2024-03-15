package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.model.Odontologo;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

	private IService<Odontologo> odontologoIService;
	@Autowired
	public OdontologoController(IService<Odontologo> odontologoIService) {
		this.odontologoIService = odontologoIService;
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
	public ResponseEntity buscarPorId(@PathVariable Integer id) {
		ResponseEntity response = null;
		Odontologo odontologoEncontrado = null;
		try {
			odontologoEncontrado = this.odontologoIService.buscarPorId(id);
			if (odontologoEncontrado != null) {
				response = new ResponseEntity<>(odontologoEncontrado, HttpStatus.FOUND);
			} else {
				response = new ResponseEntity("No se encontro Odontologo", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	//TODO CAMBIAR METODO ACTUALIZAR COMO EL DEL TURNO
	//TODO cuando se ve el ResponseEnntity?
	@PutMapping("/actualizar")
	public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
		ResponseEntity response = null;
		try {
			if(odontologo != null) {
				this.odontologoIService.actualizar(odontologo);

				Odontologo actualizarOdontologo = odontologoIService.buscarPorId(odontologo.getId());
				if (actualizarOdontologo.compareTo(odontologo)==0) {
					return new ResponseEntity(actualizarOdontologo, HttpStatus.OK);
				} else {
					return new ResponseEntity("El odontólogo con el ID especificado no existe", HttpStatus.NOT_FOUND);
				}
			}
		} catch (Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
			return response;
		}
		return  new ResponseEntity("Actualizacion correcta", HttpStatus.OK);
	}

	//TODO METODO ACTUALIZAR CON VOID? Y EXCEPTION O CAMBIAR EL VOID??
	//TODO RESPONSE ENTITY ESPECIFICO O GENERICO?



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


	@DeleteMapping("eliminar/{id}")
	public ResponseEntity eliminar(@PathVariable Integer id) {
		ResponseEntity response = null;
		try {
			this.odontologoIService.eliminar(id);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Odontologo eliminado correctamente", HttpStatus.OK);
	}

}
