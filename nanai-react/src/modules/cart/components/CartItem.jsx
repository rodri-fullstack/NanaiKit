import React from 'react';
import { useCart } from '../context/CartContext';

const CartItem = ({ product }) => {
  const { updateQuantity, removeFromCart } = useCart();

  const handleQuantityChange = (e) => {
    const newQuantity = parseInt(e.target.value);
    if (newQuantity > 0) {
      updateQuantity(product.id, newQuantity);
    }
  };

  const handleRemove = () => {
    removeFromCart(product.id);
  };

  return (
    <div className="cart-item">
      <div className="product-info">
        <h3>{product.name}</h3>
      </div>
      <div className="item-price">${product.price.toLocaleString()}</div>
      <div className="item-quantity">
        <input 
          type="number" 
          min="1" 
          value={product.quantity} 
          onChange={handleQuantityChange} 
        />
      </div>
      <div className="item-total">
        ${(product.price * product.quantity).toLocaleString()}
      </div>
      <button className="remove-item" onClick={handleRemove}>
        ×
      </button>
    </div>
  );
};

export default CartItem;