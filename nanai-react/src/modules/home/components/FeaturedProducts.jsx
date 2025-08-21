import React from 'react';
import { Link } from 'react-router-dom';
import AddToCartButton from '../../cart/components/AddToCartButton';

const FeaturedProducts = () => {
  // Datos simulados de productos destacados
  const featuredProducts = [
    {
      id: 1,
      name: 'Kit Rutina',
      description: 'Productos esenciales para tu rutina diaria de autocuidado.',
      image: '/assets/images/kitRutinaHome.png',
      price: 35000
    },
    {
      id: 2,
      name: 'Kit Gratitud',
      description: 'Elementos para practicar la gratitud y mejorar tu bienestar emocional.',
      image: '/assets/images/kitGratitudHome.png',
      price: 28000
    },
    {
      id: 3,
      name: 'Kit Destacado',
      description: 'Nuestra selección premium de productos para un autocuidado completo.',
      image: '/assets/images/kitDestacadoHome.png',
      price: 42000
    }
  ];

  const formatPrice = (price) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      minimumFractionDigits: 0
    }).format(price);
  };

  return (
    <section className="featured-products py-5 bg-light">
      <div className="container">
        <h2 className="text-center mb-5">Nuestros Kits Destacados</h2>
        
        <div className="row">
          {featuredProducts.map(product => (
            <div key={product.id} className="col-md-4 mb-4">
              <div className="card h-100 border-0 shadow-sm">
                <div className="featured-image-container">
                  <img 
                    src={product.image} 
                    className="card-img-top featured-image" 
                    alt={product.name} 
                  />
                </div>
                <div className="card-body">
                  <h5 className="card-title">{product.name}</h5>
                  <p className="card-text">{product.description}</p>
                  <p className="card-text fw-bold">{formatPrice(product.price)}</p>
                  <div className="d-flex justify-content-between mt-3">
                    <Link to={`/productos/${product.id}`} className="btn btn-outline-primary">
                      Ver Detalles
                    </Link>
                    <AddToCartButton product={product} />
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
        
        <div className="text-center mt-4">
          <Link to="/kits" className="btn btn-primary">
            Ver Todos los Kits
          </Link>
        </div>
      </div>
    </section>
  );
};

export default FeaturedProducts;