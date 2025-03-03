import React, { useContext } from "react";
import { AuthContext } from "../components/auth.context";
import defaultAvatar from "../assets/avatar.png"; 

const UserProfile = () => {
  const { currentUser, signOut } = useContext(AuthContext);

  const handleLogout = async () => {
    await signOut();
    console.log("Sesión cerrada");
  };

  return (
    <div className="user-profile">
      <img src={currentUser?.photoURL || defaultAvatar} alt="Usuario" className="user-avatar" />
      <span className="user-name">{currentUser?.displayName || "Invitado"}</span>
      <button className="btn-logout" onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserProfile;
