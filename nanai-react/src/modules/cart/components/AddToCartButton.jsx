import React from 'react';
import { useCart } from '../context/CartContext';

const AddToCartButton = ({ product }) => {
  const { addToCart } = useCart();

  const handleAddToCart = () => {
    addToCart(product);
    // Opcional: Mostrar una notificación de que el producto se agregó al carrito
    alert(`${product.name} ha sido añadido al carrito`);
  };

  return (
    <button 
      className="btn btn-primary" 
      onClick={handleAddToCart}
    >
      Añadir al Carrito
    </button>
  );
};

export default AddToCartButton;