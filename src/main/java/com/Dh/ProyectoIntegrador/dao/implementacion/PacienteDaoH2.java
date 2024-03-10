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
	private static final String SQL_UPDATE_PACIENTE = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID=? WHERE ID=?";
	private static final String SQL_DELETE_PACIENTE = "DELETE FROM PACIENTES WHERE ID =?";
	private static final String SQL_SEARCH_ID = "SELECT * FROM PACIENTES WHERE ID=?";
	private static final String SQL_SEARCH_ALL = "SELECT * FROM PACIENTES";


	@Override
	public Paciente guardar(Paciente paciente) {

		Connection conexion = null;

		try {
			LOGGER.info("Guardando Paciente 💁‍♂️💁‍♀️");
			conexion = BD.getConnection();


			DomicilioDaoH2 persistenciaDomicilio = new DomicilioDaoH2();
			paciente.setDomicilio(persistenciaDomicilio.guardar(paciente.getDomicilio()));
			PreparedStatement psInsert = conexion.prepareStatement(SQL_INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS);
			conexion.setAutoCommit(false);
			psInsert.setString(1, paciente.getNombre());
			psInsert.setString(2, paciente.getApellido());
			psInsert.setString(3, paciente.getDni());
			psInsert.setDate(4, paciente.getFechaIngreso());
			psInsert.setInt(5, paciente.getDomicilio().getId());
			psInsert.executeUpdate();
			ResultSet rs = psInsert.getGeneratedKeys();

			if (!rs.wasNull()) {
				while (rs.next()) {
					paciente.setId(rs.getInt(1));
				}
			}

			conexion.commit();
			conexion.setAutoCommit(true);

		} catch (Exception e) {
			LOGGER.warn("Error guardando Paciente 💁‍♂️💁‍♀️" + e.getMessage());

			try {
				conexion.rollback();
				LOGGER.info("Reestableciendo los registros originales (Paciente guardar) 💁‍♂️💁‍♀️");
			} catch (Exception ex) {
				LOGGER.warn("Error al reestablecer los registros originales (Paciente guardar) 💁‍♂️💁‍♀️" + e.getMessage());
			}

			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception ex) {
				LOGGER.warn("Error cerrando conexion de (guardar Paciente)💁‍♂️💁‍♀️" + ex.getMessage());
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
			while (rsSearch.next()) {

				pacienteEncontrado = new Paciente();
				Domicilio domicilio = new Domicilio();
				pacienteEncontrado.setDomicilio(domicilio);
				pacienteEncontrado.setId(rsSearch.getInt(1));
				pacienteEncontrado.setNombre(rsSearch.getString(2));
				pacienteEncontrado.setApellido(rsSearch.getString(3));
				pacienteEncontrado.setDni(rsSearch.getString(4));
				pacienteEncontrado.setFechaIngreso(rsSearch.getDate(5));
				pacienteEncontrado.getDomicilio().setId(rsSearch.getInt(6));

				DomicilioDaoH2 persistenciaDomicilio = new DomicilioDaoH2();
				pacienteEncontrado.setDomicilio(persistenciaDomicilio.buscarPorId(domicilio.getId()));

			}

		} catch (Exception e) {
			LOGGER.warn("Error buscando Paciente 💁‍♂️💁‍♀️" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception ex) {
				LOGGER.warn("Error cerrando conexion de (Buscar pacienteID) 💁‍♂️💁‍♀️" + ex.getMessage());
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
			Paciente pacienteEncontrado = this.buscarPorId(id);
			DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
			Integer id_domicilio = pacienteEncontrado.getDomicilio().getId();
			domicilioDaoH2.eliminar(id_domicilio);
			PreparedStatement psDelete = conexion.prepareStatement(SQL_DELETE_PACIENTE);
			psDelete.setInt(1, id);
			psDelete.executeUpdate();
			conexion.commit();

			conexion.setAutoCommit(true);

		} catch (Exception e) {
			LOGGER.warn("Error eliminando Paciente 💁‍♂️💁‍♀️");
			try {
				conexion.rollback();
				LOGGER.info("Reestableciendo los registros originales (Paciente eliminar)");
			} catch (Exception ex) {
				LOGGER.error("Error al reestablecer los registros originales (Paciente eliminar)" + e.getMessage());
				e.printStackTrace();
			}
		} finally {
			try {
				conexion.close();
			} catch (Exception ex) {
				LOGGER.warn("Error cerrando conexion de (eliminar Paciente)💁‍♂️💁‍♀️");
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void actualizar(Paciente paciente) {

		Connection conexion = null;

		try {
			LOGGER.info("Actualizando Paciente 👨‍💻💁‍♂️💁‍♀️");
			conexion = BD.getConnection();
			PreparedStatement psUpdate = conexion.prepareStatement(SQL_UPDATE_PACIENTE);

			conexion.setAutoCommit(false);
			psUpdate.setString(1, paciente.getNombre());
			psUpdate.setString(2, paciente.getApellido());
			psUpdate.setString(3, paciente.getDni());
			psUpdate.setDate(4, paciente.getFechaIngreso());
			psUpdate.setInt(5, paciente.getDomicilio().getId());
			psUpdate.setInt(6, paciente.getId());

			psUpdate.executeUpdate();

			conexion.commit();

			conexion.setAutoCommit(true);

			DomicilioDaoH2 persistenciaDomicilio = new DomicilioDaoH2();
			persistenciaDomicilio.actualizar(paciente.getDomicilio());

		} catch (Exception e) {
			LOGGER.warn("Error actualizando Paciente 💁‍♂️💁‍♀️" + e.getMessage());

			try {
				conexion.rollback();
				LOGGER.warn("Reestableciendo los registros originales en la base de datos (Paciente actualizar)");
			} catch (Exception ex) {
				LOGGER.warn("No se pudo reestablecer los registros originales en la base de datos (Paciente) " + ex.getMessage());
			}
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (Exception e) {
				LOGGER.warn("Error cerrando la conexion de (actualizar Paciente )💁‍♂️💁‍♀️" + e.getMessage());
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
				DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

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
					paciente.setDomicilio(domicilioDaoH2.buscarPorId(paciente.getDomicilio().getId()));

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
				LOGGER.warn("Error cerrando la conexion de (listando Pacientes) 💁‍♂️💁‍♀️" + e.getMessage());
				e.printStackTrace();
			}
		}
		return listaPacientes;
	}
}
