package com.Dh.ProyectoIntegrador.dto.request;


import com.Dh.ProyectoIntegrador.dto.OdontologoDTO;
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

public class OdontologoRequestDTO extends OdontologoDTO {

	private Long id;

	private String apellido;
	private String matricula;


}
