package com.Dh.ProyectoIntegrador.controller;

import com.Dh.ProyectoIntegrador.Excepciones.DomicilioException;
import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.model.Domicilio;
import com.Dh.ProyectoIntegrador.model.Paciente;
import com.Dh.ProyectoIntegrador.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {
    private IService<Domicilio> domicilioIService;
    public DomicilioController(IService<Domicilio> domicilioIService) {
        this.domicilioIService = domicilioIService;
    }

    @PostMapping("/registrar")
    public ResponseEntity guardar(@RequestBody Domicilio domicilio){
	    try {
			 domicilio = domicilioIService.guardar(domicilio);

	    } catch (Exception e) {

	    }
		return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        ResponseEntity response = null;
	    Domicilio domicilioEncontrado = null;
	    try {
		    domicilioEncontrado = this.domicilioIService.buscarPorId(id);
	    } catch (Exception e) {

	    }

	    if (domicilioEncontrado != null) {
            response =new ResponseEntity<>(domicilioEncontrado, HttpStatus.ACCEPTED);
        }else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PutMapping("/actualizar")
    public void actualizar(@RequestBody Domicilio domicilio) throws OdontologoException, DomicilioException {
        this.domicilioIService.actualizar(domicilio);
        try {
            this.domicilioIService.actualizar(domicilio);
        } catch (OdontologoException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
	    try {
		    this.domicilioIService.eliminar(id);
	    } catch (Exception e) {

	    }
    }
    @GetMapping("/listar")
    public ResponseEntity listarTodos(){
        ResponseEntity response = null;
	    List<Domicilio> listaDomicilios = null;
	    try {
		    listaDomicilios = this.domicilioIService.listarTodos();
	    if (listaDomicilios != null) {
            response = new ResponseEntity<>(listaDomicilios, HttpStatus.ACCEPTED);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	    } catch (Exception e) {

	    }

        return response;
    }
}
