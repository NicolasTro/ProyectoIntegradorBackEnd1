package com.Dh.ProyectoIntegrador.dto.odontologos.response;

import com.Dh.ProyectoIntegrador.dto.odontologos.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoResponseDTOName extends OdontologoDTO {



	private Long id;
private String apellido;

}
