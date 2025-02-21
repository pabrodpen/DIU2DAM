import React, { Component } from "react";
import ProductDataService from "../services/product.service";
import { Link } from "react-router-dom";
import ProgressBar from "./progress.bar"; // Importamos el componente de ProgressBar

export default class ProductsList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      products: [],
      currentProduct: null,
      currentIndex: -1,
      unitsToBuy: 0, // Estado para el número de unidades a comprar
    };
  }

  componentDidMount = () => {
    this.retrieveProducts();
  };

  retrieveProducts = () => {
    ProductDataService.getAll()
      .then((response) => {
        this.setState({ products: response.data });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  refreshList = () => {
    this.retrieveProducts();
    this.setState({
      currentProduct: null,
      currentIndex: -1,
      unitsToBuy: 0,
    });
  };

  setProduct = (product, index) => {
    this.setState({
      currentProduct: product,
      currentIndex: index,
      unitsToBuy: 0, // Reset al cambiar de producto
    });
  };

  handleUnitsChange = (event) => {
    const value = parseInt(event.target.value, 10) || 0; // Convertir a número
    this.setState({ unitsToBuy: value });
  };

  totalPrice = () => {
    const { currentProduct, unitsToBuy } = this.state;
    if (!currentProduct) return 0; 
    return currentProduct.price * unitsToBuy;
  };

  restarStock = () => {
    const { currentProduct, unitsToBuy } = this.state;
    const units = parseInt(unitsToBuy, 10);

    {/*if (!currentProduct || units <= 0 || units > currentProduct.stock) {
      alert("Cantidad inválida. Revisa el stock disponible.");
      return;
    }*/}

    // Restar stock
    const updatedProduct = { ...currentProduct, stock: currentProduct.stock - units };
    this.setState({ currentProduct: updatedProduct });

    // Actualizar en el backend
    ProductDataService.update(updatedProduct.id, updatedProduct)
      .then(() => {
        console.log("Stock actualizado correctamente.");
        this.retrieveProducts(); // Recargar la lista
      })
      .catch((error) => {
        console.error("Error al actualizar el stock:", error);
      });
  };

  render() {
    const { products, currentProduct, currentIndex, unitsToBuy } = this.state;
    const totalProducts = products.length;
    const maxProducts = 50;
    const progress = (totalProducts / maxProducts) * 100;

    return (
      <div className="container mt-4">
        <h2 className="text-center mb-4">Lista de Productos</h2>

        <div className="row">
          {/* Lista de Productos */}
          <div className="col-md-6">
  <table border="0" className="table table-hover">
    <thead className="table-dark">
      <tr>
        <th scope="col">#</th>
        <th scope="col">Nombre</th>
      </tr>
    </thead>
    <tbody>
      {products &&
        products.map((product, index) => (
          <tr
            key={index}
            className={index === currentIndex ? "table-primary" : ""}
            onClick={() => this.setProduct(product, index)}
            style={{ cursor: "pointer" }}
          >
            <td>{index + 1}</td>
            <td>{product.name}</td>
          </tr>
              ))}
          </tbody>
        </table>

        {/* Barra de progreso debajo de la tabla */}
        <ProgressBar progress={progress} />
      </div>


          {/* Detalles del Producto */}
          <div className="col-md-6">
            {currentProduct ? (
              <div className="card p-3">
                <h4 className="text-center">Detalles del Producto</h4>
                <div className="mb-2">
                  <label className="fw-bold">Nombre:</label>
                  <p>{currentProduct.name}</p>
                </div>
                <div className="mb-2">
                  <label className="fw-bold">Stock:</label>
                  <p>{currentProduct.stock}</p>
                </div>
                <div className="mb-2">
                  <label className="fw-bold">Precio:</label>
                  <p>{currentProduct.price} €</p>
                </div>
                <div className="mb-2">
                  <label className="fw-bold">Marca:</label>
                  <p>{currentProduct.brand}</p>
                </div>
                <div className="mb-2">
                  <label className="fw-bold">Activo:</label>
                  <p>{currentProduct.active ? "Sí" : "No"}</p>
                </div>

                {/* Input para unidades a comprar */}
                <div className="mb-2">
                  <label className="fw-bold">Unidades a comprar:</label>
                  <input
                    type="text"
                    className="form-control"
                    value={unitsToBuy}
                    onChange={this.handleUnitsChange}
                    min="0"
                    max={currentProduct.stock}
                  />
                </div>

                {/* Precio total */}
                <div className="mb-2">
                  <label className="fw-bold">Total:</label>
                  <p>{this.totalPrice()} €</p>
                </div>

                {/* Botón Comprar */}
                <button className="btn btn-success mt-3" onClick={this.restarStock}>
                  Comprar
                </button>

                {/* Botón Actualizar */}
                <Link to={`/edit/${currentProduct.id}`}>
                  <button className="btn btn-primary mt-3">Actualizar</button>
                </Link>
              </div>
            ) : (
              <div className="text-center">
                <p>Selecciona un producto para ver los detalles</p>
              </div>
              
            )}
          </div>
          <div>
            {currentProduct && (unitsToBuy >= 0 && unitsToBuy <= currentProduct.stock) ? (
              <p></p>
            ) : currentProduct && (
              <p>Cantidad inválida. Revisa el stock disponible.</p>
            )}
          </div>

        </div>
      </div>
    );
  }
}
