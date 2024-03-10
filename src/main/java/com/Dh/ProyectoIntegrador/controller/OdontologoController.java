package com.Dh.ProyectoIntegrador.controller;


import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.model.Odontologo;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

	private IService<Odontologo> odontologoIService;

	public OdontologoController(IService<Odontologo> odontologoIService) {
		this.odontologoIService = odontologoIService;
	}

	public ResponseEntity agregar(@RequestBody Odontologo odontologo) {
		ResponseEntity response = null;
		try {
			Odontologo odontologoGuardado = this.odontologoIService.guardar(odontologo);
			if (odontologoGuardado != null) {
				response = new ResponseEntity<Odontologo>(odontologoGuardado, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<Odontologo>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
				response = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (OdontologoException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PutMapping("/actualizar")
	public ResponseEntity actualizar(@RequestBody Odontologo odontologo) {
		ResponseEntity response = null;
		try {
			this.odontologoIService.actualizar(odontologo);
		} catch (OdontologoException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
			return response;
		}
		return response = new ResponseEntity<Odontologo>(HttpStatus.OK);
	}
//TODO METODO ACTUALIZAR SE LE PASA SOLO EL ID O TODOS LOS DATOS???
	//TODO METODO ACTUALIZAR CON VOID? Y EXCEPTION O CAMBIAR EL VOID??

	@GetMapping("/listar")

	public ResponseEntity listarTodos() throws OdontologoException {
		ResponseEntity response = null;
		List<Odontologo> listaOdontologos = this.odontologoIService.listarTodos();
		if (listaOdontologos != null) {
			response = new ResponseEntity(listaOdontologos, HttpStatus.FOUND);
		} else {
			response = new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
