import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import '../styles/styles.css'; 

class Table_Example extends Component {
    constructor() {
        super()
        this.state = {
            letra: [] // Inicializa como un arreglo vacío
          };
    }

    actualizarLetra(prevProps) {
        if (prevProps.letra !== this.props.letra) {
            this.setState({ letra: this.props.letra });
        }
    }
    renderData(letra, index) {
        return (
            <tr>
                <td>{letra.texto}</td>
            </tr>
        )
    }

    render() {
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>Letra</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.letra.map(this.renderData)}
                </tbody>
            </Table>)
    }
}

export default Table_Example;