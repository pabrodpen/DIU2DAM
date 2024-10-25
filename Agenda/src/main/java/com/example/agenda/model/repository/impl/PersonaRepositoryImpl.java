//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.agenda.model.repository.impl;

import com.example.agenda.model.ExcepcionPersona;
import com.example.agenda.model.PersonaVO;
import com.example.agenda.model.repository.PersonaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonaRepositoryImpl implements PersonaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;

    public PersonaRepositoryImpl() {
    }

    public ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM contactos";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                int c=rs.getInt("codigo");
                String nom = rs.getString("nombre");
                String apell = rs.getString("apellido");
                String direcc = rs.getString("direccion");
                String loc = rs.getString("localidad");
                int cPostal=rs.getInt("codPostal");
                LocalDate f= rs.getDate("fechaNac").toLocalDate();

                this.persona = new PersonaVO(c,nom,apell,direcc,loc,cPostal,f);
                //this.moneda.setCodigo(codigo);
                this.personas.add(this.persona);


            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPersona("No se ha podido realizar la operación");
        }
    }

    public void addPersona(PersonaVO p) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO personas (nombre, apellido, direccion, localidad, codPostal, fechaNac) VALUES ('" + p.getNombre() + "','" + p.getApellido() + "','"+p.getDireccion()+"','"+p.getLocalidad()+"','"+p.getCodPostal()+"','"+p.getFechaNac()+"')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPersona("No se ha podido realizar la operación");
        }
    }

    public void deletePersona(Integer idPersona) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM personas WHERE codigo = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(PersonaVO monedaVO) throws ExcepcionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE personas SET nombre = '%s', apellido = '%s' , direccion = '%s', localidad = '%s' , codPostal = '%s' , fechaNac = '%s' WHERE codigo = %d",persona.getCod(), persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getLocalidad(), persona.getCodPostal(), persona.getFechaNac());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPersona("No se ha podido realizar la edición");
        }
    }



    public int lastId() throws ExcepcionPersona {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM monedas ORDER BY codigo DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("codigo")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExcepcionPersona("No se ha podido realizar la busqueda del ID");
        }
    }
}
