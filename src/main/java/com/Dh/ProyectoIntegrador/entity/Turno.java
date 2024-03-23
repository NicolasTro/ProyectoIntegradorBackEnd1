package com.Dh.ProyectoIntegrador.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "turnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("turnoSet")
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id")
    @JsonIgnoreProperties("turnoSet")
    private Odontologo odontologo;
    private Date fechaYHora;

}
