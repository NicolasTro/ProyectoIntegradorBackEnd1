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
        ResponseEntity response = null;
	    try {
			 Domicilio domicilioGuardado = this.domicilioIService.guardar(domicilio);
             if (domicilioGuardado != null) {
                 response = new ResponseEntity(domicilioGuardado, HttpStatus.CREATED);
             } else {
                 response = new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
             }


	    } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
	    }
		return response;
    }


    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        ResponseEntity response = null;
	    Domicilio domicilioEncontrado = null;
	    try {
		    domicilioEncontrado = this.domicilioIService.buscarPorId(id);
            if (domicilioEncontrado != null) {
                response =new ResponseEntity<>(domicilioEncontrado, HttpStatus.FOUND);
            }else {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
	    } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
        return response;
    }


    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Domicilio domicilio) throws OdontologoException, DomicilioException {
        ResponseEntity response = null;
        try {
            this.domicilioIService.actualizar(domicilio);
        } catch (Exception e) {
            response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
            return response;
        }
        return new ResponseEntity("Actualizaccion correcta de Domicilio.", HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        ResponseEntity response = null;
	    try {
		    this.domicilioIService.eliminar(id);
	    } catch (Exception e) {
            return new ResponseEntity("Error al eliminar Domicilio.", HttpStatus.BAD_REQUEST);
	    }
        return new ResponseEntity("Domicilio eliminado correctamente.", HttpStatus.OK);
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
            return new ResponseEntity("No se puede listar los Domicilios.", HttpStatus.BAD_REQUEST);
	    }
        return response;
    }
}
