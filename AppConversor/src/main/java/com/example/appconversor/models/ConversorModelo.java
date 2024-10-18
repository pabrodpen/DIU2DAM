package com.example.appconversor.models;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;

import java.util.ArrayList;

public class ConversorModelo {
    private MonedaRepository monedaRepository;
    ArrayList<MonedaVO> lista;
    double conversion;

    public void setMonedaRepository(MonedaRepository m){
        this.monedaRepository=m;
    }
    public double getConversion() {
        {
            try {
                lista = monedaRepository.ObtenerListaMonedas();

                conversion = lista.get(lista.size()-1).getMultiplicador();

            } catch (ExcepcionMoneda e) {
                throw new RuntimeException(e);
            }
        }
        return conversion;
    }


}
