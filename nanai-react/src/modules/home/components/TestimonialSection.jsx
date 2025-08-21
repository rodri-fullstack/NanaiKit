import React from 'react';

const TestimonialSection = () => {
  // Datos simulados de testimonios
  const testimonials = [
    {
      id: 1,
      name: 'María González',
      text: 'El Kit Rutina ha transformado mi forma de cuidarme. Los productos son de excelente calidad y realmente noto la diferencia en mi bienestar diario.',
      rating: 5
    },
    {
      id: 2,
      name: 'Carlos Rodríguez',
      text: 'Hice el test y me recomendaron el Kit Gratitud. Estaba escéptico al principio, pero después de usarlo por un mes, puedo decir que ha mejorado mi estado de ánimo significativamente.',
      rating: 4
    },
    {
      id: 3,
      name: 'Laura Martínez',
      text: 'Lo que más me gusta de Nanai Kit es la personalización. Recibí productos específicos para mis necesidades y eso hace toda la diferencia.',
      rating: 5
    }
  ];

  // Función para renderizar estrellas según la calificación
  const renderStars = (rating) => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <span key={i} className={i <= rating ? 'text-warning' : 'text-muted'}>
          ★
        </span>
      );
    }
    return stars;
  };

  return (
    <section className="testimonial-section py-5">
      <div className="container">
        <h2 className="text-center mb-5">Lo que dicen nuestros clientes</h2>
        
        <div className="row">
          {testimonials.map(testimonial => (
            <div key={testimonial.id} className="col-md-4 mb-4">
              <div className="card h-100 border-0 shadow-sm">
                <div className="card-body">
                  <div className="mb-3">
                    {renderStars(testimonial.rating)}
                  </div>
                  <p className="card-text mb-4">"{testimonial.text}"</p>
                  <p className="card-text fw-bold mb-0">{testimonial.name}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default TestimonialSection;