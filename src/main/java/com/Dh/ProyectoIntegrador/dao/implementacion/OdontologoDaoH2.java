package com.Dh.ProyectoIntegrador.dao.implementacion;

import com.Dh.ProyectoIntegrador.Excepciones.OdontologoException;
import com.Dh.ProyectoIntegrador.dao.IDao;
import com.Dh.ProyectoIntegrador.dao.BD;
import com.Dh.ProyectoIntegrador.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoDaoH2 implements IDao<Odontologo> {
	private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
	private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)";
	private static final String SQL_UPDATE_ODONTOLOGO = "UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID=?";
	private static final String SQL_DELETE_ODONTOLOGO = "DELETE FROM ODONTOLOGOS WHERE ID=?";
	private static final String SQL_SEARCH_ID = "SELECT * FROM ODONTOLOGOS WHERE ID=?";
	private static final String SQL_SEARCH_ALL = "SELECT * FROM ODONTOLOGOS";

	@Override
	public Odontologo guardar(Odontologo odontologo) {

		Connection connection = null;
		try {
			LOGGER.info("Guardando odontologos 👨‍⚕️...");
			connection = BD.getConnection();
			PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
			psInsert.setString(1, odontologo.getNombre());
			psInsert.setString(2, odontologo.getApellido());
			psInsert.setString(3, odontologo.getMatricula());
			psInsert.execute();

			ResultSet rs = psInsert.getGeneratedKeys();
			while (rs.next()) {
				odontologo.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			LOGGER.warn("Guardando odontologos 👨‍⚕️..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.warn("Guardando odontologos 👨‍⚕️..." + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return odontologo;
	}

	@Override
	public Odontologo buscarPorId(Integer id) {
		Connection conexion = null;
		Odontologo odontologo = null;
		try {
			LOGGER.info("Buscando odontologo 👨‍⚕️...");
			conexion = BD.getConnection();
			PreparedStatement psSearchByID = conexion.prepareStatement(SQL_SEARCH_ID);
			psSearchByID.setInt(1, id);
			ResultSet rs = psSearchByID.executeQuery();

			while (rs.next()) {
				odontologo = new Odontologo();
				odontologo.setId(rs.getInt(1));
				odontologo.setNombre(rs.getString(2));
				odontologo.setApellido(rs.getString(3));
				odontologo.setMatricula(rs.getString(4));
			}
		} catch (Exception e) {
			LOGGER.warn("Error al buscar odontologo 👨‍⚕️..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error al buscar odontologo 👨‍⚕️..." + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return odontologo;
	}

	@Override
	public void eliminar(Integer id) {

		Connection conexion = null;
		try {
			LOGGER.info("Eliminando odontologo 👨‍⚕️...");
			conexion = BD.getConnection();
			PreparedStatement psDeleteByID = conexion.prepareStatement(SQL_DELETE_ODONTOLOGO);
			psDeleteByID.setInt(1, id);
			psDeleteByID.executeUpdate();

		} catch (Exception e) {
			LOGGER.warn("Error eliminando odontologos 👨‍⚕️..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error eliminando odontologo 👨‍⚕️..." + e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void actualizar(Odontologo odontologo) throws OdontologoException {
		Connection conexion = null;
		try {
			LOGGER.info("Actualizando odontologo 👨‍⚕️...");
			conexion = BD.getConnection();
			PreparedStatement psUpdateById = conexion.prepareStatement(SQL_UPDATE_ODONTOLOGO);
			psUpdateById.setString(1, odontologo.getNombre());
			psUpdateById.setString(2, odontologo.getApellido());
			psUpdateById.setString(3, odontologo.getMatricula());
			psUpdateById.setInt(4, odontologo.getId());
			double a = 4 / 0;
			psUpdateById.executeUpdate();

		} catch (Exception e) {
			LOGGER.warn("Error al actualizar odontologo 👨‍⚕️..." + e.getMessage());
			throw new OdontologoException("ERROR de actualizacion");
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error al actualizar odontologo 👨‍⚕️..." + e.getMessage());

			}
		}
	}

	@Override
	public List<Odontologo> listarTodos() {
		Connection conexion = null;
		List<Odontologo> listaOdontologos = null;
		Odontologo odontologo = null;
		try {
			LOGGER.info("Consultando todos los odontologos 👨‍⚕️...");
			conexion = BD.getConnection();
			PreparedStatement psUpdateById = conexion.prepareStatement(SQL_SEARCH_ALL);
			ResultSet rs = psUpdateById.executeQuery();
			if (!rs.wasNull()) {
				listaOdontologos = new ArrayList<>();
				while (rs.next()) {
					odontologo = new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

					listaOdontologos.add(odontologo);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Error listando odontologos 👨‍⚕️..." + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				LOGGER.warn("Error listando odontologos 👨‍⚕️..." + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return listaOdontologos;
	}
}
