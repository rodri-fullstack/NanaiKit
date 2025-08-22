import React from 'react';
import LoginForm from '../components/LoginForm';
import { Link } from 'react-router-dom';
import './LoginPage.css';

const LoginPage = () => {
  return (
    <div className="login-container">
      <div className="login-card">
        <div className="welcome-section">
          <h1 className="welcome-title">Bienvenido a NanaiKit</h1>
          <p className="welcome-subtitle">Tu espacio seguro para conectarte</p>
        </div>
        <LoginForm />
        <div className="register-link">
          <p>¿No tienes cuenta? <Link to="/registro">Regístrate</Link></p>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;