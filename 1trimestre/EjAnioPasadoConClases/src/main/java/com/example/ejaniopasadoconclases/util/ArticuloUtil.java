package com.example.ejaniopasadoconclases.util;

import Modelo.ArticuloVO;
import com.example.ejaniopasadoconclases.view.Articulo;

import java.util.ArrayList;

public class ArticuloUtil {
    public Articulo getArticulo(ArticuloVO articuloVO) {
        return new Articulo(articuloVO.codigo, articuloVO.nombre, articuloVO.descripcion, articuloVO.precio, articuloVO.cantidad);
    }

    public ArticuloVO getArticuloVO(Articulo articulo) {
        ArticuloVO articuloVO = new ArticuloVO();
        articuloVO.setCodigo(articulo.getCodigo());
        articuloVO.setNombre(articulo.getNombre());
        articuloVO.setDescripcion(articulo.getDescripcion());
        articuloVO.setPrecio(articulo.getPrecio());
        articuloVO.setCantidad(articulo.getCantidad());

        return articuloVO;
    }

}
