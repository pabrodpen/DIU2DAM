import { initializeApp } from "firebase/app";
import { getAuth, onAuthStateChanged } from "firebase/auth";
import { getFirestore, doc, setDoc, getDoc } from "firebase/firestore";
import { getStorage } from "firebase/storage";

// 🔹 Configuración de Firebase (Reemplaza con tus credenciales reales)
const firebaseConfig = {
    apiKey: "AIzaSyBTK7GwM8rhg5sxLSoqu0OX-obz3KOAd4k",
    authDomain: "agenda-react-e7ac0.firebaseapp.com",
    projectId: "agenda-react-e7ac0",
    storageBucket: "agenda-react-e7ac0.appspot.com",
    messagingSenderId: "163868833973",
    appId: "1:163868833973:web:eeb8ad0a21c5e10e34a6fc",
    measurementId: "G-24PG5WKFV1"
  };

// 🔹 Inicializar Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app);
export const storage = getStorage(app);  // AHORA Firebase ya está inicializado

console.log("✅ Firebase inicializado correctamente:", app);
console.log("📂 Storage inicializado correctamente:", storage);


// 🔹 Función para crear el documento del usuario en Firestore
export const generateUserDocument = async (user, additionalData) => {
  if (!user) return;

  const userRef = doc(db, "users", user.uid);
  const snapshot = await getDoc(userRef);

  if (!snapshot.exists()) {
    try {
      await setDoc(userRef, {
        displayName: user.displayName,
        email: user.email,
        photoURL: user.photoURL || null, // ✅ Guardamos la imagen del usuario
        ...additionalData
      });
    } catch (error) {
      console.error("❌ Error al crear el documento del usuario:", error);
    }
  }
  return userRef;
};

// 🔹 Función para obtener el usuario autenticado
export const getCurrentUser = (callback) => {
  onAuthStateChanged(auth, async (user) => {
    if (user) {
      const userDoc = await getDoc(doc(db, "users", user.uid));
      callback(userDoc.exists() ? userDoc.data() : null);
    } else {
      callback(null);
    }
  });
};