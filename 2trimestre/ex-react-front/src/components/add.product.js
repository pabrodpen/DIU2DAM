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
    const { name, value, type, checked } = e.target;
    setProduct({ 
      ...product, 
      [name]: type === "checkbox" ? checked : value 
    });
  };

  const addProduct = () => {
    const data = {
      name: product.name,
      brand: product.brand,
      price: parseFloat(product.price),
      stock: parseInt(product.stock, 10),
      active: product.active
    };
    
    ProductDataService.create(data)
      .then(response => {
        console.log("Producto agregado:", response.data);
        navigate("/products");
      })
      .catch(e => {
        console.error("Error al agregar producto:", e);
      });
  };

  return (
    <div className="container mt-3">
      <h2>Añadir Producto</h2>

      <div className="form-group">
        <label>Nombre</label>
        <input
          type="text"
          className="form-control"
          placeholder="Nombre"
          name="name"
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
          placeholder="Marca"
          name="brand"
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
          name="price"
          value={product.price}
          onChange={onChange}
        />
      </div>    

      <div className="form-group form-check">
        <input
          type="checkbox"
          className="form-check-input"
          name="active"
          checked={product.active}
          onChange={onChange}
        />
        <label className="form-check-label">Activo</label>
      </div>

      <button className="btn btn-primary mt-3" onClick={addProduct}>
        Añadir Producto
      </button>
    </div>
  );
};

export default AddProduct;
