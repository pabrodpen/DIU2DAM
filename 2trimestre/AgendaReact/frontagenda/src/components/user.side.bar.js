import React, { useState, useContext, useEffect, useRef } from "react";
import { AuthContext } from "../components/auth.context";
import { useNavigate } from "react-router-dom";
import iconAvatar from "../assets/icon_avatar.png"; // Asegúrate de que la imagen existe

const UserSidebar = () => {
  const { currentUser, logout } = useContext(AuthContext);
  const [isOpen, setIsOpen] = useState(false);
  const sidebarRef = useRef(null);
  const navigate = useNavigate();

  const toggleSidebar = () => {
    setIsOpen(!isOpen);
  };

  // Cierra la sidebar si haces clic fuera de ella
  useEffect(() => {
    function handleClickOutside(event) {
      if (sidebarRef.current && !sidebarRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  return (
    <div className="user-sidebar-container" ref={sidebarRef}>
      {/* 🔹 Avatar con hover igual que la Navbar */}
      <div 
        className="menu__item user-avatar-container"
        onClick={toggleSidebar}
        data-tooltip="Perfil" // 🔥 Tooltip igual que en Navbar
      >
        <img
          src={currentUser?.photoURL || iconAvatar}
          alt="Avatar"
          className="menu__icon" // 🔥 Usa el mismo estilo que los iconos de Navbar
        />
      </div>

      {/* 🔹 Sidebar desplegable */}
      {isOpen && (
        <div className="user-sidebar">
          {currentUser ? (
            <>
              <p>Hola, <strong>{currentUser?.displayName || currentUser?.email}</strong>!</p>
              <button className="logout-btn" onClick={logout}>Cerrar sesión</button>
            </>
          ) : (
            <>
              <p>No estás logeado</p>
              <button className="login-btn" onClick={() => navigate("/signUp")}>Iniciar sesión / Registrarse</button>
            </>
          )}
        </div>
      )}
    </div>
  );
};

export default UserSidebar;
