package com.Dh.ProyectoIntegrador.dto.turnos.response;


import com.Dh.ProyectoIntegrador.dto.turnos.TurnoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoResponseDTO extends TurnoDTO {


	private String pacienteNombre;
	private String pacienteApellido;

	private String odontologoNombre;
	private String odontologoApellido;

}
