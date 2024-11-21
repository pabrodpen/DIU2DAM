package com.example.ejaniopasado.model.repository.impl;

import com.example.ejaniopasado.model.ArticuloVO;
import com.example.ejaniopasado.model.ExcepcionArticulo;
import com.example.ejaniopasado.model.repository.ArticuloRepository;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArticuloRepositoryImpl implements ArticuloRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ArticuloVO> articulosVO;
    private ArticuloVO articuloVO;

    @Override
    public ArrayList<ArticuloVO> ObtenerListaArticulos() throws ExcepcionArticulo {
        this.articulosVO = new ArrayList<>(); // Inicializamos la lista como vacía

        try {
            Connection conn = this.conexion.conectarBD();
            if (conn == null) {
                // Si no se pudo conectar, se muestra el alerta y se devuelve la lista vacía
                mostrarAlertaConexionFallida();
                return this.articulosVO; // Devuelve la lista vacía
            }

            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM articulos";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String c = rs.getString("codigo");
                String nom = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                //LocalDate f = rs.getDate("fechaNac").toLocalDate();

                this.articuloVO = new ArticuloVO(c,nom,descripcion,precio,stock);
                this.articulosVO.add(this.articuloVO);
            }

            this.conexion.desconectarBD(conn);
        } catch (SQLException var6) {
            mostrarAlertaConexionFallida();
        }

        return this.articulosVO;
    }

    private void mostrarAlertaConexionFallida() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de conexión");
        alert.setHeaderText(null);
        alert.setContentText("No se ha podido conectar con el servidor.");
        alert.showAndWait();
    }

    @Override
    public void addArticulo(ArticuloVO var1) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO articulos (codigo, nombre, descripcion, precio, stock) VALUES ('" + var1.getCodigo() + "','" + var1.getNombre() + "','"+var1.getDescripcion()+"','"+var1.getPrecio()+"','"+var1.getStock()+"')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deleteArticulo(String var1) throws ExcepcionArticulo {

        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM articulos WHERE codigo = '%s'", var1);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionArticulo("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void editArticulo(ArticuloVO var1) throws ExcepcionArticulo {

        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            //%s string y %d entero
            //RESPETAR EÑ ORDEN DE UPDATE(si nomrbe eds el primeri, el primero del update sera persona.getnombre())
            String sql = String.format("UPDATE contactos SET nombre = '%s', descripcion = '%s' , precio = %d, stock = %d WHERE codigo = '%s'",var1.getNombre(), var1.getDescripcion() ,var1.getPrecio(), var1.getStock(), var1.getCodigo());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionArticulo("No se ha podido realizar la edición");
        }
    }

    @Override
    public float total(Integer unidades, float precio) throws ExcepcionArticulo {
        return unidades*precio;
    }
}
