package com.example.appconversor.models;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class ConversorModelo {
    MonedaRepository monedaRepository;
    ArrayList<MonedaVO> lista;
    double conversion;

    public double getConversion() {
        {
            try {
                lista = monedaRepository.ObtenerListaMonedas();

                conversion = lista.get(monedaRepository.lastId()).getMultiplicador();

            } catch (ExcepcionMoneda e) {
                throw new RuntimeException(e);
            }
        }
        return conversion;
    }


}
