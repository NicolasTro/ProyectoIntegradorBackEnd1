package com.Dh.ProyectoIntegrador.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    @OneToOne
    private Domicilio domicilio;
    @OneToMany(mappedBy = "paciente")
    private Set<Turno> turnoSet = new HashSet<>();

}
