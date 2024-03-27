package com.Dh.ProyectoIntegrador.dto.turnos.request;

import com.Dh.ProyectoIntegrador.dto.turnos.TurnoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoRequestDTO extends TurnoDTO {


//	private Long id;
//	private Long id_odontologo;
//	private Long id_paciente;
//
//	private LocalDateTime fechaYHora;
}
