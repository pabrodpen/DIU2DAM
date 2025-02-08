package com.example.agenda.model;

public class ExcepcionPersona extends RuntimeException {
  private String mensaje;

  public ExcepcionPersona(String message) {
    this.mensaje=message;
  }



  public ExcepcionPersona() {
  }


  public String imprimirMensaje() {
    return this.mensaje;
  }
}
