import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../auth/hook/useAuth';
import { useCart } from '../cart/context/CartContext';
import './Navbar.css';

const Navbar = () => {
  const { user, logout } = useAuth();
  const { getCartItemsCount } = useCart();
  const navigate = useNavigate();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const closeMenu = () => {
    setIsMenuOpen(false);
  };

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <header className="navbar navbar-expand-lg navbar-light bg-white border-bottom py-3">
      <div className="container">
        <Link to="/" className="navbar-brand">
          <img src="/assets/images/logoSoloLetras.png" alt="Logo Nanai Kit"/>
        </Link>
        
        <button 
          className="navbar-toggler" 
          type="button" 
          onClick={toggleMenu}
          aria-controls="navbarNav"
          aria-expanded={isMenuOpen}
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        
        <div className={`collapse navbar-collapse ${isMenuOpen ? 'show' : ''}`} id="navbarNav">
          <div className="ms-auto d-flex align-items-center gap-3 navbar-content">
            <ul className="navbar-nav d-flex flex-row gap-3 mb-0 nav-links">
              <li className="nav-item">
                <Link to="/" className="nav-link" onClick={closeMenu}>Inicio</Link>
              </li>
              <li className="nav-item">
                <Link to="/blog" className="nav-link" onClick={closeMenu}>Blog</Link>
              </li>
              <li className="nav-item">
                <Link to="/test" className="nav-link" onClick={closeMenu}>Test</Link>
              </li>
              <li className="nav-item">
                <Link to="/kits" className="nav-link" onClick={closeMenu}>Kits</Link>
              </li>
              <li className="nav-item">
                <Link to="/nosotres" className="nav-link" onClick={closeMenu}>Nosotres</Link>
              </li>
            </ul>
            
            {!user ? (
              <>
                <Link to="/registro" className="btn btn-light fw-bold" onClick={closeMenu}>Registrarse</Link>
                <Link to="/login" className="btn btn-light fw-bold" onClick={closeMenu}>Iniciar Sesión</Link>
              </>
            ) : (
              <>
                <Link to="/perfil" className="btn btn-outline-primary fw-bold me-2" onClick={closeMenu}>
                  Mi Perfil
                </Link>
                <button onClick={() => { handleLogout(); closeMenu(); }} className="btn btn-light fw-bold">Cerrar Sesión</button>
              </>
            )}
            
            <Link to="/buscar" className="btn btn-light p-2" onClick={closeMenu}>
              <img src="/assets/images/search.png" alt="Buscar" width="24" />
            </Link>
            
            <Link to="/carrito" className="btn btn-light p-2 position-relative" onClick={closeMenu}>
              <img src="/assets/images/shopping-cart.png" alt="Carrito" width="24" />
              {getCartItemsCount() > 0 && (
                <span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary">
                  {getCartItemsCount()}
                  <span className="visually-hidden">productos en el carrito</span>
                </span>
              )}
            </Link>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Navbar;