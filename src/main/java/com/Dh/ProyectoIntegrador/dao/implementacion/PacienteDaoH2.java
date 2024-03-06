package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.BD;
import com.Dh.ProyectoIntegrador.model.Domicilio;
import com.Dh.ProyectoIntegrador.model.Paciente;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {

	private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);
	private static final String SQL_INSERT_PACIENTE = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
	private static final String SQL_MODIFY_PACIENTE = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID=?";
	private static final String SQL_DELETE_PACIENTE = "DELETE FROM PACIENTES WHERE ID =?";

	private static final String SQL_DELETE_DOMICILIO = "DELETE FROM DOMICILIOS WHERE ID=?";
	private static final String SQL_SEARCH_ID = "SELECT * FROM PACIENTES WHERE ID=?";
	private static final String SQL_SEARCH_ALL = "SELECT * FROM PACIENTES";
	private static final String SQL_SEARCH_DOMICILIO_PACIENTE = "SELECT * FROM DOMICILIOS WHERE ID =?";
	private static final String SQL_SEARCH_DOMICILIO_ID = "SELECT DOMICILIO_ID FROM PACIENTES WHERE ID =?";


	@Override
	public Paciente guardar(Paciente paciente) {

		Connection connection = null;

		try {
			LOGGER.info("Guardando Paciente 💁‍♂️💁‍♀️");
			connection = BD.getConnection();
			PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS);
			psInsert.setString(1, paciente.getNombre());
			psInsert.setString(2, paciente.getApellido());
			psInsert.setString(3, paciente.getDni());
			psInsert.setDate(4, paciente.getFechaIngreso());
			psInsert.setInt(5, paciente.getDomicilio().getId());
			psInsert.execute();

			ResultSet rs = psInsert.getGeneratedKeys();
			while (rs.next()) {
				paciente.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			LOGGER.warn("Error guardando Paciente 💁‍♂️💁‍♀️" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.warn("Error guardando Paciente 💁‍♂️💁‍♀️" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return paciente;
	}

	@Override
	public Paciente buscarPorId(Integer id) {

		Connection conexion = null;
		Paciente pacienteEncontrado = null;
		try {
			LOGGER.info("Buscando Paciente 🔎 💁‍♂️💁‍♀️");
			conexion = BD.getConnection();
			PreparedStatement psSearchID = conexion.prepareStatement(SQL_SEARCH_ID);
			psSearchID.setInt(1, id);
			ResultSet rsSearch = psSearchID.executeQuery();
			if (!rsSearch.wasNull()) {
				pacienteEncontrado = new Paciente();
				Domicilio domicilio = new Domicilio();
				pacienteEncontrado.setDomicilio(domicilio);
				while (rsSearch.next()) {
					pacienteEncontrado.setId(rsSearch.getInt(1));
					pacienteEncontrado.setNombre(rsSearch.getString(2));
					pacienteEncontrado.setApellido(rsSearch.getString(3));
					pacienteEncontrado.setDni(rsSearch.getString(4));
					pacienteEncontrado.setFechaIngreso(rsSearch.getDate(5));
					pacienteEncontrado.getDomicilio().setId(rsSearch.getInt(6));
				}

				psSearchID = conexion.prepareStatement(SQL_SEARCH_DOMICILIO_PACIENTE);
				psSearchID.setInt(1, domicilio.getId());
				rsSearch = psSearchID.executeQuery();
				while (rsSearch.next()) {

					domicilio.setId(rsSearch.getInt(1));
					domicilio.setCalle(rsSearch.getString(2));
					domicilio.setNumero(rsSearch.getInt(3));
					domicilio.setLocalidad(rsSearch.getString(4));
					domicilio.setProvincia(rsSearch.getString(5));
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Error buscando Paciente 💁‍♂️💁‍♀️" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception ex) {
				LOGGER.warn("Error buscando Paciente 💁‍♂️💁‍♀️" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return pacienteEncontrado;
	}

	@Override
	public void eliminar(Integer id) {

		Connection conexion = null;

		try {
			LOGGER.info("Eliminando Paciente 🚮💁‍♂️💁‍♀️");
			conexion = BD.getConnection();

			conexion.setAutoCommit(false);
			Integer id_domicilio = 0;
			PreparedStatement psDelete1 = conexion.prepareStatement(SQL_SEARCH_DOMICILIO_ID);
			psDelete1.setInt(1, id);
			ResultSet rsDomicilioID = psDelete1.executeQuery();
			while (rsDomicilioID.next()) {
				id_domicilio = rsDomicilioID.getInt(1);
			}
			PreparedStatement psDelete2 = conexion.prepareStatement(SQL_DELETE_PACIENTE);
			psDelete2.setInt(1,id);
			psDelete2.executeUpdate();

			

			PreparedStatement psDelete3 = conexion.prepareStatement(SQL_DELETE_DOMICILIO);
			psDelete3.setInt(1, id_domicilio);
			psDelete3.executeUpdate();


			conexion.commit();


			conexion.setAutoCommit(true);

		} catch (Exception e) {
			LOGGER.warn("Error eliminando Paciente 💁‍♂️💁‍♀️");
			try {
				conexion.rollback();
				LOGGER.error("ARREGLANDO TODO "+ e.getMessage());
			} catch (Exception ex) {
				LOGGER.error("NO SE PUDO VOLVER AL INICIO CAGASTE");
				e.printStackTrace();
			}

		}finally{
			try {
				conexion.close();
			} catch (Exception ex) {
				LOGGER.warn("Error eliminando Paciente 💁‍♂️💁‍♀️");

			}
		}
	}

	@Override
	public void actualizar(Paciente paciente) {

		Connection conexion = null;

		try {
			LOGGER.info("Actualizando Paciente 👨‍💻💁‍♂️💁‍♀️");
			conexion = BD.getConnection();
			PreparedStatement psUpdate = conexion.prepareStatement(SQL_MODIFY_PACIENTE);
			psUpdate.setString(1, paciente.getNombre());
			psUpdate.setString(2, paciente.getApellido());
			psUpdate.setString(3, paciente.getDni());
			psUpdate.setDate(4, paciente.getFechaIngreso());
			psUpdate.setInt(5, paciente.getDomicilio().getId());
			psUpdate.executeUpdate();
		} catch (Exception e) {
			LOGGER.warn("Error actualizando Paciente 💁‍♂️💁‍♀️" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				LOGGER.warn("Error actualizando Paciente 💁‍♂️💁‍♀️" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Paciente> listarTodos() {
		Connection conexion = null;
		List<Paciente> listaPacientes = null;
		try {
			LOGGER.info("Listando todos los Pacientes 💁‍♂️💁‍♀️");
			conexion = BD.getConnection();
			PreparedStatement psSearchAll = conexion.prepareStatement(SQL_SEARCH_ALL);
			ResultSet rsSearchAll = psSearchAll.executeQuery();
			if (!rsSearchAll.wasNull()) {
				listaPacientes = new ArrayList();
				//TODO SE PUEDE MEZCLAR METODO BUSCAR PACIENTE CON METODO BUSCAR DOMICILIO
				while (rsSearchAll.next()) {
					Paciente paciente = new Paciente();
					Domicilio domicilio = new Domicilio();
					paciente.setDomicilio(domicilio);
					paciente.setId(rsSearchAll.getInt(1));
					paciente.setNombre(rsSearchAll.getString(2));
					paciente.setApellido(rsSearchAll.getString(3));
					paciente.setDni(rsSearchAll.getString(4));
					paciente.setFechaIngreso(rsSearchAll.getDate(5));
					paciente.getDomicilio().setId(rsSearchAll.getInt(6));

					psSearchAll = conexion.prepareStatement(SQL_SEARCH_DOMICILIO_PACIENTE);
					psSearchAll.setInt(1, domicilio.getId());
					rsSearchAll = psSearchAll.executeQuery();
					while (rsSearchAll.next()) {

						domicilio.setId(rsSearchAll.getInt(1));
						domicilio.setCalle(rsSearchAll.getString(2));
						domicilio.setNumero(rsSearchAll.getInt(3));
						domicilio.setLocalidad(rsSearchAll.getString(4));
						domicilio.setProvincia(rsSearchAll.getString(5));
					}
					listaPacientes.add(paciente);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Error listando Pacientes 💁‍♂️💁‍♀️" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				LOGGER.warn("Error listando Pacientes 💁‍♂️💁‍♀️" + e.getMessage());
				e.printStackTrace();
			}
		}
		return listaPacientes;
	}
}
