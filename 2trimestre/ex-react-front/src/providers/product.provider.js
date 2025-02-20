import React, { createContext, useState, useEffect } from "react";
import ProductDataService from "../services/product.service";

export const ProductsContext = createContext();

const ProductsProvider = ({ children }) => {
  const [numeroProducts, setNumeroProductos] = useState(0);
  const numeroMaximo = 50; // Definimos el máximo

  // Cargar el número de productos desde la API al inicio
  useEffect(() => {
    ProductDataService.getAll()
      .then(response => {
        setNumeroProductos(response.data.length);
      })
      .catch(e => {
        console.error("Error al obtener productos:", e);
      });
  }, []);

  return (
    <ProductsContext.Provider value={{ numeroProducts, setNumeroProductos, numeroMaximo }}>
      {children}
    </ProductsContext.Provider>
  );
};

export default ProductsProvider;
