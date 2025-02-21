import React, { Component } from "react";
import MonedaDataService from "../services/monedas.service";
import { Link } from "react-router-dom";

export default class MonedasList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            monedas: [],
            currentMoneda: null,
            currentIndex: -1,
            eurValue: "",
            currencyValue: "",
        };
    }

    handleConvert = () => {
        const { currentMoneda, eurValue, currencyValue } = this.state;
        if (!currentMoneda) return;

        //si elijo los euros no cambia la conversion
        if (currentMoneda.name == "euro") {
            this.setState({ currencyValue: eurValue });
            return;
        }

        //si hay valor en la caja de euros, se produce la conversion en la otra moneda
        if (eurValue) {
            this.setState({ currencyValue: (parseFloat(eurValue) * currentMoneda.price) });
            //si hay un valor en la caja de la otra moneda, se 
            //produce la conversion en los euros
        } else if (currencyValue) {
            this.setState({ eurValue: (parseFloat(currencyValue) / currentMoneda.price) });
        }
    };

    //guarda el valor del input en eurValue y deja el otro vacio
    handleEurChange = (e) => {
        this.setState({
            eurValue: e.target.value,
            currencyValue: "",
        });
    };
    //guarda el valor del input en la otra moneda y vacia la caja de euros
    handleCurrencyChange = (e) => {
        this.setState({
            currencyValue: e.target.value,
            eurValue: "",
        });
    };

    componentDidMount = () => {
        this.retrieveMonedas();
    };

    retrieveMonedas = () => {
        MonedaDataService.getAll()
            .then((response) => {
                this.setState({ monedas: response.data });
                console.log(response.data);
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
        });
    };

    //cambia a la moneda seleccionada
    setMoneda = (moneda, index) => {
        this.setState({
            currentMoneda: moneda,
            currentIndex: index,
            eurValue: "",
            currencyValue: "",
        });
    };

    render() {
        const { monedas, currentMoneda, currentIndex, eurValue, currencyValue } = this.state;
        return (
            <div className="container mt-4">
                <h2 className="text-center mb-4">Lista de Productos</h2>

                <div className="row">
                <div className="col-md-6">
  <table className="table table-hover">
    <thead className="table-dark">
      <tr>
        <th scope="col">#</th>
        <th scope="col">Moneda</th>
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
          </tr>
        ))}
    </tbody>
  </table>
</div>


                    <div className="col-md-6">
                        {currentMoneda && (
                            <div>                                
                                <div className="form-group">
                                    <label><strong>Euros:</strong></label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={eurValue}
                                        onChange={this.handleEurChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <label><strong>{currentMoneda.name}</strong></label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={currencyValue}
                                        onChange={this.handleCurrencyChange}
                                    />
                                </div>
                                <button onClick={this.handleConvert} className="btn btn-primary mt-2">
                                    Convertir
                                </button>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        );
    }
}
