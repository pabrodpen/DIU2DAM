package com.example.agenda.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Contacto{
    //NOMBRE,APELLIADO,DIRECC,LOCALIDAD,CODIGO POSTA. FECHA NAC

    public StringProperty nombre,apellido,direccion,localidad;
    public IntegerProperty codPostal;
    public ObjectProperty<LocalDate> fechaNac;

    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //constructor de solo nombre y apellido
    public Contacto(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion =new SimpleStringProperty("direccion");
        this.localidad =new SimpleStringProperty("localidad");
        this.codPostal =new SimpleIntegerProperty(1234);
        this.fechaNac = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000,1,1));
    }

    //constuctor vacio
    public Contacto() {
    }


    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public int getCodPostal() {
        return codPostal.get();
    }

    public IntegerProperty codPostalProperty() {
        return codPostal;
    }

    public LocalDate getFechaNac() {
        return fechaNac.get();
    }

    public ObjectProperty<LocalDate> fechaNacProperty() {
        return fechaNac;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public void setCodPostal(int codPostal) {
        this.codPostal.set(codPostal);
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac.set(fechaNac);
    }
}
