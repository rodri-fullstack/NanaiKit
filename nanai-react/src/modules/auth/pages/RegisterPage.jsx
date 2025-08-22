import React from 'react';
import RegisterForm from '../components/RegisterForm';
import { Link } from 'react-router-dom';
import './RegisterPage.css';

const RegisterPage = () => {
  return (
    <div className="register-container">
      <div className="register-card">
        <div className="welcome-section">
          <h1 className="welcome-title">Únete a NanaiKit</h1>
          <p className="welcome-subtitle">Crea tu espacio seguro</p>
        </div>
        <RegisterForm />
        <div className="login-link">
          <p>¿Ya tienes cuenta? <Link to="/login">Inicia sesión</Link></p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;