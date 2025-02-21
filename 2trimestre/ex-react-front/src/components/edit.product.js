import React, { useState, useEffect } from "react";
import ProductDataService from "../services/product.service";
import { useParams, useNavigate } from "react-router-dom";

const EditProduct = () => {
  const { id } = useParams(); // Obtener el ID del producto desde la URL
  const navigate = useNavigate(); // Para redirigir después de actualizar

  const [product, setProduct] = useState({
    name: "",
    price: 0,
    stock: 0,
    brand: "",
    active: false,
  });

  // Obtener todos los productos y filtrar el correcto
  useEffect(() => {
    ProductDataService.getAll()
      .then((response) => {
        if (response.data && response.data.length > 0) {
          const selectedProduct = response.data.find((p) => p.id === id);
          if (selectedProduct) {
            console.log("Producto encontrado:", selectedProduct);
            setProduct(selectedProduct);
          } else {
            console.error("Producto no encontrado con ID:", id);
          }
        }
      })
      .catch((error) => {
        console.error("Error al obtener productos:", error);
      });
  }, [id]);

  // Manejar cambios en los campos del formulario
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  // Manejar la actualización del producto
  const handleEditProduct = (e) => {
    e.preventDefault();

    ProductDataService.update(id, product)
      .then(() => {
        console.log("Producto actualizado correctamente");
        navigate("/products"); // Redirige a la lista de productos
      })
      .catch((error) => {
        console.error("Error al actualizar producto:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Editar Producto</h2>

      <form onSubmit={handleEditProduct}>
        <div className="form-group">
          <label><strong>Nombre:</strong></label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={product.name}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label><strong>Stock:</strong></label>
          <input
            type="number"
            className="form-control"
            name="stock"
            value={product.stock}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label><strong>Precio:</strong></label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={product.price}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label><strong>Marca:</strong></label>
          <input
            type="text"
            className="form-control"
            name="brand"
            value={product.brand}
            onChange={handleChange}
          />
        </div>

        <div className="form-group form-check mt-3">
          <input
            type="checkbox"
            className="form-check-input"
            name="active"
            checked={product.active}
            onChange={handleChange}
          />
          <label className="form-check-label">Activo</label>
        </div>

        <button type="submit" className="btn btn-success mt-3">
          Actualizar Producto
        </button>
      </form>
    </div>
  );
};

export default EditProduct;
