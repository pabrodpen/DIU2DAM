package com.example.hotel.model;

import java.time.LocalDateTime;

public class ReservaVO {

    String codigo;
    int numHabitaciones;
    String tipoHabitacion;
    boolean esFumador;
    String regimenHabitacion;
    LocalDateTime horaLLegada,horaSalida;
    String dniCliente;


    public ReservaVO(String codigo, int numHabitaciones, String tipoHabitacion, boolean esFumador, String regimenHabitacion, LocalDateTime horaLLegada, LocalDateTime horaSalida, String dniCliente) {
        this.codigo = codigo;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.esFumador = esFumador;
        this.regimenHabitacion = regimenHabitacion;
        this.horaLLegada = horaLLegada;
        this.horaSalida = horaSalida;
        this.dniCliente = dniCliente;
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

    public LocalDateTime getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(LocalDateTime horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }
}
