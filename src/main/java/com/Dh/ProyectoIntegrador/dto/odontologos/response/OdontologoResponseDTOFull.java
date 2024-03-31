package com.Dh.ProyectoIntegrador.dto.odontologos.response;


import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OdontologoResponseDTOFull extends OdontologoDTO {


	private Long id;
	private String apellido;
	private String matricula;



}
