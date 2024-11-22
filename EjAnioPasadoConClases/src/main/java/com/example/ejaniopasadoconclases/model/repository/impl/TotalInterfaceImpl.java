package com.example.ejaniopasadoconclases.model.repository.impl;

import Modelo.ExcepcionArticulo;
import com.example.ejaniopasadoconclases.model.repository.TotalInterface;

public class TotalInterfaceImpl implements TotalInterface {
    @Override
    public float total(int unidades, float precio) throws ExcepcionArticulo {
        return unidades * precio;
    }
}
