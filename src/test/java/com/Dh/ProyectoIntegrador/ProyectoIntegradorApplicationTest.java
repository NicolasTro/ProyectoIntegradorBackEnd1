package com.Dh.ProyectoIntegrador;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.Dh.ProyectoIntegrador.dto.odontologos.request.OdontologoRequestDTO;


import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDomicilioDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.request.PacienteRequestDTO;
import com.Dh.ProyectoIntegrador.dto.pacientes.response.PacienteResponseDTOFull;
import com.Dh.ProyectoIntegrador.entity.Domicilio;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import com.Dh.ProyectoIntegrador.excepciones.ResourceNotFoundException;
import com.Dh.ProyectoIntegrador.repository.IPacienteRepository;
import com.Dh.ProyectoIntegrador.service.implementacion.OdontologoService;
import com.Dh.ProyectoIntegrador.service.implementacion.PacienteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

	@Slf4j
@SpringBootTest
	@Transactional
class ProyectoIntegradorApplicationTest {


@Autowired
private OdontologoService odontologoService;

@Autowired
private PacienteService pacienteService;

@Autowired
private IPacienteRepository pacienteRepository;

	@Test
	public void guardarOdontologo() {



		OdontologoRequestDTO od = new OdontologoRequestDTO();


		od.setApellido("apellidoJero");
		od.setNombre("nombreJero");
		od.setMatricula("matriculaPatente");


		OdontologoDTO odNUevo = odontologoService.guardar(od);


		log.info(odNUevo.getNombre());
	}


















































































































































































	@Test
		public void guardarPaciente() {

		PacienteDomicilioDTO pacienteAGuardar = new PacienteDomicilioDTO();

		pacienteAGuardar.setApellido("ApellidoPrueba");
		pacienteAGuardar.setNombre("NombrePrueba");
		pacienteAGuardar.setDni("DniPrueba");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaHora = LocalDate.parse("2020-02-02", formatter);
		pacienteAGuardar.setFechaIngreso(fechaHora);
		pacienteAGuardar.setCalle("CallePrueba");
		pacienteAGuardar.setNumero(2);
		pacienteAGuardar.setLocalidad("LocalidadPrueba");
		pacienteAGuardar.setProvincia("ProvinciaPrueba");

		PacienteDomicilioDTO pacienteGuardado = pacienteService.guardar(pacienteAGuardar);

		PacienteDomicilioDTO pacienteRecuperado = pacienteService.buscarPorId(pacienteGuardado.getId());

		assertEquals(pacienteAGuardar.getApellido(), pacienteRecuperado.getApellido());
		assertEquals(pacienteAGuardar.getNombre(), pacienteRecuperado.getNombre());
		assertEquals(pacienteAGuardar.getDni(), pacienteRecuperado.getDni());
		assertEquals(pacienteAGuardar.getFechaIngreso(), pacienteRecuperado.getFechaIngreso());
		assertEquals(pacienteAGuardar.getCalle(), pacienteRecuperado.getCalle());
		assertEquals(pacienteAGuardar.getNumero(), pacienteRecuperado.getNumero());
		assertEquals(pacienteAGuardar.getLocalidad(), pacienteRecuperado.getLocalidad());
		assertEquals(pacienteAGuardar.getProvincia(), pacienteRecuperado.getProvincia());
	}

	@Test
		public void actualizarPaciente() {
		PacienteDomicilioDTO pacienteAGuardar = new PacienteDomicilioDTO();
		pacienteAGuardar.setApellido("ApellidoPrueba");
		pacienteAGuardar.setNombre("NombrePrueba");
		pacienteAGuardar.setDni("DniPrueba");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaHora = LocalDate.parse("2020-02-02", formatter);
		pacienteAGuardar.setFechaIngreso(fechaHora);
		pacienteAGuardar.setCalle("CallePrueba");
		pacienteAGuardar.setNumero(2);
		pacienteAGuardar.setLocalidad("LocalidadPrueba");
		pacienteAGuardar.setProvincia("ProvinciaPrueba");

		PacienteDomicilioDTO pacienteGuardado = pacienteService.guardar(pacienteAGuardar);

		pacienteGuardado.setApellido("NuevoApellido");
		pacienteGuardado.setNombre("NuevoNombre");

		pacienteService.actualizar(pacienteGuardado);

		PacienteDomicilioDTO pacienteActualizado = pacienteService.buscarPorId(pacienteGuardado.getId());

		assertEquals("NuevoApellido", pacienteActualizado.getApellido());
		assertEquals("NuevoNombre", pacienteActualizado.getNombre());
		assertEquals("DniPrueba", pacienteActualizado.getDni());
		assertEquals(fechaHora, pacienteActualizado.getFechaIngreso());
		assertEquals("CallePrueba", pacienteActualizado.getCalle());
		assertEquals(2, pacienteActualizado.getNumero());
		assertEquals("LocalidadPrueba", pacienteActualizado.getLocalidad());
		assertEquals("ProvinciaPrueba", pacienteActualizado.getProvincia());
	}

		@Test
		public void testBuscarPorIdExistente() {

			Paciente paciente = new Paciente();
			paciente.setNombre("Juan");
			paciente.setApellido("Pérez");
			paciente.setDni("12345678");
			Domicilio domicilioPaciente = new Domicilio();
			domicilioPaciente.setCalle("Calle 123");
			domicilioPaciente.setNumero(123);
			domicilioPaciente.setLocalidad("Ciudad");
			domicilioPaciente.setProvincia("Provincia");

			paciente.setDomicilio(domicilioPaciente);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			paciente.setFechaIngreso(LocalDate.parse("2022-01-01", formatter));

			Paciente pacienteGuardado = pacienteRepository.save(paciente);

			PacienteDomicilioDTO pacienteEncontrado = pacienteService.buscarPorId(pacienteGuardado.getId());

			// Verificamos que se haya encontrado el paciente correcto
			assertEquals(pacienteGuardado.getId(), pacienteEncontrado.getId());

			assertEquals(pacienteGuardado.getNombre(), pacienteEncontrado.getNombre());
			assertEquals(pacienteGuardado.getApellido(), pacienteEncontrado.getApellido());
			assertEquals(pacienteGuardado.getDni(), pacienteEncontrado.getDni());
			assertEquals(pacienteGuardado.getFechaIngreso(), pacienteEncontrado.getFechaIngreso());
			assertEquals(domicilioPaciente.getCalle(), pacienteEncontrado.getCalle());
			assertEquals(domicilioPaciente.getNumero(), pacienteEncontrado.getNumero());
			assertEquals(domicilioPaciente.getLocalidad(), pacienteEncontrado.getLocalidad());
			assertEquals(domicilioPaciente.getProvincia(), pacienteEncontrado.getProvincia());
		}
		@Test
		public void testListarTodos() {

		//	pacienteRepository.save(new Paciente("Nombre1", "Apellido1", "12345678", LocalDate.of(1990, 5, 15), "Calle 123", 123, "Ciudad", "Provincia"));
		//	pacienteRepository.save(new Paciente("Nombre2", "Apellido2", "87654321", LocalDate.of(1985, 10, 20), "Avenida 456", 456, "Otra Ciudad", "Otra Provincia"));

			PacienteDomicilioDTO pacienteAguardar1 = new PacienteDomicilioDTO();
			pacienteAguardar1.setApellido("ApellidoPrueba");
			pacienteAguardar1.setNombre("NombrePrueba");
			pacienteAguardar1.setDni("DniPrueba");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaHora1 = LocalDate.parse("2020-02-02", formatter);
			pacienteAguardar1.setFechaIngreso(fechaHora1);
			pacienteAguardar1.setCalle("CallePrueba");
			pacienteAguardar1.setNumero(2);
			pacienteAguardar1.setLocalidad("LocalidadPrueba");
			pacienteAguardar1.setProvincia("ProvinciaPrueba");
			PacienteDomicilioDTO pacienteAguardar2 = new PacienteDomicilioDTO();
			pacienteAguardar2.setNombre("NombrePrueba");
			pacienteAguardar2.setDni("DniPrueba");
			LocalDate fechaHora2 = LocalDate.parse("2020-02-02", formatter);
			pacienteAguardar2.setFechaIngreso(fechaHora2);
			pacienteAguardar2.setCalle("CallePrueba");
			pacienteAguardar2.setNumero(2);
			pacienteAguardar2.setLocalidad("LocalidadPrueba");
			pacienteAguardar2.setProvincia("ProvinciaPrueba");

			PacienteDomicilioDTO pacienteGuardado1 =  pacienteService.guardar(pacienteAguardar1);
			PacienteDomicilioDTO pacienteGuardado2 =  pacienteService.guardar(pacienteAguardar2);
			List<PacienteDomicilioDTO> listaPacientes = pacienteService.listarTodos();

			assertFalse(listaPacientes.isEmpty());

			assertThat(listaPacientes).hasSizeGreaterThan(1);
		}

		//@Test(expected = ResourceNotFoundException.class)
		@Disabled
		public void testListarTodosListaVacia() {
			pacienteRepository.deleteAll();

			pacienteService.listarTodos();
		}
	}
