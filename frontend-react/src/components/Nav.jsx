import { Link, NavLink } from "react-router-dom";

export default function Nav() {
  return (
    <header className="navbar">
      <img src="/assets/images/logoSoloLetras.png" alt="Logo Nanai Kit" className="logo" />

      <nav className="nav-menu">
        {/* Anclas que llevan a secciones dentro del Home */}
        <a href="#inicio">Inicio</a>
        <a href="#blog">Blog</a>
        <a href="#test">Test</a>
        <a href="#kits">Kits</a>
        <a href="#nosotres">Nosotres</a>

        {/* Navegación entre páginas SPA (cuando tengas esas rutas) */}
        <NavLink to="/registro" className="btn-secondary">Registrarse</NavLink>
        <NavLink to="/login" className="btn-secondary">Iniciar Sesión</NavLink>

        {/* Íconos */}
        <a href="#buscar"><img src="/assets/images/search.png" alt="Buscar" /></a>
        <Link to="/carrito"><img src="/assets/images/shopping-cart.png" alt="Carrito" /></Link>
      </nav>
    </header>
  );
}
