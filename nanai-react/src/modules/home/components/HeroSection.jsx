import React from 'react';
import { Link } from 'react-router-dom';

const HeroSection = () => {
  return (
    <section className="hero-section py-5">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-lg-6 mb-4 mb-lg-0">
            <h1 className="display-4 fw-bold mb-3">Descubre tu bienestar con Nanai Kit</h1>
            <p className="lead mb-4">
              Kits personalizados con productos naturales para tu rutina de autocuidado.
              Descubre qué necesita tu cuerpo y mente para sentirte mejor cada día.
            </p>
            <div className="d-grid gap-2 d-md-flex">
              <Link to="/test" className="btn btn-primary btn-lg px-4 me-md-2">Hacer Test</Link>
              <Link to="/kits" className="btn btn-outline-secondary btn-lg px-4">Ver Kits</Link>
            </div>
          </div>
          <div className="col-lg-6">
            <img 
              src="/assets/images/logo2.png" 
              alt="Nanai Kit Logo" 
              className="img-fluid" 
            />
          </div>
        </div>
      </div>
    </section>
  );
};

export default HeroSection;