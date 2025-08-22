import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AddToCartButton from '../../cart/components/AddToCartButton';
import './KitsPage.css';

const KitsPage = () => {
  const [kits, setKits] = useState([]);
  const [loading, setLoading] = useState(true);

  // Datos simulados de kits
  const mockKits = [
    {
      id: 1,
      name: "Kit Rutina",
      price: 35000,
      image: "/assets/images/kitRutinaHome.png",
      description: "Kit completo para establecer rutinas saludables",
      category: "Bienestar"
    },
    {
      id: 2,
      name: "Kit Gratitud",
      price: 28000,
      image: "/assets/images/kitGratitudHome.png",
      description: "Kit para cultivar la gratitud diaria",
      category: "Mindfulness"
    },
    {
      id: 3,
      name: "Kit Calma",
      price: 42000,
      image: "/assets/images/kitCalma.png",
      description: "Nuestro kit más popular y completo",
      category: "Premium"
    },
    {
      id: 4,
      name: "Kit Renace",
      price: 38000,
      image: "/assets/images/KitRenace.png",
      description: "Experiencia completa de campo de lavanda",
      category: "Premium"
    
    },
    {
      id: 5,
      name: "Kit Contención",
      price: 25000,
      image: "/assets/images/KitContencion.png",
      description: "Kit relajante con esencia de lavanda",
      category: "Relajación"
    },
    {
      id: 6,
      name: "Kit Sos Ansiedad",
      price: 32000,
      image: "/assets/images/KitSOS.png",
      description: "Kit esencial para comenzar tu journey",
      category: "Esencial"
     
    }
  ];





  useEffect(() => {
    // Simular carga de datos
    setTimeout(() => {
      setKits(mockKits);
      setLoading(false);
    }, 500);
  }, []);

  const formatPrice = (price) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      minimumFractionDigits: 0
    }).format(price);
  };

  if (loading) {
    return (
      <div className="kits-page">
        <div className="container py-5">
          <div className="text-center">
            <div className="spinner-border text-primary" role="status">
              <span className="visually-hidden">Cargando...</span>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="kits-page">
      <div className="container py-5">
        <div className="row mb-5">
          <div className="col-12 text-center">
            <h1 className="display-4 fw-bold mb-3">Nuestros Kits</h1>
            <p className="lead text-muted">
              Descubre nuestra colección completa de kits diseñados para tu bienestar
            </p>
          </div>
        </div>

        {/* Grid responsivo sin fijar ancho de card */}
        <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
          {kits.map((kit) => (
            <div key={kit.id} className="col">
              <div className="card kit-card h-100 shadow-sm">
                {/* Top de imagen ocupando todo el ancho y alto del contenedor */}
                <div className="kit-image-container">
                  <img
                    src={kit.image}
                    alt={kit.name}
                    className="card-img-top kit-image"
                  />
                  <div className="kit-category">{kit.category}</div>
                </div>

                {/* Contenido */}
                <div className="card-body d-flex flex-column">
                  <h5 className="card-title fw-bold">{kit.name}</h5>
                  <p className="card-text text-muted flex-grow-1">
                    {kit.description}
                  </p>
                  <div className="kit-price mb-3">
                    <span className="price-amount">{formatPrice(kit.price)}</span>
                  </div>
                  <div className="d-flex gap-2">
                    <Link
                      to={`/productos/${kit.id}`}
                      className="btn btn-outline-primary flex-grow-1"
                    >
                      Ver Detalles
                    </Link>
                    <AddToCartButton product={kit} />
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="row mt-5">
          <div className="col-12">
            <div className="kit-info-section">
              <div className="section-header">
                <h2 className="section-title">¿Por qué elegir nuestros kits?</h2>
                <p className="section-subtitle">Descubre lo que nos hace únicos</p>
              </div>
              <div className="row g-4">
                <div className="col-md-4">
                  <div className="feature-card">
                    <div className="feature-icon">
                      <svg width="56" height="56" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M12 2L13.09 8.26L20 9L13.09 9.74L12 16L10.91 9.74L4 9L10.91 8.26L12 2Z" fill="currentColor"/>
                      </svg>
                    </div>
                    <div className="feature-content">
                      <h5>Calidad Premium</h5>
                      <p>Productos cuidadosamente seleccionados para tu bienestar</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-4">
                  <div className="feature-card">
                    <div className="feature-icon">
                      <svg width="56" height="56" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M12 2L2 7V10C2 16 6 20 12 22C18 20 22 16 22 10V7L12 2Z" fill="currentColor"/>
                      </svg>
                    </div>
                    <div className="feature-content">
                      <h5>Envío Gratis</h5>
                      <p>Envío gratuito en compras superiores a $30.000</p>
                    </div>
                  </div>
                </div>
                <div className="col-md-4">
                  <div className="feature-card">
                    <div className="feature-icon">
                      <svg width="56" height="56" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M9 12L11 14L15 10M21 12C21 16.9706 16.9706 21 12 21C7.02944 21 3 16.9706 3 12C3 7.02944 7.02944 3 12 3C16.9706 3 21 7.02944 21 12Z" fill="currentColor"/>
                      </svg>
                    </div>
                    <div className="feature-content">
                      <h5>Satisfacción Garantizada</h5>
                      <p>30 días de garantía en todos nuestros productos</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>  
    </div>
  );
};

export default KitsPage;
