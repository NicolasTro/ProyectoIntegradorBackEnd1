package com.Dh.ProyectoIntegrador.dto.pacientes.response;

import com.Dh.ProyectoIntegrador.dto.pacientes.PacienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteResponseDTOName extends PacienteDTO {

    private Long id;
    private String apellido;
}
