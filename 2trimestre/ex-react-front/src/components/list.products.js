import React, { Component } from "react";
import ProductDataService from "../services/product.service";
import { Link } from "react-router-dom";
import ProgressBar from "./progress.bar";

export default class ProductsList extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      products: [],
      currentProduct: null,
      currentIndex: -1,
    };
  }

  componentDidMount() {
    this.retrieveProducts();
  }

  retrieveProducts() {
    ProductDataService.getAll()
      .then(response => {
        this.setState({ products: response.data });
      })
      .catch(e => {
        console.error("Error al obtener productos:", e);
      });
  }

  setActiveProduct(product, index) {
    this.setState({ currentProduct: product, currentIndex: index });
  }

  render() {
    const { products, currentProduct, currentIndex } = this.state;
    const maxProducts = 50;
    const progress = (products.length / maxProducts) * 100;

    return (
      <div className="container mt-4">
        <h2>Lista de Productos</h2>

        <ul className="list-group">
          {products.map((product, index) => (
            <li
              key={index}
              className={`list-group-item ${index === currentIndex ? "active" : ""}`}
              onClick={() => this.setActiveProduct(product, index)}
            >
              {product.name}
            </li>
          ))}
        </ul>

        <div className="col-md-6">
       

        {currentProduct ? (
          <div className="mt-4">
            <h4>Detalles del Producto</h4>


            <div>
                <label>
                  <strong>Nombre:</strong>
                </label>{" "}
                {currentProduct.name}
              </div>
              <div>
                <label>
                  <strong>Stock:</strong>
                </label>{" "}
                {currentProduct.stock}
              </div>
              <div>
                <label>
                  <strong>Precio:</strong>
                </label>{" "}
                {currentProduct.price}
              </div>
              <div>
                <label>
                  <strong>Marca:</strong>
                </label>{" "}
                {currentProduct.brand}
              </div>
                <div>
                    <label>
                    <strong>Activo:</strong>
                    </label>{" "}
                    {currentProduct.active ? "Sí" : "No"}
                </div>
            <Link to={`/products/${currentProduct.id}`} className="btn btn-warning">Editar</Link>

            <div className="mt-3">
              <ProgressBar progress={progress} />
            </div>
          </div>
        ) : (
          <p className="mt-4">Seleccione un producto...</p>
        )}
        </div>
      </div>
    );
  }
}
