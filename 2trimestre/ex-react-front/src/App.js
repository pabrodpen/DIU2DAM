import React, { useRef } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import AddProduct from "./components/add.product";
import ProductsList from "./components/list.products";
import "./App.css";

function App() {
  const productsListRef = useRef(null);

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
            <Route path="/" element={<ProductsList ref={productsListRef} />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/add" element={<AddProduct />} />
          </Routes>
        </div>
    </div>
  );
}

export default App;
