package com.Dh.ProyectoIntegrador.dto.response;


import com.Dh.ProyectoIntegrador.dto.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OdontologoResponseDTO extends OdontologoDTO {


	private Long id;
	private String apellido;
	private String matricula;



}
