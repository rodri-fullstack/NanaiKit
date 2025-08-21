import React from 'react';
import { useCart } from '../context/CartContext';

const OrderSummary = () => {
  const { cartItems, clearCart, getCartSubtotal } = useCart();
  
  const subtotal = getCartSubtotal();
  const shipping = 0; // Envío gratis
  const total = subtotal + shipping;

  const handleCheckout = () => {
    // Aquí se implementaría la lógica para proceder al pago
    alert('Redirigiendo al proceso de pago...');
  };

  const handleClearCart = () => {
    if (window.confirm('¿Estás seguro de que deseas vaciar tu carrito?')) {
      clearCart();
    }
  };

  return (
    <div className="order-summary">
      <h2 className="summary-title">Resumen del Pedido</h2>
      
      <div className="summary-row">
        <span>Subtotal</span>
        <span>${subtotal.toLocaleString()}</span>
      </div>
      
      <div className="summary-row">
        <span>Envío</span>
        <span>{shipping === 0 ? 'Gratis' : `$${shipping.toLocaleString()}`}</span>
      </div>
      
      <div className="summary-row total">
        <span>Total</span>
        <span>${total.toLocaleString()}</span>
      </div>
      
      <div className="cart-actions">
        <button 
          className="btn-proceed" 
          onClick={handleCheckout}
          disabled={cartItems.length === 0}
        >
          Proceder al Pago
        </button>
        
        <button 
          className="btn-clear" 
          onClick={handleClearCart}
          disabled={cartItems.length === 0}
        >
          Vaciar Carrito
        </button>
      </div>
      
      <img 
        src="/images/cart-decoration.svg" 
        alt="" 
        className="cart-image" 
        aria-hidden="true"
      />
    </div>
  );
};

export default OrderSummary;