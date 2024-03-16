package com.Dh.ProyectoIntegrador.entity;

import java.sql.Date;

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
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Odontologo odontologo;
    private Date fechaYHora;

}
