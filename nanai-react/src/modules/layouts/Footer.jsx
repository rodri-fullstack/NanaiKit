import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="footer">
      <section className="social">
        <h2>Síguenos</h2>
        <div className="social-icons">
          <a href="#"><img src="/assets/images/icon-instagram.png" alt="Instagram" />Instagram</a>
          <a href="#"><img src="/assets/images/icon-equis.png" alt="X" />X</a>
          <a href="#"><img src="/assets/images/icon-facebook.png" alt="Facebook" />Facebook</a>
        </div>
      </section>
      <br />

      <div className="footer-links">
        <a href="#">Preguntas Frecuentes</a>
        <a href="#">Política de Privacidad</a>
        <a href="#">Términos de Servicio</a>
      </div>
      <p>&copy; 2025 Nanai Kit. Todos los derechos reservados.</p>
    </footer>
  );
};

export default Footer;