package com.Dh.ProyectoIntegrador.model;

public class Odontologo implements Comparable {


	private Integer id;
	private String nombre;

	private String apellido;
	private String matricula;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Odontologo() {
	}

	public Odontologo(String nombre, String apellido, String matricula) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.matricula = matricula;
	}

	public Odontologo(Integer id, String nombre, String apellido, String matricula) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.matricula = matricula;
	}

	@Override
	public int compareTo(Object object) {
		if (object instanceof Odontologo) {
			Odontologo odontologoAComparar = (Odontologo) object;
			if (this.id < (odontologoAComparar.getId())) {
				return -1;
			} else if (this.id > odontologoAComparar.getId()) {
				return 1;
			} else {
				// Si los ids son iguales, comparamos por nombre
				if (this.nombre.compareTo(odontologoAComparar.getNombre()) < 0) {
					return -1;
				} else if (this.nombre.compareTo(odontologoAComparar.getNombre()) > 0) {
					return 1;
				} else {
					// Si los nombres son iguales, comparamos por apellido
					if (this.apellido.compareTo(odontologoAComparar.getApellido()) < 0) {
						return -1;
					} else if (this.apellido.compareTo(odontologoAComparar.getApellido()) > 0) {
						return 1;
					} else {
						// Si los apellidos son iguales, comparamos por matricula
						if (this.matricula.compareTo(odontologoAComparar.getMatricula()) < 0) {
							return -1;
						} else if (this.matricula.compareTo(odontologoAComparar.getMatricula()) > 0) {
							return 1;
						} else {
							// Si todos los atributos son iguales, retornamos 0
							return 0;
						}
					}
				}
			}

		}
		return -1;
	}


}
