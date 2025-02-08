package com.example.ejaniopasado.model.repository;

import com.example.ejaniopasado.model.ArticuloVO;
import com.example.ejaniopasado.model.ExcepcionArticulo;

import java.util.ArrayList;

public interface ArticuloRepository {
    ArrayList<ArticuloVO> ObtenerListaArticulos() throws ExcepcionArticulo;

    void addArticulo(ArticuloVO var1) throws ExcepcionArticulo;

    void deleteArticulo(String var1) throws ExcepcionArticulo;

    void editArticulo(ArticuloVO var1) throws ExcepcionArticulo;

    float total(Integer unidades, float precio) throws ExcepcionArticulo;
}
