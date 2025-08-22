import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../auth/hook/useAuth';
import { useCart } from '../cart/context/CartContext';
import './Navbar.css';

const Navbar = () => {
  const { user, logout } = useAuth();
  const { getCartItemsCount } = useCart();
  const navigate = useNavigate();
  const location = useLocation();
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      const isScrolled = window.scrollY > 20;
      setScrolled(isScrolled);
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

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

  const isActive = (path) => {
    return location.pathname === path;
  };

  return (
    <header className={`navbar ${scrolled ? 'navbar-scrolled' : ''}`}>
      <div className="navbar-container">
        <Link to="/" className="navbar-brand">
          <div className="logo-container">
            <img src="/assets/images/logoSoloLetras.png" alt="Logo Nanai Kit" className="logo-image"/>
            <div className="logo-glow"></div>
          </div>
        </Link>
        
        <button 
          className={`navbar-toggler ${isMenuOpen ? 'active' : ''}`}
          type="button" 
          onClick={toggleMenu}
          aria-controls="navbarNav"
          aria-expanded={isMenuOpen}
          aria-label="Toggle navigation"
        >
          <span className="hamburger-line"></span>
          <span className="hamburger-line"></span>
          <span className="hamburger-line"></span>
        </button>
        
        <div className={`navbar-menu ${isMenuOpen ? 'show' : ''}`}>
          <nav className="navbar-nav">
            <Link 
              to="/" 
              className={`nav-link ${isActive('/') ? 'active' : ''}`} 
              onClick={closeMenu}
            >
              Inicio
            </Link>
            <Link 
              to="/blog" 
              className={`nav-link ${isActive('/blog') ? 'active' : ''}`} 
              onClick={closeMenu}
            >
              Blog
            </Link>
            <Link 
              to="/test" 
              className={`nav-link ${isActive('/test') ? 'active' : ''}`} 
              onClick={closeMenu}
            >
              Test
            </Link>
            <Link 
              to="/kits" 
              className={`nav-link ${isActive('/kits') ? 'active' : ''}`} 
              onClick={closeMenu}
            >
              Kits
            </Link>
            <Link 
              to="/nosotres" 
              className={`nav-link ${isActive('/nosotres') ? 'active' : ''}`} 
              onClick={closeMenu}
            >
              Nosotres
            </Link>
          </nav>
          
          <div className="navbar-actions">
            <div className="auth-buttons">
              {!user ? (
                <>
                  <Link to="/registro" className="btn btn-register" onClick={closeMenu}>
                    <span className="btn-text">Registrarse</span>
                    <div className="btn-glow"></div>
                  </Link>
                  <Link to="/login" className="btn btn-login" onClick={closeMenu}>
                    <span className="btn-text">Iniciar Sesión</span>
                    <div className="btn-glow"></div>
                  </Link>
                </>
              ) : (
                <>
                  <Link to="/perfil" className="btn btn-profile" onClick={closeMenu}>
                    <span className="btn-text">Mi Perfil</span>
                    <div className="btn-glow"></div>
                  </Link>
                  <button onClick={() => { handleLogout(); closeMenu(); }} className="btn btn-logout">
                    <span className="btn-text">Cerrar Sesión</span>
                    <div className="btn-glow"></div>
                  </button>
                </>
              )}
            </div>
            
            <div className="search-cart-container">
              <Link to="/buscar" className="action-btn search-btn" onClick={closeMenu}>
                <svg className="search-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <circle cx="11" cy="11" r="8"/>
                  <path d="m21 21-4.35-4.35"/>
                </svg>
              </Link>
              
              <Link 
                to="/carrito" 
                className={`action-btn cart-btn ${getCartItemsCount() > 0 ? 'has-items' : ''}`} 
                onClick={closeMenu}
              >
                <svg className="cart-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                  <path d="M9 22a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                  <path d="M20 22a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                  <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/>
                </svg>
                {getCartItemsCount() > 0 && (
                  <span className="cart-badge">
                    {getCartItemsCount()}
                  </span>
                )}
              </Link>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Navbar;