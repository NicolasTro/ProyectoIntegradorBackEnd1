package com.Dh.ProyectoIntegrador.controller;


import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.model.Paciente;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

private IService<Paciente> pacienteService;

	public PacienteController(IService<Paciente> pacienteService) {
		this.pacienteService = pacienteService;
	}

@PostMapping("/registrar")
public ResponseEntity guardar(@RequestBody Paciente paciente) throws OdontologoException, DomicilioException {
		ResponseEntity response = null;


				pacienteService.guardar(paciente);
		return response;




}


@GetMapping("/{id}")
public Paciente buscarPorId(@PathVariable Integer id) throws OdontologoException, DomicilioException {
		return pacienteService.buscarPorId(id);
}


@PutMapping("/actualizar")
public void actualizar(@RequestBody Paciente paciente) throws OdontologoException, DomicilioException {
		this.pacienteService.actualizar(paciente);


}

@DeleteMapping("/{id}")
public void eliminar(@PathVariable Integer id) throws OdontologoException, DomicilioException {
		this.pacienteService.eliminar(id);
}


@GetMapping("/listar")
public List<Paciente> listarTodos() throws OdontologoException, DomicilioException {
		return this.pacienteService.listarTodos();
}

}
