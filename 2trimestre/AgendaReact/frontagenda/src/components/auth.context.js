import React, { createContext, useState, useEffect } from "react";
import { auth } from "../firebase";
import { onAuthStateChanged, signOut } from "firebase/auth";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(auth, (user) => {
      console.log("🔥 Usuario detectado en AuthContext:", user); // 🔥 Debug
      setCurrentUser(user);
    });
    return () => unsubscribe();
  }, []);

  const logout = () => {
    signOut(auth)
      .then(() => console.log("✅ Usuario cerró sesión"))
      .catch((error) => console.error("❌ Error al cerrar sesión", error));
  };

  return (
    <AuthContext.Provider value={{ currentUser, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
