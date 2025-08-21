import React from 'react';
import { Link } from 'react-router-dom';

const AboutSection = () => {
  return (
    <section className="about-section py-5 bg-light">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-lg-6 order-lg-2 mb-4 mb-lg-0">
            <img 
              src="/assets/images/lavandaHome.png" 
              alt="Sobre Nanai Kit" 
              className="img-fluid rounded shadow-lg" 
            />
          </div>
          <div className="col-lg-6 order-lg-1">
            <h2 className="mb-4">Sobre Nanai Kit</h2>
            <p className="lead mb-4">
              En Nanai Kit creemos que el autocuidado debe ser accesible y personalizado para cada persona.
              Nuestros kits están diseñados por expertos en bienestar, utilizando productos naturales
              y sostenibles que respetan tanto tu cuerpo como el medio ambiente.
            </p>
            <p className="mb-4">
              Cada kit es cuidadosamente seleccionado según las necesidades específicas identificadas
              a través de nuestro test de bienestar, garantizando que recibas exactamente lo que necesitas
              para mejorar tu calidad de vida.
            </p>
            <Link to="/nosotres" className="btn btn-outline-primary">
              Conoce Más Sobre Nosotres
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AboutSection;