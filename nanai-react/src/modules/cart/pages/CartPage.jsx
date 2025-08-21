import React from 'react';
import { Link } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import '../styles/CartPage.css';

const CartPage = () => {
  const { 
    cartItems, 
    removeFromCart, 
    updateQuantity, 
    clearCart, 
    getSubtotal 
  } = useCart();

  // Calcular el subtotal
  const subtotal = getSubtotal();
  // Envío gratuito
  const shipping = 0;
  // Total final
  const total = subtotal + shipping;

  // Formatear precio en pesos chilenos
  const formatPrice = (price) => {
    return `$${price.toLocaleString('es-CL')}`;
  };

  // Manejar cambio de cantidad
  const handleQuantityChange = (productId, newQuantity) => {
    updateQuantity(productId, parseInt(newQuantity));
  };

  return (
    <div className="cart-page">
      <h1 className="cart-title">Tu Carrito</h1>
      
      {cartItems.length === 0 ? (
        <div className="empty-cart">
          <p>Tu carrito está vacío</p>
          <Link to="/kits" className="btn-primary">Ver Kits</Link>
        </div>
      ) : (
        <div className="cart-container">
          {/* Tabla de productos */}
          <div className="cart-items">
            <div className="cart-header">
              <div className="product-header">Producto</div>
              <div className="price-header">Precio</div>
              <div className="quantity-header">Cantidad</div>
              <div className="total-header">Total</div>
            </div>
            
            {cartItems.map(item => (
              <div key={item.id} className="cart-item">
                <div className="product-info">
                  <h3>{item.name}</h3>
                </div>
                <div className="item-price">
                  {formatPrice(item.price)}
                </div>
                <div className="item-quantity">
                  <input 
                    type="number" 
                    min="1" 
                    value={item.quantity} 
                    onChange={(e) => handleQuantityChange(item.id, e.target.value)}
                  />
                </div>
                <div className="item-total">
                  {formatPrice(item.price * item.quantity)}
                </div>
                <button 
                  className="remove-item" 
                  onClick={() => removeFromCart(item.id)}
                  aria-label="Eliminar producto"
                >
                  ×
                </button>
              </div>
            ))}
          </div>
          
          {/* Resumen del pedido */}
          <div className="order-summary">
            <h2 className="summary-title">Resumen del Pedido</h2>
            
            <div className="summary-row">
              <span>Subtotal</span>
              <span>{formatPrice(subtotal)}</span>
            </div>
            
            <div className="summary-row">
              <span>Envío</span>
              <span>{shipping === 0 ? 'Gratis' : formatPrice(shipping)}</span>
            </div>
            
            <div className="summary-row total">
              <span>Total</span>
              <span>{formatPrice(total)}</span>
            </div>
            
            <div className="cart-actions">
              <button className="btn-proceed" onClick={() => window.location.href = '/checkout'}>
                Proceder al Pago
              </button>
              <button className="btn-clear" onClick={clearCart}>
                Vaciar Carrito
              </button>
            </div>
          </div>
        </div>
      )}
      
      {/* Enlaces de ayuda */}
      <div className="help-links">
        <Link to="/preguntas-frecuentes">Preguntas Frecuentes</Link>
        <Link to="/privacidad">Política de Privacidad</Link>
        <Link to="/terminos">Términos de Servicio</Link>
      </div>
      
      {/* Iconos de redes sociales */}
      <div className="social-icons">
        <a href="#" aria-label="Instagram">
          <img src="/assets/images/icon-instagram.png" alt="Instagram" />
        </a>
        <a href="#" aria-label="Facebook">
          <img src="/assets/images/icon-facebook.png" alt="Facebook" />
        </a>
        <a href="#" aria-label="Twitter">
          <img src="/assets/images/icon-equis.png" alt="Twitter" />
        </a>
      </div>
    </div>
  );
};

export default CartPage;