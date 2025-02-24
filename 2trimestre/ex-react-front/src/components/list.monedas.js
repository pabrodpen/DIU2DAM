import React, { Component } from "react";
import MonedaDataService from "../services/moneda.service";
import { Link } from "react-router-dom";
import ProgressBar from "./progress.bar"; 

export default class MonedasList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      monedas: [],
      currentMoneda: null,
      currentIndex: -1,
      unitsToSelect: 0, 
      stockInsuficiente: false,
    };
  }



  componentDidMount = () => {
    this.retrieveMonedas();
  };

  retrieveMonedas = () => {
    MonedaDataService.getAllCurrencies()
      .then((response) => {
        this.setState({ monedas: response.data });
      })
      .catch((e) => {
        console.log(e);
      });
  };

  refreshList = () => {
    this.retrieveMonedas();
    this.setState({
      currentMoneda: null,
      currentIndex: -1,
      unitsToSelect: 0,
      stockInsuficiente: false,
    });
  };

  setMoneda = (moneda, index) => {
    this.setState({
      currentMoneda: moneda,
      currentIndex: index,
      unitsToSelect: 0, 
      stockInsuficiente: false,
    });
  };

  handleUnitsChange = (event) => {
    const value = parseInt(event.target.value, 10) || 0; 

    this.setState((prevState) => ({
      unitsToSelect: value,
      stockInsuficiente: prevState.currentProduct ? value > prevState.currentMoneda.stock : false,
    }));
  };

  totalPrice = () => {
    const { currentMoneda, unitsToSelect } = this.state;
    if (!currentMoneda || unitsToSelect>currentMoneda.stock) return 0; 
    return currentMoneda.price * unitsToSelect;
  };

  sumarStock = () => {
    const {currentMoneda} = this.state;

    if (!currentMoneda) {
      return;
    }

    const updatedMoneda = { ...currentMoneda, stock: currentMoneda.stock<90? currentMoneda.stock +10: 100 };
    this.setState({ currentMoneda: updatedMoneda });

    MonedaDataService.updateCurrency(updatedMoneda.id, updatedMoneda)
      .then(() => {
        console.log("Stock actualizado correctamente.");
        this.retrieveMonedas(); 
      })
      .catch((error) => {
        console.error("Error al actualizar el stock:", error);
      });
  };

  calcularProgreso =() =>{
    const {currentMoneda} = this.state;
    const maxStock=100;
    const progress = (currentMoneda.stock / maxStock) * 100;
    if(progress>100){
      progress=100;
    }
    return progress;
  }


  render() {
    const { monedas, currentMoneda, currentIndex, unitsToSelect, stockInsuficiente } = this.state;
  

    return (
      
      <div className="container mt-4">
        <h2 className="text-center mb-4">Lista de Monedas</h2>
        <div className="input-group mb-3">
          </div>
        <div className="row">
          <div className="col-md-6">
            <table className="table table-hover">
              <thead className="table-dark">
                <tr>
                  <th>#</th>
                  <th>Nombre</th>
                  <th>Descripcion</th>
                  <th>Stock</th>
                </tr>
              </thead>
              <tbody>
                {monedas &&
                  monedas.map((moneda, index) => (
                    <tr
                      key={index}
                      className={index === currentIndex ? "table-primary" : ""}
                      onClick={() => this.setMoneda(moneda, index)}
                      style={{ cursor: "pointer" }}
                    >
                      <td>{index + 1}</td>
                      <td>{moneda.name}</td>
                      <td>{moneda.brand}</td>
                      <td>{moneda.stock}</td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>

          <div className="col-md-6">
            {currentMoneda ? (
              <div className="card p-3">
    
                

                <div className="mb-2">
                  <label className="fw-bold">Unidades:</label>
                  <input
                    type="name"
                    className="form-control"
                    value={unitsToSelect}
                    onChange={this.handleUnitsChange}
                    min="0"
                    max={currentMoneda.stock}
                    disabled={!currentMoneda.active}
                  />
                </div>

              
                <div className="mb-2">
                  <label className="fw-bold">Equivalencia en euros:</label>
                  <p>{this.totalPrice()} €</p>
                </div>

                {stockInsuficiente && (
                  <p className="text-danger">Stock insuficiente.</p>
                )}

{unitsToSelect> currentMoneda.stock && (
                  <p className="text-danger">Stock insuficiente.</p>
                )}

               


                  <button className="btn btn-primary mt-3"
                  disabled={!currentMoneda.active}
                  onClick={this.sumarStock}
                  >Actualizar stock</button>
                  
                <ProgressBar progress={this.calcularProgreso()} />
              
              </div>
              
            ) : (
              <div className="text-center">
                <p>Selecciona un producto para ver los detalles</p>
              </div>

              
            )}
            {/*
             {currentProduct.stock < 100 && (<p className="text-success">Reserva no llena.</p>
              ) : (<p className="text-danger">Reserva llena.</p>

              )}*/}
          </div>
        </div>
      </div>
    );
  }
}
