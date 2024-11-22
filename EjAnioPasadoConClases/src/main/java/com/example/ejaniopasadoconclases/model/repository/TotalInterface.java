package com.example.ejaniopasadoconclases.model.repository;

import Modelo.ExcepcionArticulo;

public interface TotalInterface {
    float total(int unidades,float precio) throws ExcepcionArticulo;
}
