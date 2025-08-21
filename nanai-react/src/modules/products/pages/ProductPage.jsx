import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ProductDetail from '../components/ProductDetail';
import '../styles/ProductPage.css';

const ProductPage = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Simulación de carga de datos del producto
    const fetchProduct = () => {
      setLoading(true);
      
      // Simulamos productos para demostración
      const mockProducts = [
        {
          id: 1,
          name: 'Kit Rutina',
          description: 'Productos esenciales para tu rutina diaria de autocuidado.',
          longDescription: 'El Kit Rutina está diseñado para ayudarte a establecer hábitos saludables de autocuidado en tu día a día. Incluye productos naturales cuidadosamente seleccionados que te ayudarán a mantener una rutina constante y efectiva.',
          image: '/assets/images/kitRutinaHome.png',
          price: 35000,
          benefits: [
            'Mejora tu rutina diaria de autocuidado',
            'Productos 100% naturales',
            'Incluye guía de uso personalizada',
            'Envío gratuito a todo Chile'
          ],
          includes: [
            'Aceite esencial de lavanda',
            'Vela aromática',
            'Sales de baño',
            'Guía de rutinas diarias'
          ]
        },
        {
          id: 2,
          name: 'Kit Gratitud',
          description: 'Elementos para practicar la gratitud y mejorar tu bienestar emocional.',
          longDescription: 'El Kit Gratitud está diseñado para ayudarte a cultivar una actitud de agradecimiento en tu vida diaria. Incluye herramientas y productos que te invitan a reconocer y apreciar las cosas positivas que te rodean, mejorando así tu bienestar emocional.',
          image: '/assets/images/kitGratitudHome.png',
          price: 28000,
          benefits: [
            'Fomenta una actitud positiva',
            'Reduce el estrés y la ansiedad',
            'Mejora la calidad del sueño',
            'Aumenta la sensación de bienestar'
          ],
          includes: [
            'Diario de gratitud',
            'Aceite esencial de bergamota',
            'Tarjetas de afirmaciones positivas',
            'Guía de prácticas de gratitud'
          ]
        },
        {
          id: 3,
          name: 'Kit Destacado',
          description: 'Nuestra selección premium de productos para un autocuidado completo.',
          longDescription: 'El Kit Destacado es nuestra selección premium de productos para un autocuidado completo. Incluye una combinación perfecta de elementos para cuidar tu cuerpo, mente y espíritu, proporcionándote una experiencia integral de bienestar.',
          image: '/assets/images/kitDestacadoHome.png',
          price: 42000,
          benefits: [
            'Experiencia completa de autocuidado',
            'Productos premium de alta calidad',
            'Combinación perfecta para cuerpo y mente',
            'Presentación elegante, ideal para regalo'
          ],
          includes: [
            'Difusor de aromas',
            'Set de aceites esenciales',
            'Antifaz de seda para dormir',
            'Infusiones orgánicas',
            'Guía completa de autocuidado'
          ]
        }
      ];

      // Buscar el producto por ID
      setTimeout(() => {
        const foundProduct = mockProducts.find(p => p.id === parseInt(productId));
        
        if (foundProduct) {
          setProduct(foundProduct);
          setLoading(false);
        } else {
          setError('Producto no encontrado');
          setLoading(false);
        }
      }, 500); // Simulamos un pequeño retraso de carga
    };

    fetchProduct();
  }, [productId]);

  if (loading) {
    return (
      <div className="product-page-loading">
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Cargando...</span>
        </div>
        <p>Cargando información del producto...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="product-page-error">
        <h2>Error</h2>
        <p>{error}</p>
        <button 
          className="btn btn-primary" 
          onClick={() => window.history.back()}
        >
          Volver
        </button>
      </div>
    );
  }

  return (
    <div className="product-page">
      {product && <ProductDetail product={product} />}
      
      <div className="product-page-decoration">
        <img 
          src="/images/cart-decoration.svg" 
          alt="Decoración" 
          className="decoration-image" 
        />
      </div>
    </div>
  );
};

export default ProductPage;