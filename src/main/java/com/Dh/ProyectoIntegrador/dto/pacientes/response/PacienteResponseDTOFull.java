package com.Dh.ProyectoIntegrador.dto.pacientes.response;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteResponseDTOFull extends PacienteDTO {


    private Long id;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
    private Long domicilio_id;
}
