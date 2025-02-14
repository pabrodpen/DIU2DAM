import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

import App from "./App";
import * as serviceWorker from "./serviceWorker";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    {/*}
     Forma de parte de la librería react-router .Es una envoltura
     para nuestra aplicación. Esta envoltura nos da acceso al API
     de historia de HTML5 para mantener nuestra interfaz
     gráfica en sincronía con la locación actual o URL.*/}
    <App />
  </BrowserRouter>
);

serviceWorker.unregister();