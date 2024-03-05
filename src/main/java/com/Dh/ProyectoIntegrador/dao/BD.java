package com.Dh.ProyectoIntegrador.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

	private static final String SQL_DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
			"DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
			" CALLE VARCHAR(100) NOT NULL," +
			" NUMERO INT NOT NULL," +
			" LOCALIDAD VARCHAR(100) NOT NULL," +
			" PROVINCIA VARCHAR(100) NOT NULL" +
			")";

	private static final String SQL_DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS " +
			"PACIENTES; CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
			" NOMBRE VARCHAR(100) NOT NULL," +
			" APELLIDO VARCHAR(100)  NOT NULL," +
			" DNI VARCHAR(100) NOT NULL ," +
			" FECHA_INGRESO DATE NOT NULL," +
			" DOMICILIO_ID INT NOT NULL)";

	private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS " +
			" ODONTOLOGOS;" + "CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, " +
			" NOMBRE VARCHAR(100) NOT NULL," +
			" APELLIDO VARCHAR(100) NOT NULL," +
			" MATRICULA VARCHAR(100) NOT NULL)";

	private static final String SQL_DROP_CREATE_TURNOS = "DROP TABLE IF EXISTS TURNOS; " + "CREATE TABLE TURNOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
			" PACIENTE_ID INT, ODONTOLOGO_ID INT, FECHA_HORA_TURNO DATE)";

	public static Connection getConnection() throws Exception {
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection("jdbc:h2:./BDPI",
				"sa", "sa");
	}

	public static void crearTablas() {
		Connection connection = null;

		try {

			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute(SQL_DROP_CREATE_PACIENTES);
			statement.execute(SQL_DROP_CREATE_DOMICILIOS);
			statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
			statement.execute(SQL_DROP_CREATE_TURNOS);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
