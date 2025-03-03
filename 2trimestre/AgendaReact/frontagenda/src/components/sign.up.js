import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { auth, generateUserDocument } from "../firebase";  
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import "../styles/auth.css";

const SignUp = () => {
  const [formData, setFormData] = useState({ 
    firstName: "", 
    lastName: "", 
    email: "", 
    password: "", 
    photoURL: ""  // 🔥 Agregamos campo para URL de imagen
  });
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
  
    // 🔹 Validar si la URL de la imagen es válida (opcional)
    if (formData.photoURL && !/(https?:\/\/.*\.(?:png|jpg|jpeg|gif|svg))/i.test(formData.photoURL)) {
      alert("❌ La URL de la imagen no es válida.");
      return;
    }
  
    try {
      const userCredential = await createUserWithEmailAndPassword(auth, formData.email, formData.password);
      const user = userCredential.user;
  
      await updateProfile(user, {
        displayName: `${formData.firstName} ${formData.lastName}`,
        photoURL: formData.photoURL || "https://via.placeholder.com/150",  
      });
  
      await generateUserDocument(user, { 
        firstName: formData.firstName, 
        lastName: formData.lastName, 
        photoURL: user.photoURL 
      });
  
      alert("✅ Registro exitoso!");
      navigate("/persons");
    } catch (error) {
      console.error("❌ Error al registrar usuario:", error);
  
      if (error.code === "auth/email-already-in-use") {
        alert("⚠️ Este correo ya está registrado. Intenta con otro.");
      } else {
        alert("❌ Error en el registro. Intenta de nuevo.");
      }
    }
  };
  

  return (
    <div className="form-container">
      <ul className="tab-group">
        <li className="tab active"><a href="/signUp">Sign Up</a></li>
        <li className="tab"><a href="/signIn">Log In</a></li>
      </ul>

      <div className="tab-content">
        <div id="signup">
          <h1>Sign Up for Free</h1>
          <form onSubmit={handleSubmit}>
            <div className="top-row">
              <div className="field-wrap">
                <label>First Name<span className="req">*</span></label>
                <input type="text" name="firstName" required autoComplete="off" onChange={handleChange} />
              </div>
              <div className="field-wrap">
                <label>Last Name<span className="req">*</span></label>
                <input type="text" name="lastName" required autoComplete="off" onChange={handleChange} />
              </div>
            </div>

            <div className="field-wrap">
              <label>Email Address<span className="req">*</span></label>
              <input type="email" name="email" required autoComplete="off" onChange={handleChange} />
            </div>

            <div className="field-wrap">
              <label>Set A Password<span className="req">*</span></label>
              <input type="password" name="password" required autoComplete="off" onChange={handleChange} />
            </div>

            <div className="field-wrap">
              <label>Profile Picture URL</label>
              <input 
                type="text" 
                name="photoURL" 
                placeholder="https://example.com/image.png" 
                autoComplete="off" 
                onChange={handleChange} 
              />
            </div>

            <button className="button button-block">Get Started</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
