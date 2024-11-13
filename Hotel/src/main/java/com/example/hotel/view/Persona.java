package com.example.hotel.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
//    String dni,nombre_completo,direccion,localidad,provincia;
    private StringProperty dni;
    private StringProperty nombre_completo;
    private StringProperty direccion;
    private StringProperty localidad;
    private StringProperty provincia;


    public Persona(String dni, String nombre_completo, String direccion, String localidad, String provincia) {
        this.dni =new SimpleStringProperty(dni);
        this.nombre_completo = new SimpleStringProperty(nombre_completo);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public Persona(){

    }



    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getNombre_completo() {
        return nombre_completo.get();
    }

    public StringProperty nombre_completoProperty() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo.set(nombre_completo);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }
}
