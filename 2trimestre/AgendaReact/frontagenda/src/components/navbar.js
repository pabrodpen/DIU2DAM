import React, { useEffect, useState, useContext } from "react";
import { Link, useLocation } from "react-router-dom";
import "../App.css"; // Asegúrate de importar los estilos correctamente
import imagenLista from "../assets/icon_lista.png";
import imagenFormulario from "../assets/icon_form.png";
import imagenAvatar from "../assets/avatar.png";
import { AuthContext  } from "./auth.context";
import UserSidebar from "./user.side.bar";

const menuItems = [
  { path: "/persons", image: imagenLista, label: "Lista de contactos", color: "#ff8c00", tooltip: "Ver lista de contactos" },
  { path: "/add", image: imagenFormulario, label: "Añadir contacto", color: "#f54888", tooltip: "Añadir un nuevo contacto" },
];

const Navbar = () => {
  const location = useLocation();
  const [activeIndex, setActiveIndex] = useState(0);
  const { currentUser, logout } = useContext(AuthContext);

  useEffect(() => {
    const currentIndex = menuItems.findIndex((item) => item.path === location.pathname);
    if (currentIndex !== -1) setActiveIndex(currentIndex);
  }, [location]);

  return (
    <menu className="menu">
      {menuItems.map((item, index) => (
        <button
          key={index}
          className={`menu__item ${index === activeIndex ? "active" : ""}`}
          style={{ "--bgColorItem": item.color }}
          onClick={() => setActiveIndex(index)}
          data-tooltip={item.tooltip} // 🔹 Para usar el tooltip con CSS
        >
          <Link to={item.path} className="menu__link">
            <img src={item.image} alt={item.label} className="menu__icon" />
          </Link>
        </button>
      ))}
      <div className="menu__border"></div>
      {/*currentUser ? (
          <li className="user-info">
            <img 
              src={currentUser.photoURL || "https://via.placeholder.com/40"} 
              alt="User"
              className="user-avatar"
            />
            <span>{currentUser.displayName || currentUser.email}</span>
            <button className="logout-btn" onClick={logout}>Cerrar sesión</button>
          </li>
        ) : (
          <>
            <li className="user-info">
              <img 
                src="https://via.placeholder.com/40" 
                alt="Default User"
                className="user-avatar"
              />
              <span>Invitado</span>
            </li>
            
          </>
        )*/}
        <UserSidebar />
    </menu>
  );
};

export default Navbar;
