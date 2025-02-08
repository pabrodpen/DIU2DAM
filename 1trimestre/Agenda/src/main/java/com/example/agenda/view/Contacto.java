package com.example.agenda.view;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contacto {
    // Propiedades: nombre, apellido, dirección, localidad, código postal, fecha de nacimiento
    private  IntegerProperty codigo = null;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty direccion;
    private final StringProperty localidad;
    private final IntegerProperty codPostal;
    private final ObjectProperty<LocalDate> fechaNac;

    // Formato para la fecha
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Constructor con solo nombre y apellido
    public Contacto(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty("Dirección");
        this.localidad = new SimpleStringProperty("Localidad");
        this.codPostal = new SimpleIntegerProperty(1234);
        this.fechaNac = new SimpleObjectProperty<>(LocalDate.of(2000, 1, 1));
    }

    public Contacto(int codigo,String nombre, String apellido, String direccion, String localidad, int codPostal, String fechaNac) {
        this.codigo=new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.codPostal = new SimpleIntegerProperty(codPostal);
        this.fechaNac = new SimpleObjectProperty<>(LocalDate.parse(fechaNac));
    }

    // Constructor vacío (inicializa todas las propiedades con valores predeterminados)
    public Contacto() {
        this.codigo=new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.apellido = new SimpleStringProperty("");
        this.direccion = new SimpleStringProperty("");
        this.localidad = new SimpleStringProperty("");
        this.codPostal = new SimpleIntegerProperty(0);
        this.fechaNac = new SimpleObjectProperty<>(LocalDate.now());
    }

    // Getters y setters para cada propiedad


    public int getCodigo() {
        return codigo.get();
    }



    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getDireccion() {
        return direccion.get();
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

    public int getCodPostal() {
        return codPostal.get();
    }

    public IntegerProperty codPostalProperty() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal.set(codPostal);
    }

    public LocalDate getFechaNac() {
        return fechaNac.get();
    }

    public ObjectProperty<LocalDate> fechaNacProperty() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac.set(fechaNac);
    }
}
