import React, { useState, useEffect, forwardRef, useImperativeHandle } from "react";
import ProductDataService from "../services/product.service";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from "react-router-dom";


const ProductsList = forwardRef((props, ref) => {
const { id } = useParams();
  const [currentProduct, setCurrentTutorial] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);


  const [product, setProduct] = useState({
    name: '',
    stock: 0,
    price: 0,
    brand: 0,
    acive: false
  });

  useEffect(() => {
    console.log("ID recibido en TutorialsList:", id); 
    retrieveTutorialsFromPerson();
  }, []);

  const retrievePersonFromID = async () => {
    const productsresponse = await ProductDataService.getAll();
    const allproducts = productsresponse.data || [];
    
      

      // Filtrar productos que coincidan con el ID del producto seleccionado
      const producto_editar = allproducts.filter(product =>
          product.id.includes(id)
      );

      setProduct(producto_editar);
    

    
  };


 
  const handleEditProduct = (e) => {
    e.preventDefault();
    ProductDataService.update(id, person).then(() => {
      console.log("Contacto actualizado correctamente");
      navigate("/persons"); // Redirigir a la lista de contactos
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Nombre"
            name="nombre"
            value={product.name}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Stock"
            name="stock"
            value={product.stock}
            onChange={handleChange}
          />
          <input
            type="text"
            className="form-control"
            placeholder="Precio"
            name="calle"
            value={product.price}
            onChange={handleChange}
          />
            <input
              type="checkbox"
              checked={product.acive}
              onChange={handleChange}
            />
            Published

          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={handleEditProduct}
            >
              Actualizar producto
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditProduct;