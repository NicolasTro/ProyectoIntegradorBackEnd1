package com.Dh.ProyectoIntegrador.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OdontologoDTO {

	private Long id;
	private String nombre;



}
