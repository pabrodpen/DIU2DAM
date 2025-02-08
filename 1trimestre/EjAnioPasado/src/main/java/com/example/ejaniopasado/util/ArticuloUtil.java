package com.example.ejaniopasado.util;

import com.example.ejaniopasado.model.ArticuloVO;
import com.example.ejaniopasado.model.repository.impl.ArticuloRepositoryImpl;
import com.example.ejaniopasado.view.Articulo;

import java.util.ArrayList;

public class ArticuloUtil {

    ArticuloRepositoryImpl articuloRepository;
    public void setArticuloRepository(ArticuloRepositoryImpl articuloRepository){
        this.articuloRepository = articuloRepository;
    }

    public Articulo articuloVOtoArticulo(ArticuloVO articuloVO) {
        return new Articulo(articuloVO.getCodigo(),articuloVO.getNombre(),articuloVO.getDescripcion(),articuloVO.getPrecio(),articuloVO.getStock());
    }

    public ArticuloVO articulotoArticuloVO(Articulo articulo) {
        return new ArticuloVO(articulo.getCodigo(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getStock());
    }
}
