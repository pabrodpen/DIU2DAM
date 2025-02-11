import React, { useState } from "react";
import { auth } from "../firebaseConfig";
import { 
  signInWithEmailAndPassword, 
  createUserWithEmailAndPassword, 
  sendPasswordResetEmail, 
  signInWithPopup, 
  GoogleAuthProvider 
} from "firebase/auth";

const Auth = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isRegister, setIsRegister] = useState(false);
  const [message, setMessage] = useState("");

  const provider = new GoogleAuthProvider();

  // Método para manejar login/registro
  const handleAuth = async (e) => {
    e.preventDefault();
    try {
      if (isRegister) {
        await createUserWithEmailAndPassword(auth, email, password);
        setMessage("Usuario registrado correctamente");
      } else {
        await signInWithEmailAndPassword(auth, email, password);
        setMessage("Inicio de sesión exitoso");
      }
    } catch (error) {
      setMessage(error.message);
    }
  };

  // Método para login con Google
  const handleGoogleLogin = async () => {
    try {
      await signInWithPopup(auth, provider);
      setMessage("Inicio de sesión con Google exitoso");
    } catch (error) {
      setMessage(error.message);
    }
  };

  // Método para restablecer contraseña
  const handleResetPassword = async () => {
    if (!email) {
      setMessage("Introduce un correo electrónico válido");
      return;
    }
    try {
      await sendPasswordResetEmail(auth, email);
      setMessage("Correo de restablecimiento enviado");
    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    <div className="auth-container">
      <h2>{isRegister ? "Registro" : "Iniciar Sesión"}</h2>
      {message && <p>{message}</p>}
      <form onSubmit={handleAuth}>
        <input 
          type="email" 
          placeholder="Correo" 
          value={email} 
          onChange={(e) => setEmail(e.target.value)} 
        />
        <input 
          type="password" 
          placeholder="Contraseña" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
        />
        <button type="submit">{isRegister ? "Registrarse" : "Iniciar Sesión"}</button>
      </form>
      <button onClick={handleGoogleLogin}>Iniciar con Google</button>
      <button onClick={handleResetPassword}>¿Olvidaste tu contraseña?</button>
      <p onClick={() => setIsRegister(!isRegister)}>
        {isRegister ? "¿Ya tienes cuenta? Inicia sesión" : "¿No tienes cuenta? Regístrate"}
      </p>
    </div>
  );
};

export default Auth;
