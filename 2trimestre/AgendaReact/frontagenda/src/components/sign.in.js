import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { signInWithEmailAndPassword } from "firebase/auth"; // 🔹 Importar autenticación
import { auth } from "../firebase"; // 🔹 Importar configuración de Firebase
import "../styles/auth.css"; // Asegúrate de importar los nuevos estilos

const SignIn = () => {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    // 🔹 Validar formato de email
    if (!/\S+@\S+\.\S+/.test(formData.email)) {
      alert("❌ El formato del email no es válido.");
      return;
    }
  
    try {
      const userCredential = await signInWithEmailAndPassword(auth, formData.email, formData.password);
      const user = userCredential.user;
  
      // 🔹 Validar si la imagen de perfil es válida
      if (!user.photoURL || user.photoURL === "https://via.placeholder.com/150") {
        alert("⚠️ No tienes una imagen de perfil válida.");
      }
  
      alert("✅ Inicio de sesión exitoso!");
      navigate("/persons");
    } catch (error) {
      console.error("❌ Error al iniciar sesión:", error);
  
      // 🔹 Manejo de errores específicos
      switch (error.code) {
        case "auth/user-not-found":
          alert("⚠️ Usuario no encontrado. Verifica tu correo.");
          break;
        case "auth/wrong-password":
          alert("⚠️ Contraseña incorrecta.");
          break;
        case "auth/invalid-credential":
          alert("⚠️ Correo o contraseña incorrectos.");
          break;
        default:
          alert("❌ Error al iniciar sesión. Intenta de nuevo.");
          break;
      }
    }
  };
  

  return (
    <div className="form-container">
      <ul className="tab-group">
        <li className="tab"><a href="/signUp">Sign Up</a></li>
        <li className="tab active"><a href="/signIn">Log In</a></li>
      </ul>

      <div className="tab-content">
        <div id="login">
          <h1>Welcome Back!</h1>
          <form onSubmit={handleSubmit}>
            <div className="field-wrap">
              <label>Email Address<span className="req">*</span></label>
              <input type="email" name="email" required autoComplete="off" onChange={handleChange} />
            </div>

            <div className="field-wrap">
              <label>Password<span className="req">*</span></label>
              <input type="password" name="password" required autoComplete="off" onChange={handleChange} />
            </div>

            <button className="button button-block">Log In</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignIn;
