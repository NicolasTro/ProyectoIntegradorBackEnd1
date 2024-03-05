package com.Dh.ProyectoIntegrador.model;

import java.sql.Date;

public class Turno {

private Integer id;
private Paciente paciente;
private Odontologo odontologo;
private Date fechaYHora;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Odontologo getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}

	public Date getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}


	public Turno(){

	}

	public Turno(Paciente paciente, Odontologo odontologo, Date fechaYHora) {
		this.paciente = paciente;
		this.odontologo = odontologo;
		this.fechaYHora = fechaYHora;
	}
}
