package com.Dh.ProyectoIntegrador.model;

public class Odontologo {


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
}
