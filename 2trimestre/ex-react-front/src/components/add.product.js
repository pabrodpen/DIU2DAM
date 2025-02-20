import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ProductDataService from "../services/product.service";

const AddProduct = () => {
  const [product, setProduct] = useState({
    stock: 0,
    name: '',
    brand: '',
    price: '',
    active: false
  });

  const navigate = useNavigate();

  const onChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };



  const addProduct = () => {
    const data = {
      name: product.name,
      brand: product.brand,
      price: product.price,
      stock: parseInt(product.stock, 10),
      active: product.active
    };
    
  
    ProductDataService.create(data)
      .then(response => {
        console.log("Respuesta del backend:", response.data);

        setProduct({
                id: response.data.id,
                name: response.data.name,
                brand: response.data.brand,
                price: response.data.price,
                stock: response.data.stock,
                active: response.data.active
                });
                navigate("/products"); // Redirigir a la lista de contactos
            })
            .catch(e => {
                console.error("Error al agregar producto:", e);
            });
        };

       

  return (
    <div className="container mt-3">
      <h4>Añadir Producto</h4>
      <div className="form-group">
        <label>Nombre</label>
        <input
          type="text"
          className="form-control"
          placeholder="Nombre"
          name="nombre"
          value={product.name}
          onChange={onChange}
        />
      </div>
      
      <div className="form-group">
        <label>Stock</label>
        <input
          type="text"
          className="form-control"
          placeholder="Stock"
          name="stock"
          value={product.stock}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Marca</label>
        <input
          type="text"
          className="form-control"
          placeholder="Dirección"
          name="calle"
          value={product.brand}
          onChange={onChange}
        />
      </div>

      <div className="form-group">
        <label>Precio</label>
        <input
          type="text"
          className="form-control"
          placeholder="Precio"
          name="precio"
          value={product.price}
          onChange={onChange}
        />
      </div>    
      <div className="form-group">
        <label>Activo</label>
        <input
          type="checkbox"
          className="form-control"
          name="activo"
          value={product.active}
          onChange={onChange}
        />
      </div>

        <button className="btn btn-primary mt-3" onClick={addProduct}>
        Añadir Producto
      </button>
    </div>
  );
};

     

export default AddProduct;