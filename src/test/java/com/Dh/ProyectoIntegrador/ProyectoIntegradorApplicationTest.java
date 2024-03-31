package com.Dh.ProyectoIntegrador;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.request.OdontologoRequestDTO;

import com.Dh.ProyectoIntegrador.dto.odontologos.response.OdontologoResponseDTOFull;
import com.Dh.ProyectoIntegrador.excepciones.ResourceNotDeletedException;
import com.Dh.ProyectoIntegrador.excepciones.ResourceNotFoundException;

import com.Dh.ProyectoIntegrador.service.implementacion.OdontologoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class ProyectoIntegradorApplicationTest {


	@Autowired

	private OdontologoService odontologoService;

	@Test
	public void guardarOdontologoEnService() {

		OdontologoRequestDTO odontologoAGuardar = new OdontologoRequestDTO();

		odontologoAGuardar.setApellido("ApellidoPrueba");
		odontologoAGuardar.setNombre("NombrePrueba");
		odontologoAGuardar.setMatricula("MatriculaPrueba");

		OdontologoResponseDTOFull odontologoGuardado = (OdontologoResponseDTOFull) odontologoService.guardar(odontologoAGuardar);
		assertEquals(odontologoAGuardar.getNombre(), odontologoGuardado.getNombre());
		assertEquals(odontologoAGuardar.getApellido(), odontologoGuardado.getApellido());
		assertEquals(odontologoAGuardar.getMatricula(), odontologoGuardado.getMatricula());
	}

	@Test
	public void eliminarOdontologoEnService() {

		Long indiceOdontologoAEliminar = -1L;

		assertThrows(ResourceNotDeletedException.class, () -> {
			odontologoService.eliminar(indiceOdontologoAEliminar);
		});


	}

	@Test
	public void modificarOdontologoEnService() {

		OdontologoRequestDTO odontologoAActualizar = new OdontologoRequestDTO();
		odontologoAActualizar.setId(30L);
		odontologoAActualizar.setNombre("NombrePruebaActualizar");
		odontologoAActualizar.setApellido("ApellidoPruebaActualizar");
		odontologoAActualizar.setMatricula("MatricualPruebaActualizar");

		assertThrows(ResourceNotFoundException.class, ()->{
			odontologoService.actualizar(odontologoAActualizar);
		});
	}

@Test
	public void listarOdontologosEnService(){

	OdontologoRequestDTO odontologoAGuardar1 = new OdontologoRequestDTO();
	OdontologoRequestDTO odontologoAGuardar2 = new OdontologoRequestDTO();

	odontologoAGuardar1.setApellido("ApellidoPrueba1");
	odontologoAGuardar1.setNombre("NombrePrueba1");
	odontologoAGuardar1.setMatricula("MatriculaPrueba1");


	odontologoAGuardar2.setApellido("ApellidoPrueba2");
	odontologoAGuardar2.setNombre("NombrePrueba2");
	odontologoAGuardar2.setMatricula("MatriculaPrueba2");

	OdontologoResponseDTOFull odontologoGuardado1 = (OdontologoResponseDTOFull) odontologoService.guardar(odontologoAGuardar1);
	OdontologoResponseDTOFull odontologoGuardado2 = (OdontologoResponseDTOFull) odontologoService.guardar(odontologoAGuardar2);
	List<OdontologoDTO> listaOdontologosDTO = odontologoService.listarTodos();

	assertThat(listaOdontologosDTO).hasSizeGreaterThan(1);

}




























}