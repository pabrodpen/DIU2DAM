package com.example.hotel.view;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Reserva {
    //String codigo;int numHabitaciones;String tipoHabitacion;boolean esFumador;String regimenHabitacion;

    private StringProperty codigo=new SimpleStringProperty();
    private IntegerProperty numHabitaciones=new SimpleIntegerProperty();
    private StringProperty tipoHabitacion=new SimpleStringProperty();
    private BooleanProperty esFumador=new SimpleBooleanProperty();
    private StringProperty regimenHabitacion=new SimpleStringProperty();
    private StringProperty dniCliente=new SimpleStringProperty();
    private SimpleObjectProperty<LocalDateTime> horaLlegada=new SimpleObjectProperty<LocalDateTime>();
    private SimpleObjectProperty<LocalDateTime> horaSalida=new SimpleObjectProperty<LocalDateTime>();

    public Reserva(String codigo, int numHabitaciones, String tipoHabitacion, boolean esFumador, String regimenHabitacion, LocalDateTime horaLlegada, LocalDateTime horaSalida) {
        this.codigo = new SimpleStringProperty(codigo);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.esFumador = new SimpleBooleanProperty(esFumador);
        this.regimenHabitacion = new SimpleStringProperty(regimenHabitacion);
        this.horaLlegada = new SimpleObjectProperty(horaLlegada);
        this.horaSalida = new SimpleObjectProperty(horaSalida);
    }

    public Reserva(String codigo, int numHabitaciones, String tipoHabitacion, boolean esFumador, String regimenHabitacion,LocalDateTime horaLlegada, LocalDateTime horaSalida, String dniCliente) {
        this.codigo = new SimpleStringProperty(codigo);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.esFumador = new SimpleBooleanProperty(esFumador);
        this.regimenHabitacion = new SimpleStringProperty(regimenHabitacion);
        this.horaLlegada = new SimpleObjectProperty(horaLlegada);
        this.horaSalida = new SimpleObjectProperty(horaSalida);
        this.dniCliente = new SimpleStringProperty(dniCliente);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public int getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public IntegerProperty numHabitacionesProperty() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones.set(numHabitaciones);
    }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }

    public StringProperty tipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion.set(tipoHabitacion);
    }

    public boolean isEsFumador() {
        return esFumador.get();
    }

    public BooleanProperty esFumadorProperty() {
        return esFumador;
    }

    public void setEsFumador(boolean esFumador) {
        this.esFumador.set(esFumador);
    }

    public String getRegimenHabitacion() {
        return regimenHabitacion.get();
    }

    public StringProperty regimenHabitacionProperty() {
        return regimenHabitacion;
    }

    public void setRegimenHabitacion(String regimenHabitacion) {
        this.regimenHabitacion.set(regimenHabitacion);
    }

    public String getDniCliente() {
        return dniCliente.get();
    }

    public StringProperty dniClienteProperty() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }

    public LocalDateTime getHoraLlegada() {
        return horaLlegada.get();
    }

    public SimpleObjectProperty<LocalDateTime> horaLlegadaProperty() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalDateTime horaLlegada) {
        this.horaLlegada.set(horaLlegada);
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida.get();
    }

    public SimpleObjectProperty<LocalDateTime> horaSalidaProperty() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida.set(horaSalida);
    }
}
