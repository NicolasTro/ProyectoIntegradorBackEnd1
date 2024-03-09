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

	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Integer id) {
		ResponseEntity response = null;

		Odontologo odontologoEncontrado = this.odontologoIService.buscarPorId(id);

<<<<<<< HEAD
		if(odontologoEncontrado != null){
			response = new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else{
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
=======
		if (odontologoEncontrado != null) {
			response = new ResponseEntity<>(odontologoEncontrado, HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity(HttpStatus.NOT_FOUND);
>>>>>>> e8b143eda05d742e63b28e0d5f3638cbbe3703f4
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


	@GetMapping("/listar")

	public ResponseEntity listarTodos() {
		ResponseEntity response = null;
		List<Odontologo> listaOdontologos = this.odontologoIService.listarTodos();
		if (listaOdontologos != null) {
			response = new ResponseEntity(listaOdontologos, HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
