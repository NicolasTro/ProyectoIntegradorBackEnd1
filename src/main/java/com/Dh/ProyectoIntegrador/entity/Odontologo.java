package com.Dh.ProyectoIntegrador.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Odontologo implements Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String matricula;
	@OneToMany(mappedBy = "odontologo")
	private Set<Turno> turnoSet = new HashSet<>();

	@Override
	public int compareTo(Object object) {
		if (object instanceof Odontologo) {
			Odontologo odontologoAComparar = (Odontologo) object;
			if (this.id < (odontologoAComparar.getId())) {
				return -1;
			} else if (this.id > odontologoAComparar.getId()) {
				return 1;
			} else {

				if (this.nombre.compareTo(odontologoAComparar.getNombre()) < 0) {
					return -1;
				} else if (this.nombre.compareTo(odontologoAComparar.getNombre()) > 0) {
					return 1;
				} else {

					if (this.apellido.compareTo(odontologoAComparar.getApellido()) < 0) {
						return -1;
					} else if (this.apellido.compareTo(odontologoAComparar.getApellido()) > 0) {
						return 1;
					} else {

						if (this.matricula.compareTo(odontologoAComparar.getMatricula()) < 0) {
							return -1;
						} else if (this.matricula.compareTo(odontologoAComparar.getMatricula()) > 0) {
							return 1;
						} else {

							return 0;
						}
					}
				}
			}
		}
		return -1;
	}
}
