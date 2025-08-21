import React from 'react';
import RegisterForm from '../components/RegisterForm';
import { Link } from 'react-router-dom';
import './RegisterPage.css';

const RegisterPage = () => {
  return (
    <div className="register-container">
      <div className="register-card">
        <h1 className="register-title">Crea tu cuenta</h1>
        <p className="register-subtitle">Te invitamos a registrarte y crear tu espacio seguro en Nanai Kit</p>
        <RegisterForm />
        <div className="login-link">
          <p>¿Ya tienes una cuenta? <Link to="/login">Inicia sesión</Link></p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;