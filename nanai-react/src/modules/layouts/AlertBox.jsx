import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const AlertBox = () => {
  const [alert, setAlert] = useState(null);
  const location = useLocation();

  useEffect(() => {
    // Verificar si hay un mensaje en el estado de navegación
    if (location.state?.message) {
      setAlert({
        type: location.state.type || 'success',
        message: location.state.message
      });
      
      // Limpiar el mensaje después de 5 segundos
      const timer = setTimeout(() => {
        setAlert(null);
      }, 5000);
      
      return () => clearTimeout(timer);
    }
  }, [location]);

  if (!alert) return null;

  return (
    <div className={`alert alert-${alert.type} alert-dismissible fade show mb-4`} role="alert">
      {alert.message}
      <button 
        type="button" 
        className="btn-close" 
        onClick={() => setAlert(null)}
        aria-label="Close"
      ></button>
    </div>
  );
};

export default AlertBox;