package com.example.hotel.model;

import java.time.LocalDateTime;

public class ReservaVO {

    String codigo;
    int numHabitaciones;
    String tipoHabitacion;
    boolean esFumador;
    String regimenHabitacion;


    public ReservaVO(String codigo, int numHabitaciones, String tipoHabitacion, boolean esFumador, String regimenHabitacion) {
        this.codigo = codigo;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.esFumador = esFumador;
        this.regimenHabitacion = regimenHabitacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public boolean isEsFumador() {
        return esFumador;
    }

    public void setEsFumador(boolean esFumador) {
        this.esFumador = esFumador;
    }

    public String getRegimenHabitacion() {
        return regimenHabitacion;
    }

    public void setRegimenHabitacion(String regimenHabitacion) {
        this.regimenHabitacion = regimenHabitacion;
    }
}
