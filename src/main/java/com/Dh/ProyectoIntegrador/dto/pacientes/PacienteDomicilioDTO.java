package com.Dh.ProyectoIntegrador.dto.pacientes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDomicilioDTO extends PacienteDTO{



	private Long id;
	private String apellido;
	private String dni;
	private Date fechaIngreso;
	private String calle;
	private Integer numero;
	private String localidad;
	private String provincia;
	private Long domicilio_id;
}
