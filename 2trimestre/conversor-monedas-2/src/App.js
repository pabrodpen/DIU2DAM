import React, { useRef } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import MonedasList from "./components/monedas.list";

function App() {
  const personsListRef = useRef(null); // Creamos la referencia

  return (
    <Router> {/* 👈 Envuelve toda la app dentro de <Router> */}
      <div>
        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<Navigate to="/products" />} />
            <Route path="/products" element={<MonedasList />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;