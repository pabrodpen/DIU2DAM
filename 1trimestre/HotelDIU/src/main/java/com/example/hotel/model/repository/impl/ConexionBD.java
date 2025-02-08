package com.example.hotel.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";

    public Connection conectarBD() throws SQLException {
        Connection conn = null;
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar a la base de datos
            conn = DriverManager.getConnection(URL + PARAMETERS, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de MySQL: " + e.getMessage());
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        } catch (SQLException e) {
            manejarSQLException(e);
            throw new SQLException("Error al conectar a la base de datos", e);
        }
        return conn;
    }

    public void desconectarBD(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                manejarSQLException(e);
            }
        }
    }

    private void manejarSQLException(SQLException e) {
        System.err.println("\n--- SQLException capturada ---\n");
        while (e != null) {
            System.err.println("Mensaje:   " + e.getMessage());
            System.err.println("SQLState:  " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e = e.getNextException();
            System.err.println();
        }
    }
}
