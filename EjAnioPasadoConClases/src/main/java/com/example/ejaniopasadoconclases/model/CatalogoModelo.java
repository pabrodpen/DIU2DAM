package com.example.ejaniopasadoconclases.model;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import com.example.ejaniopasadoconclases.model.repository.TotalInterface;
import com.example.ejaniopasadoconclases.model.repository.impl.TotalInterfaceImpl;
import com.example.ejaniopasadoconclases.util.ArticuloUtil;
import com.example.ejaniopasadoconclases.view.Articulo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class CatalogoModelo {

    ArticuloRepository articuloRepository;
    ArticuloUtil articuloUtil;
    TotalInterface totalInterface;
    IntegerProperty numArticulos=new SimpleIntegerProperty(0);
    public void setArticuloRepository(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public void setArticuloUtil(ArticuloUtil articuloUtil) {
        this.articuloUtil = articuloUtil;
    }

    public void setTotalInterface(TotalInterface totalInterface) {
        this.totalInterface = totalInterface;
    }

    public ArrayList<Articulo> getListaArticulos(){
        ArrayList<Articulo> articulos = new ArrayList<>();
        try {
            ArrayList<ArticuloVO> listaArticulosVO=articuloRepository.obternerListaArticulos();
            for(ArticuloVO articuloVO:listaArticulosVO){
                Articulo articulo=articuloUtil.getArticulo(articuloVO);
                articulos.add(articulo);
            }
            return articulos;
        } catch (ExcepcionArticulo e) {
            throw new RuntimeException(e);
        }


    }

    public void addArticuloVOtoBD(Articulo articulo){
        ArticuloVO articuloVO=articuloUtil.getArticuloVO(articulo);
        try {
            articuloRepository.addArticulo(articuloVO);
        } catch (ExcepcionArticulo e) {
            throw new RuntimeException(e);
        }

    }

    public float getTotal(Integer unidades,float precio){
        try {
           return totalInterface.total(unidades,precio);
        } catch (ExcepcionArticulo e) {
            throw new RuntimeException(e);
        }
    }

    public IntegerProperty getNumContactosProperty(){
        return numArticulos;
    }

    public void setNumContactosProperty(int n){
        numArticulos.set(n);
    }


}
