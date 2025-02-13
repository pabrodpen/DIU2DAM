import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

//import AddTutorial from "./components/add-tutorial.component";
//Componente para editar componente
//import Tutorial from "./components/tutorial.component";
import PersonsList from "./components/persons.list";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/persons"} className="navbar-brand">
            Contactos
          </Link>
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
          </div>
        </nav>

        <div className="container mt-3">
          {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
          <Routes>
            <Route path="/" element={<PersonsList />} />
            <Route path="/persons" element={<PersonsList />} />
            {/*  <Route exact path="/add" component={AddTutorial} /> */}
          {/*  <Route path="/tutorials/:id" component={Tutorial} /> */}
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;