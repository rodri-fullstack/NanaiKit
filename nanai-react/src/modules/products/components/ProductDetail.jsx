import React from 'react';
import { Link } from 'react-router-dom';
import AddToCartButton from '../../cart/components/AddToCartButton';

const ProductDetail = ({ product }) => {
  if (!product) return null;

  const formatPrice = (price) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      minimumFractionDigits: 0
    }).format(price);
  };

  return (
    <div className="product-detail">
      <div className="product-detail-container">
        <div className="product-image-container">
          <img 
            src={product.image} 
            alt={product.name} 
            className="product-image" 
          />
        </div>
        
        <div className="product-info-container">
          <h1 className="product-title">{product.name}</h1>
          <p className="product-price">{formatPrice(product.price)}</p>
          
          <div className="product-description">
            <p>{product.longDescription}</p>
          </div>
          
          <div className="product-actions">
            <AddToCartButton product={product} />
            <Link to="/carrito" className="btn btn-outline-primary">
              Ver Carrito
            </Link>
          </div>
          
          <div className="product-details">
            <div className="product-benefits">
              <h3>Beneficios</h3>
              <ul>
                {product.benefits.map((benefit, index) => (
                  <li key={index}>{benefit}</li>
                ))}
              </ul>
            </div>
            
            <div className="product-includes">
              <h3>Incluye</h3>
              <ul>
                {product.includes.map((item, index) => (
                  <li key={index}>{item}</li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
      
      <div className="related-products">
        <h2>También te puede interesar</h2>
        <div className="related-products-container">
          <div className="related-product">
            <img 
              src="/assets/images/kitGratitudHome.png" 
              alt="Kit Gratitud" 
              className="related-product-image" 
            />
            <h3>Kit Gratitud</h3>
            <Link to="/productos/2" className="btn btn-sm btn-outline-primary">
              Ver Detalles
            </Link>
          </div>
          
          <div className="related-product">
            <img 
              src="/assets/images/kitDestacadoHome.png" 
              alt="Kit Destacado" 
              className="related-product-image" 
            />
            <h3>Kit Destacado</h3>
            <Link to="/productos/3" className="btn btn-sm btn-outline-primary">
              Ver Detalles
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;