package com.example.agenda.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    public void ConexionJDBC() {
    }

    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn;
        } catch (SQLException var2) {
            SQLException ex = var2;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                /*
                * Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexion");
            alert.setHeaderText(null); // No encabezado
            alert.setContentText("No se ha podido conectar con el servidor");
            alert.showAndWait();
                * */
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");

            }

            throw new SQLException();
        } catch (Exception var3) {
            throw new SQLException();
        }
    }

    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

    }
}
