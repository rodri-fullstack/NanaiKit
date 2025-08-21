import React from 'react';
import { Link } from 'react-router-dom';

const EmptyCart = () => {
  return (
    <div className="empty-cart">
      <p>Tu carrito está vacío</p>
      <Link to="/productos/1" className="btn-primary">
        Ver Productos
      </Link>
    </div>
  );
};

export default EmptyCart;