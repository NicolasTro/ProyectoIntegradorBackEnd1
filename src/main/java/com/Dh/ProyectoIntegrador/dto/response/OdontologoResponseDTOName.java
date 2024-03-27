package com.Dh.ProyectoIntegrador.dto.response;

import com.Dh.ProyectoIntegrador.dto.OdontologoDTO;
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


}
