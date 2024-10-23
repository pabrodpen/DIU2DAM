package com.example.agenda.model;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class PersonaVO {
    int cod;
    String nombre;
    String apellido,direccion,localidad;
    int codPostal;
    LocalDate fechaNac;

    public PersonaVO(int cod, String nombre, String apellido, String direccion, String localidad, int codPostal, LocalDate fechaNac) {
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codPostal = codPostal;
        this.fechaNac = fechaNac;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "PersonaVO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", codPostal=" + codPostal +
                ", fechaNac=" + fechaNac +
                '}';
    }
}
