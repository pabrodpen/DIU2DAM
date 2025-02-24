import React, { useRef } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import MonedasList from "./components/list.monedas";

function App() {


  return (
    <div>


      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<MonedasList />} />
          <Route path="/products" element={<MonedasList />} />
        </Routes>

      </div>
    </div>
  );
}

export default App;