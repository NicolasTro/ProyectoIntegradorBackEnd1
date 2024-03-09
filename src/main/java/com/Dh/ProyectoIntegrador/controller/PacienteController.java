package com.Dh.ProyectoIntegrador.controller;


import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.model.Paciente;
import com.Dh.ProyectoIntegrador.service.IService;
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
public Paciente guardar(@RequestBody Paciente paciente){
		return pacienteService.guardar(paciente);
}


@GetMapping("/{id}")
public Paciente buscarPorId(@PathVariable Integer id){
		return pacienteService.buscarPorId(id);
}


@PutMapping("/actualizar")
public void actualizar(@RequestBody Paciente paciente) throws OdontologoException {
		this.pacienteService.actualizar(paciente);
}

@DeleteMapping("/{id}")
public void eliminar(@PathVariable Integer id){
		this.pacienteService.eliminar(id);
}


@GetMapping("/listar")
public List<Paciente> listarTodos(){
		return this.pacienteService.listarTodos();
}

}
