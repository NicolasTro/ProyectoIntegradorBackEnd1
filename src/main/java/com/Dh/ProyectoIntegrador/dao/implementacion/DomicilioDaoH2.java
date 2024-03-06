package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.dao.BD;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {

	private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

	private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
	private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM DOMICILIOS WHERE ID=?";
	private static final String SQL_DELETE_BY_ID= "DELETE FROM DOMICILIOS WHERE ID=?";
	private static final String SQL_UPDATE_BY_ID= "UPDATE DOMICILIOS SET CALLE=? NUMERO=? LOCALIDAD=? PROVINCIA=? WHERE ID=?";

	@Override
	public Domicilio guardar(Domicilio domicilio) {
		Connection connection = null;

		try {
			LOGGER.info("Guardando domicilio 🏡...");
			connection = BD.getConnection();
			PreparedStatement psInsert = connection.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
			psInsert.setString(1, domicilio.getCalle());
			psInsert.setInt(2, domicilio.getNumero());
			psInsert.setString(3, domicilio.getLocalidad());
			psInsert.setString(4, domicilio.getProvincia());
			psInsert.execute();
			ResultSet resultSet = psInsert.getGeneratedKeys();
			while (resultSet.next()) {
				domicilio.setId(resultSet.getInt(1));
			}

		} catch (Exception e) {
			LOGGER.warn("Error guardando domicilio 🏡..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				LOGGER.warn("Error guardando domicilio 🏡..." + e.getMessage());
				e.printStackTrace();
			}
		}
		LOGGER.info("Este es el id: " + domicilio.getId());
		return domicilio;
	}

	@Override
	public Domicilio buscarPorId(Integer id) {

		Connection conexion = null;
		Domicilio domicilio = null;
		try {
			LOGGER.info("Buscando domicilio 🔎🏡...");
			conexion = BD.getConnection();
			PreparedStatement psSearchById = conexion.prepareStatement(SQL_SELECT_BY_ID);
			psSearchById.setInt(1, id);
			ResultSet rs = psSearchById.executeQuery();

			while (rs.next()) {
				domicilio = new Domicilio();
				domicilio.setId(rs.getInt(1));
				domicilio.setCalle(rs.getString(2));
				domicilio.setNumero(rs.getInt(3));
				domicilio.setLocalidad(rs.getString(4));
				domicilio.setProvincia(rs.getString(5));
			}

		} catch (Exception e) {
			LOGGER.warn("Error buscando domicilio 🔎🏡..." + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (Exception ex) {
				LOGGER.warn("Error buscando domicilio 🔎🏡..." + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return domicilio;
	}

	@Override
	public void eliminar(Integer id) {

		Connection conexion = null;
		try {
			LOGGER.info("Eliminando domicilio 🚮🏡...");
			conexion = BD.getConnection();
			PreparedStatement psDeleteByID = conexion.prepareStatement(SQL_DELETE_BY_ID);
			psDeleteByID.setInt(1, id);
			psDeleteByID.executeUpdate();

		} catch (Exception e) {
			LOGGER.warn("Error eliminando domicilio 🚮🏡..." + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error eliminando domicilio 🚮🏡..." + e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void actualizar(Domicilio domicilio) {
		Connection conexion = null;
		try {
			LOGGER.info("Actualizando domicilio 👨‍💻🏡...");
			conexion = BD.getConnection();
			PreparedStatement psUpdateById = conexion.prepareStatement(SQL_UPDATE_BY_ID);
			psUpdateById.setString(1, domicilio.getCalle());
			psUpdateById.setInt(2, domicilio.getNumero());
			psUpdateById.setString(3, domicilio.getLocalidad());
			psUpdateById.setString(4, domicilio.getProvincia());
			psUpdateById.setInt(5, domicilio.getId());

			//todo PREGUNTARLE A LA PROFE PARA QUE ES EL EXECUTE LONG UPDATE
			psUpdateById.executeUpdate();

		} catch (Exception e) {
			LOGGER.warn("Error actualizando domicilio 👨‍💻🏡..." + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error actualizando domicilio 👨‍💻🏡..." + e.getMessage());
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<Domicilio> listarTodos() {
		LOGGER.info("Estamos consultando todos los domicilios");
		Connection connection = null;
		List<Domicilio> domicilioList = new ArrayList<>();
		Domicilio domicilio = null;

		try {
			LOGGER.info("Error Listando los domicilios 👨‍💻🏡🏡...");
			connection = BD.getConnection();
			PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				//completamos el domicilio
				domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3),
						rs.getString(4), rs.getString(5));

				//lo guardamos en la lista
				domicilioList.add(domicilio);
			}

		} catch (Exception e) {
			LOGGER.warn("Error Listando los domicilios 👨‍💻🏡🏡..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				LOGGER.warn("Error actualizando domicilio 👨‍💻🏡🏡..." + e.getMessage());
				e.printStackTrace();
			}
		}
		return domicilioList;
	}
}
