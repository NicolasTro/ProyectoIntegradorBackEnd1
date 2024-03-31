package com.Dh.ProyectoIntegrador.dto.turnos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO {

	private Long id;

	private Long odontologo_Id;
	private Long paciente_Id;

	private LocalDateTime fechaYHora;
}
