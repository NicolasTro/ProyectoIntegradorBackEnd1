package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.dao.BD;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.model.Odontologo;
import com.Dh.ProyectoIntegrador.model.Paciente;
import com.Dh.ProyectoIntegrador.model.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
	private static final String SQL_INSERT = "INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA_HORA_TURNO) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE TURNOS SET PACIENTE_ID=?, ODONTOLOGO_ID=?, FECHA_HORA_TURNO=?";
	private static final String SQL_DELETE = "DELETE FROM TURNOS WHERE ID=?";
	private static final String SQL_SEARCHID = "SELECT * FROM TURNOS WHERE ID=?";
	private static final String SQL_SEARCH_ALL = "SELECT * FROM TURNOS";


	@Override
	public Turno guardar(Turno turno) {
		Connection conexion = null;
		try {

			conexion = BD.getConnection();
			PreparedStatement psInsert = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			psInsert.setInt(1, turno.getPaciente().getId());
			psInsert.setInt(2, turno.getOdontologo().getId());
			psInsert.setDate(3, turno.getFechaYHora());
			psInsert.execute();

			ResultSet rsInsert = psInsert.getGeneratedKeys();
			while (rsInsert.next()) {
				turno.setId(rsInsert.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return turno;
	}

	@Override
	public Turno buscarPorId(Integer id) {

		Connection conexion = null;
		Turno turnoEncontrado = null;
		try {
			conexion = BD.getConnection();
			PreparedStatement psSearchID = conexion.prepareStatement(SQL_SEARCHID);
			psSearchID.setInt(1, id);
			ResultSet rsSearch = psSearchID.executeQuery();

			if (!rsSearch.wasNull()) {
				turnoEncontrado = new Turno();
				Odontologo odontologo = new Odontologo();
				Paciente paciente = new Paciente();
				turnoEncontrado.setOdontologo(odontologo);
				turnoEncontrado.setPaciente(paciente);
				while (rsSearch.next()) {
					turnoEncontrado.setId(rsSearch.getInt(1));
					turnoEncontrado.getPaciente().setId(rsSearch.getInt(2));
					turnoEncontrado.getOdontologo().setId(rsSearch.getInt(3));
					turnoEncontrado.setFechaYHora(rsSearch.getDate(4));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return turnoEncontrado;
	}

	@Override
	public void eliminar(Integer id) {

		Connection conexion = null;
		try {
			conexion = BD.getConnection();
			PreparedStatement psDelete = conexion.prepareStatement(SQL_DELETE);
			psDelete.setInt(1, id);
			psDelete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actualizar(Turno turno) {

		Connection conexion = null;

		try {
			conexion = BD.getConnection();
			PreparedStatement psUpdate = conexion.prepareStatement(SQL_UPDATE);
			psUpdate.setInt(1, turno.getPaciente().getId());
			psUpdate.setInt(2, turno.getOdontologo().getId());
			psUpdate.setDate(3, turno.getFechaYHora());
			psUpdate.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Turno> listarTodos() {

		Connection conexion = null;
		List<Turno> listaTurnos = null;
		try {

			conexion = BD.getConnection();
			PreparedStatement psSearchAll = conexion.prepareStatement(SQL_SEARCH_ALL);
			ResultSet rsSearchAll = psSearchAll.executeQuery();
			if (!rsSearchAll.wasNull()) {
				listaTurnos = new ArrayList();
				//TODO SE PUEDE MEZCLAR METODO BUSCAR PACIENTE CON METODO BUSCAR DOMICILIO
				while (rsSearchAll.next()) {
					Turno turno = new Turno();
					Paciente paciente = new Paciente();
					Odontologo odontologo = new Odontologo();
					turno.setPaciente(paciente);
					turno.setOdontologo(odontologo);
					turno.getPaciente().setId(rsSearchAll.getInt(1));
					turno.getOdontologo().setId(rsSearchAll.getInt(2));
					turno.setFechaYHora(rsSearchAll.getDate(3));
					listaTurnos.add(turno);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaTurnos;
	}
}
