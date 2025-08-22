import React from 'react';
import { useAuth } from '../hook/useAuth';
import './UserProfile.css';

const UserProfile = () => {
  const { user, logout } = useAuth();

  if (!user) {
    return (
      <div className="user-profile-container">
        <div className="alert alert-warning">
          No hay usuario autenticado
        </div>
      </div>
    );
  }

  const handleLogout = () => {
    logout();
  };

  return (
    <div className="user-profile-container">
      <div className="profile-card">
        <div className="profile-header">
          <div className="profile-avatar">
            <span className="avatar-text">
              {user.nombre?.charAt(0)?.toUpperCase() || 'U'}
            </span>
          </div>
          <h2 className="profile-name">
            {user.nombre} {user.apellido}
          </h2>
          <p className="profile-username">@{user.username}</p>
        </div>
        
        <div className="profile-info">
          <div className="info-item">
            <label>ID de Usuario:</label>
            <span>{user.id}</span>
          </div>
          
          <div className="info-item">
            <label>Nombre:</label>
            <span>{user.nombre}</span>
          </div>
          
          <div className="info-item">
            <label>Apellido:</label>
            <span>{user.apellido}</span>
          </div>
          
          <div className="info-item">
            <label>Email:</label>
            <span>{user.email}</span>
          </div>
          
          <div className="info-item">
            <label>Usuario:</label>
            <span>{user.username}</span>
          </div>
      
        </div>
        
        <div className="profile-actions">
          <button 
            className="btn btn-outline-primary me-2"
            onClick={() => window.location.reload()}
          >
            Actualizar Perfil
          </button>
          <button 
            className="btn btn-danger"
            onClick={handleLogout}
          >
            Cerrar Sesión
          </button>
        </div>
      </div>
      
     
      </div>
    
  );
};

export default UserProfile;