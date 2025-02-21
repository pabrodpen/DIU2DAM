import React, { Component } from "react";
import MonedaDataService from "../services/monedas.service";
import ProgressBar from "./progress.bar";

export default class MonedasList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            monedas: [],
            fromMoneda: null,
            toMoneda: null,
            fromValue: "",
            toValue: "",
            isFromTo: true, // Controla la dirección de la conversión
        };
    }

    componentDidMount = () => {
        this.retrieveMonedas();
    };

    retrieveMonedas = () => {
        MonedaDataService.getAll()
            .then((response) => {
                this.setState({ monedas: response.data });
            })
            .catch((e) => {
                console.log(e);
            });
    };

    setFromMoneda = (moneda) => {
        this.setState({ fromMoneda: moneda, fromValue: "", toValue: "" });
    };

    setToMoneda = (moneda) => {
        this.setState({ toMoneda: moneda, fromValue: "", toValue: "" });
    };

    handleConvert = () => {
        const { fromMoneda, toMoneda, fromValue, toValue, isFromTo } = this.state;
        if (!fromMoneda || !toMoneda) return;

        let convertedValue = "";

        if (isFromTo) {
            if (fromValue !== "") {
                convertedValue = (parseFloat(fromValue) * (toMoneda.price / fromMoneda.price)).toFixed(2);
                this.setState({ toValue: convertedValue });
            }
        } else {
            if (toValue !== "") {
                convertedValue = (parseFloat(toValue) * (fromMoneda.price / toMoneda.price)).toFixed(2);
                this.setState({ fromValue: convertedValue });
            }
        }
    };

    handleFromChange = (e) => {
        this.setState({ fromValue: e.target.value, isFromTo: true });
    };

    handleToChange = (e) => {
        this.setState({ toValue: e.target.value, isFromTo: false });
    };

    render() {
        const { monedas, fromMoneda, toMoneda, fromValue, toValue } = this.state;

        const totalMonedas = monedas.length;
        const maxMonedas = 50;
        const progress = (totalMonedas / maxMonedas) * 100;
        return (
            <div className="container mt-4">
                <h2 className="text-center mb-4">Conversor de Monedas</h2>

                <div className="row">
                    {/* Tabla de Monedas de Origen */}
                    <div className="col-md-5">
                        <h5>Desde</h5>
                        <table className="table table-hover">
                            <thead className="table-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Moneda</th>
                                </tr>
                            </thead>
                            <tbody>
                                {monedas.map((moneda, index) => (
                                    <tr
                                        key={index}
                                        className={fromMoneda === moneda ? "table-primary" : ""}
                                        onClick={() => this.setFromMoneda(moneda)}
                                        style={{ cursor: "pointer" }}
                                    >
                                        <td>{index + 1}</td>
                                        <td>{moneda.name}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>

                        {fromMoneda && (
                            <div className="mt-3">
                                <label><strong>{fromMoneda.name}:</strong></label>
                                <input
                                    type="number"
                                    className="form-control"
                                    value={fromValue}
                                    onChange={this.handleFromChange}
                                />
                            </div>
                        )}
                    </div>

                    {/* Tabla de Monedas de Destino */}
                    <div className="col-md-5">
                        <h5>Hacia</h5>
                        <table className="table table-hover">
                            <thead className="table-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Moneda</th>
                                </tr>
                            </thead>
                            <tbody>
                                {monedas.map((moneda, index) => (
                                    <tr
                                        key={index}
                                        className={toMoneda === moneda ? "table-success" : ""}
                                        onClick={() => this.setToMoneda(moneda)}
                                        style={{ cursor: "pointer" }}
                                    >
                                        <td>{index + 1}</td>
                                        <td>{moneda.name}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>

                        {toMoneda && (
                            <div className="mt-3">
                                <label><strong>{toMoneda.name}:</strong></label>
                                <input
                                    type="number"
                                    className="form-control"
                                    value={toValue}
                                    onChange={this.handleToChange}
                                />
                            </div>
                        )}
                   </div>
                   <div>
                    <ProgressBar progress={progress} />
                   </div>
                </div>

                {/* Botón para realizar la conversión */}
                <div className="text-center mt-3">
                    <button className="btn btn-primary" onClick={this.handleConvert} disabled={!fromMoneda || !toMoneda}>
                        Convertir
                    </button>
                </div>
            </div>
        );
    }
}
