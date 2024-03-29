package com.Dh.ProyectoIntegrador;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.request.OdontologoRequestDTO;


import com.Dh.ProyectoIntegrador.service.implementacion.OdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

	@Slf4j
@SpringBootTest
class ProyectoIntegradorApplicationTest {


@Autowired

	private OdontologoService odontologoService;




	@Test
	public void guardarOdontologo() {



		OdontologoRequestDTO od = new OdontologoRequestDTO();


		od.setApellido("apellidoJero");
		od.setNombre("nombreJero");
		od.setMatricula("matriculaPatente");


		OdontologoDTO odNUevo = odontologoService.guardar(od);

log.info(odNUevo.getNombre());
	}


}