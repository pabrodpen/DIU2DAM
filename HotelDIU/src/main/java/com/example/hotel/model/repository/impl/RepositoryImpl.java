//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.hotel.model.repository.impl;

import com.example.hotel.model.ExcepcionPersona;
import com.example.hotel.model.ExcepcionReserva;
import com.example.hotel.model.PersonaVO;
import com.example.hotel.model.ReservaVO;
import com.example.hotel.model.repository.Repository;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositoryImpl implements Repository {
    private final ConexionBD conexion = new ConexionBD();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public RepositoryImpl() {
    }

    public ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona {
        this.personas = new ArrayList<>(); // Inicializamos la lista como vacía

        try {
            Connection conn = this.conexion.conectarBD();
            if (conn == null) {
                // Si no se pudo conectar, se muestra el alerta y se devuelve la lista vacía
                mostrarAlertaConexionFallida();
                return this.personas; // Devuelve la lista vacía
            }

            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM personas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni= rs.getString("dni");
                String nom = rs.getString("nombre_completo");
                String direcc = rs.getString("direccion");
                String loc = rs.getString("localidad");
                String provincia = rs.getString("provincia");


                this.persona = new PersonaVO(dni, nom, direcc, loc, provincia );
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
        } catch (SQLException var6) {
            mostrarAlertaConexionFallida();
        }

        return this.personas; // Devuelve la lista, que puede estar vacía si hubo un error
    }

    private void mostrarAlertaConexionFallida() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de conexión");
        alert.setHeaderText(null);
        alert.setContentText("No se ha podido conectar con el servidor.");
        alert.showAndWait();
    }
    public void addPersona(PersonaVO p) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO personas (dni, nombre_completo, direccion, localidad, provincia) VALUES ('" + p.getDni() + "','" + p.getNombre_completo() + "','"+p.getDireccion()+"','"+p.getLocalidad()+"','"+p.getProvincia()+"')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPersona("No se ha podido realizar la operación");
        }
    }

    //IMP!!-->SI EL CODIFGO ES UN STRING-->ENTRE COMILLAS SIMPLES Y %s
    //SI EL CODIGO ES UN INT-->SIN COMILLAS SIMPLES Y %d
    public void deletePersona(String dniPersona) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM personas WHERE dni = '%s'", dniPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(PersonaVO personaVO) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            //%s string y %d entero
            //RESPETAR EÑ ORDEN DE UPDATE(si nomrbe eds el primeri, el primero del update sera persona.getnombre())
            String sql = String.format("UPDATE personas SET nombre_completo = '%s', direccion = '%s', localidad = '%s' , provincia = '%s' WHERE dni = '%s'",personaVO.getNombre_completo(), personaVO.getDireccion() ,personaVO.getLocalidad(), personaVO.getProvincia(),personaVO.getDni());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPersona("No se ha podido realizar la edición");
        }
    }

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas(String dniCliente) throws ExcepcionReserva {
        this.reservas = new ArrayList<>(); // Inicializamos la lista como vacía

        try {
            // Conexión a la base de datos
            Connection conn = this.conexion.conectarBD();
            if (conn == null) {
                // Si no se pudo conectar, se muestra el alerta y se devuelve la lista vacía
                mostrarAlertaConexionFallida();
                return this.reservas; // Devuelve la lista vacía
            }

            // Crear sentencia SQL con filtrado por dni_cliente
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas WHERE dni_cliente = '" + dniCliente + "'";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            // Procesamos los resultados
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                int numHabitaciones = rs.getInt("num_habitaciones");
                String tipoHabitacion = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String regimenHabitacion = rs.getString("regimen_habitacion");
                LocalDateTime horaLlegada = rs.getTimestamp("fecha_llegada").toLocalDateTime();
                LocalDateTime horaSalida = rs.getTimestamp("fecha_salida").toLocalDateTime();
                String dniClienteRes = rs.getString("dni_cliente");

                // Crear el objeto ReservaVO y agregarlo a la lista
                this.reserva = new ReservaVO(codigo, numHabitaciones, tipoHabitacion, fumador, regimenHabitacion, horaLlegada, horaSalida, dniClienteRes);
                this.reservas.add(this.reserva);
            }

            // Desconectar de la base de datos
            this.conexion.desconectarBD(conn);
        } catch (SQLException var6) {
            mostrarAlertaConexionFallida();
        }

        // Devolver la lista de reservas para el cliente
        return this.reservas; // Devuelve la lista que puede estar vacía si hubo un error
    }

    public void addReserva(ReservaVO r) throws ExcepcionReserva {
        Connection conn = null;
        try {
            conn = this.conexion.conectarBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.sentencia="INSERT INTO reservas (codigo, num_habitaciones, tipo_habitacion, fumador, regimen_habitacion, fecha_llegada, fecha_salida, dni_cliente) " +
                "VALUES ('" + r.getCodigo() + "', " +
                r.getNumHabitaciones() + ", '" +
                r.getTipoHabitacion() + "', " +
                r.isEsFumador() + ", '" +
                r.getRegimenHabitacion() + "', '" +
                Timestamp.valueOf(r.getHoraLLegada()) + "', '" +
                Timestamp.valueOf(r.getHoraSalida()) + "', '" +
                r.getDniCliente() + "')";

        try {
            this.stmt.executeUpdate(sentencia);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.conexion.desconectarBD(conn);



            /*
            *    try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO personas (dni, nombre_completo, direccion, localidad, provincia) VALUES ('" + p.getDni() + "','" + p.getNombre_completo() + "','"+p.getDireccion()+"','"+p.getLocalidad()+"','"+p.getProvincia()+"')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPersona("No se ha podido realizar la operación");
        }
            * */

    }



    @Override
    public void deleteReserva(String codReserva) throws ExcepcionReserva {

        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reservas WHERE codigo = '%s'", codReserva);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void editReserva(ReservaVO r) throws ExcepcionReserva {

        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            //%s string y %d entero
            //RESPETAR EÑ ORDEN DE UPDATE(si nomrbe eds el primeri, el primero del update sera persona.getnombre())
            String sql = String.format("UPDATE reservas SET num_habitaciones = '%s', tipo_habitacion = '%s', fumador = '%s' , regimen_habitacion = '%s' , fecha_llegada='%s' , fecha_salida='%s'  WHERE codigo = %d",r.getNumHabitaciones(), r.getTipoHabitacion() ,r.isEsFumador(), r.getRegimenHabitacion(), r.getHoraLLegada(), r.getHoraSalida(), r.getCodigo());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPersona("No se ha podido realizar la edición");
        }
    }


}
