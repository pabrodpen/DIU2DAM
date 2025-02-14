import React, { Component } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import PersonsList from "./components/persons.list";
import AddPerson from "./components/add.person";
import EditPerson from "./components/edit.person"


class App extends Component {
  render() {
    const { removeAllPersons } = this.props;
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
                <div className="navbar-nav mr-auto">
                <li className="nav-item">
                  <Link to={"/persons"} className="nav-link">
                  Personas
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to={"/add"} className="nav-link">
                  Add
                  </Link>
                </li>
                <li className="nav-item">
                  <button className="nav-link btn btn-link" onClick={PersonsList.removeAllPersons}>
                  Limpiar contactos
                  </button>
                </li>
                </div>
              </nav>

              <div className="container mt-3">
                {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
          <Routes>
            <Route path="/" element={<PersonsList />} />
            <Route path="/persons" element={<PersonsList />} />
            <Route path="/add" element={<AddPerson/>} />
            <Route path="/edit/:id" element={<EditPerson/>} /> 
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;