package com.example.ejaniopasado.model;

import com.example.ejaniopasado.model.repository.ArticuloRepository;
import com.example.ejaniopasado.util.ArticuloUtil;
import com.example.ejaniopasado.view.Articulo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class CatalogoModelo {
    ArticuloRepository articuloRepository;
    ArticuloUtil articuloUtil;


    public void setArticuloRepository(ArticuloRepository a){
        this.articuloRepository=a;
    }
    public void setArticuloUtil(ArticuloUtil articuloUtil){
        this.articuloUtil=articuloUtil;
    }

    IntegerProperty numArticulos=new SimpleIntegerProperty(0);



    public ArrayList<Articulo> getArticulos(){
        ArrayList<ArticuloVO> listaArticulosVO=articuloRepository.ObtenerListaArticulos();
        ArrayList<Articulo> listaArticulos=new ArrayList<>();
        for(ArticuloVO articuloVO:listaArticulosVO){
            Articulo articulo=articuloUtil.articuloVOtoArticulo(articuloVO);
            listaArticulos.add(articulo);
        }
        // Actualizamos el número de artículos después de obtener la lista
        //para que al iniciar ya nos salga el numero actualizado
        setNumContactosProperty(listaArticulos.size());

        return listaArticulos;
    }

    public void addArticuloVOtoBD(Articulo c){
        ArticuloVO articuloVOVO= new ArticuloVO(c.getCodigo(),c.getNombre(),c.getDescripcion(),c.getPrecio(),c.getStock());
        articuloRepository.addArticulo(articuloVOVO);
        //actualizamos el numero de personas por si abrimos varias ventanas
        //setNumContactos(getPersonas().size());
    }

    public void deleteArticuloVOtoBD(Articulo c){
        String cod=c.getCodigo();
        articuloRepository.deleteArticulo(cod);
        //actualizamos el numero de personas por si abrimos varias ventanas
        //setNumContactos(getPersonas().size());
    }

    public float getTotal(Integer unidades,float precio){
        System.out.println(articuloRepository.total(unidades,precio));
        return articuloRepository.total(unidades,precio);
    }

    public IntegerProperty getNumContactosProperty(){
        return numArticulos;
    }

    public void setNumContactosProperty(int n){
        numArticulos.set(n);
    }




}
