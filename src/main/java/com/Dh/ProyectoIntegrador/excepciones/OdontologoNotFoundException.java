package com.Dh.ProyectoIntegrador.excepciones;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OdontologoNotFoundException extends RuntimeException{

	public OdontologoNotFoundException(String message) {
		super(message);
	}

}
