import React, { useRef } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import ProductsList from "./components/list.products";
import AddProduct from "./components/add.product";
import EditProduct from "./components/edit.product";

function App() {
  const personsListRef = useRef(null); // Creamos la referencia

  const handleRemoveAllPersons = () => {
    if (personsListRef.current) {
      personsListRef.current.removeAllPersons(); // Llamamos correctamente la función
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/products"} className="nav-link">
              Productos
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
        <Routes>
          <Route path="/" element={<ProductsList />} />
          <Route path="/products" element={<ProductsList />} />
          <Route path="/add" element={<AddProduct />} />
          <Route path="/edit/:id" element={<EditProduct />} /> 
        </Routes>

      </div>
    </div>
  );
}

export default App;