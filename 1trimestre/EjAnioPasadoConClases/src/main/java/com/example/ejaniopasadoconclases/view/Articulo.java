package com.example.ejaniopasadoconclases.view;

import javafx.beans.property.*;

public class Articulo {
    /*
    *  public int cantidad;
    public int codigo;
    public String descripcion;
    public String nombre;
    public float precio;
    * */

    IntegerProperty codigo = new SimpleIntegerProperty();
    StringProperty nombre = new SimpleStringProperty();
    StringProperty descripcion = new SimpleStringProperty();
    DoubleProperty precio = new SimpleDoubleProperty();
    IntegerProperty cantidad=new SimpleIntegerProperty();

    public Articulo(int codigo, String nombre, String descripcion, double precio, int cantidad) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }


    public Articulo() {
        this.codigo = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.descripcion = new SimpleStringProperty("");
        this.precio = new SimpleDoubleProperty(0);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
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

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public float getPrecio() {
        return (float) precio.get();
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }
}
