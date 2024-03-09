package com.Dh.ProyectoIntegrador.controller;



import com.Dh.ProyectoIntegrador.model.Odontologo;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

	private IService<Odontologo> odontologoIService;

	public OdontologoController(IService<Odontologo> odontologoIService) {
		this.odontologoIService = odontologoIService;
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Integer id){
		ResponseEntity response = null;
		Odontologo odontologoEncontrado = this.odontologoIService.buscarPorId(id);

		if(odontologoEncontrado != null){
			response = new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else{
			response = new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/listar")

	public List<Odontologo> listarTodos() {
		return this.odontologoIService.listarTodos();
	}
}
