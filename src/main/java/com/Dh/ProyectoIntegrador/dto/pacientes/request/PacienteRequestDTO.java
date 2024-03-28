package com.Dh.ProyectoIntegrador.dto.pacientes.request;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteRequestDTO extends PacienteDTO {


    private Long id;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

}
