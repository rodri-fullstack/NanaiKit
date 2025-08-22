import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './TestPage.css';

const TestPage = () => {
  const navigate = useNavigate();
  const [answers, setAnswers] = useState({
    mood: '',
    area: '',
    time: ''
  });
  const [showModal, setShowModal] = useState(false);
  const [recommendedKit, setRecommendedKit] = useState(null);

  const questions = {
    mood: {
      title: '¿Cuál es tu estado de ánimo general?',
      options: ['Relajado', 'Estresado', 'Ansioso', 'Triste', 'Energizado']
    },
    area: {
      title: '¿Qué área de tu vida te gustaría mejorar?',
      options: ['Sueño', 'Estrés', 'Energía', 'Concentración', 'Estado de ánimo']
    },
    time: {
      title: '¿Cuánto tiempo tienes para dedicar a tu bienestar hoy?',
      options: ['15 minutos', '30 minutos', '1 hora', 'Más de 1 hora']
    }
  };

  const kits = {
    'Kit Rutina': {
      id: 1,
      name: 'Kit Rutina',
      description: 'Perfecto para establecer rutinas saludables y mantener el equilibrio diario.',
      image: '/assets/images/kitRutinaHome.png',
      price: 35000
    },
    'Kit Gratitud': {
      id: 2,
      name: 'Kit Gratitud',
      description: 'Ideal para cultivar la gratitud y mejorar tu estado de ánimo.',
      image: '/assets/images/kitGratitudHome.png',
      price: 28000
    },
    'Kit Destacado': {
      id: 3,
      name: 'Kit SOS Ansiedad',
      description: 'Nuestro kit más completo para un bienestar integral.',
      image: '/assets/images/kitDestacadoHome.png',
      price: 42000
    },
    'Kit Nanai': {
      id: 4,
      name: 'Kit Nanai',
      description: 'Kit esencial para comenzar tu journey de autocuidado.',
      image: '/assets/images/nanaiKitHome.png',
      price: 32000
    },
    'Kit Lavanda': {
      id: 5,
      name: 'Kit Lavanda',
      description: 'Kit relajante con esencia de lavanda para reducir el estrés.',
      image: '/assets/images/lavandaHome.png',
      price: 25000
    }
  };

  const handleAnswerSelect = (questionType, answer) => {
    setAnswers(prev => ({
      ...prev,
      [questionType]: answer
    }));
  };

  const getRecommendedKit = () => {
    const { mood, area, time } = answers;
    
    // Lógica de recomendación basada en las respuestas
    if (mood === 'Estresado' || area === 'Estrés') {
      return kits['Kit Lavanda'];
    } else if (mood === 'Triste' || area === 'Estado de ánimo') {
      return kits['Kit Gratitud'];
    } else if (area === 'Sueño' || area === 'Concentración') {
      return kits['Kit Rutina'];
    } else if (area === 'Energía' || mood === 'Energizado') {
      return kits['Kit Nanai'];
    } else {
      return kits['Kit Destacado'];
    }
  };

  const handleSubmit = () => {
    if (answers.mood && answers.area && answers.time) {
      const kit = getRecommendedKit();
      setRecommendedKit(kit);
      setShowModal(true);
    } else {
      alert('Por favor responde todas las preguntas antes de continuar.');
    }
  };

  const handleGoToKit = () => {
    setShowModal(false);
    navigate(`/kits?highlight=${recommendedKit.id}`);
  };

  const formatPrice = (price) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      minimumFractionDigits: 0
    }).format(price);
  };

  return (
    <div className="test-page">
      <div className="test-container">
        <div className="test-header">
          <h1 className="test-title">Nanai Test</h1>
          <h2 className="test-subtitle">¿Cómo te sientes hoy?</h2>
          <p className="test-description">
            Responde a unas pocas preguntas para obtener recomendaciones personalizadas para tu bienestar y así obtener el mejor kit.
          </p>
        </div>

        <div className="test-questions">
          {/* Pregunta 1: Estado de ánimo */}
          <div className="question-section">
            <h3 className="question-title">
              1. {questions.mood.title}
            </h3>
            <div className="options-grid">
              {questions.mood.options.map((option, index) => (
                <button
                  key={index}
                  className={`option-button ${
                    answers.mood === option ? 'selected' : ''
                  }`}
                  onClick={() => handleAnswerSelect('mood', option)}
                >
                  {option}
                </button>
              ))}
            </div>
          </div>

          {/* Pregunta 2: Área de mejora */}
          <div className="question-section">
            <h3 className="question-title">
              2. {questions.area.title}
            </h3>
            <div className="options-grid">
              {questions.area.options.map((option, index) => (
                <button
                  key={index}
                  className={`option-button ${
                    answers.area === option ? 'selected' : ''
                  }`}
                  onClick={() => handleAnswerSelect('area', option)}
                >
                  {option}
                </button>
              ))}
            </div>
          </div>

          {/* Pregunta 3: Tiempo disponible */}
          <div className="question-section">
            <h3 className="question-title">
              3. {questions.time.title}
            </h3>
            <div className="options-grid">
              {questions.time.options.map((option, index) => (
                <button
                  key={index}
                  className={`option-button ${
                    answers.time === option ? 'selected' : ''
                  }`}
                  onClick={() => handleAnswerSelect('time', option)}
                >
                  {option}
                </button>
              ))}
            </div>
          </div>
        </div>

        <div className="test-submit">
          <button className="submit-button" onClick={handleSubmit}>
            ⭐ Ver mis Recomendaciones
          </button>
        </div>
      </div>

      {/* Modal de resultado */}
      {showModal && recommendedKit && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2>¡Tu Kit Ideal!</h2>
              <button 
                className="modal-close"
                onClick={() => setShowModal(false)}
              >
                ×
              </button>
            </div>
            <div className="modal-body">
              <div className="kit-recommendation">
                <img 
                  src={recommendedKit.image} 
                  alt={recommendedKit.name}
                  className="kit-image" id="resultado-kit"
                />
                <div className="kit-info">
                  <h3 className="kit-name">{recommendedKit.name}</h3>
                  <p className="kit-description">{recommendedKit.description}</p>
                  <p className="kit-price">{formatPrice(recommendedKit.price)}</p>
                </div>
              </div>
            </div>
            <div className="modal-footer">
              <button 
                className="btn-secondary"
                onClick={() => setShowModal(false)}
              >
                Cerrar
              </button>
              <button 
                className="btn-primary"
                onClick={handleGoToKit}
              >
                Ver Kit
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default TestPage;