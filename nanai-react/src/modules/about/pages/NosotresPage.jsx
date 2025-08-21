import React from 'react';
import './NosotresPage.css';

const NosotresPage = () => {
  return (
    <div className="nosotres-page">
      {/* Sección principal - Desde el corazón de Nanai Kit */}
      <section className="nosotres-hero">
        <div className="nosotres-container">
          <div className="nosotres-content">
            <h1 className="nosotres-title">Desde el corazón de Nanai Kit</h1>
            <p className="nosotres-description">
              Nuestro proyecto nace desde el deseo de acercar la salud mental a las personas desde la vida cotidiana, de forma 
              cálida, accesible y significativa. En Nanai Kit, creemos que todos merecen un espacio seguro para reconectar 
              consigo mismos.
            </p>
            <p className="nosotres-description">
              Todo comenzó cuando, en medio de nuestras propias experiencias de estrés y ansiedad, notamos lo difícil que era 
              encontrar herramientas simples, prácticas y emocionales que acompañaran esos momentos difíciles. Así surgió la 
              idea de crear kits de autoayuda con amor, dedicación y enfoque en el bienestar emocional.
            </p>
          </div>
          <div className="nosotres-illustration">
            <div className="nanai-logo-illustration">
              <img 
                src="/assets/images/logo3.png" 
                alt="Nanai Kit Logo" 
                className="logo-image"
              />
            </div>
          </div>
        </div>
      </section>

      {/* Sección Nuestra Misión */}
      <section className="nosotres-mission">
        <div className="nosotres-container">
          <h2 className="section-title">Nuestra misión</h2>
          <p className="mission-text">
            Nuestra misión es simple: ofrecer productos y recursos de alta calidad, que promuevan y apoyen el bienestar 
            emocional de las personas, promoviendo el autocuidado, la reflexión y la contención emocional en sus distintas 
            formas.
          </p>
          <p className="mission-text">
            De esta forma, poder brindar apoyo inmediato y accesible de apoyo para el autocuidado de la salud mental.
          </p>
        </div>
      </section>

      {/* Sección Nuestros Valores */}
      <section className="nosotres-values">
        <div className="nosotres-container">
          <h2 className="section-title">Nuestros valores</h2>
          <p className="values-intro">
            En Nanai Kit buscamos entregar el mayor acceso posible, de forma oportuna y económica para sostener el 
            autocuidado emocional en el territorio nacional.
          </p>
          <p className="values-intro">
            Además, nos guiamos por una serie de valores que reflejan nuestro compromiso con la calidad y el bienestar de 
            nuestros clientes.
          </p>
          <p className="values-intro">Estos valores son:</p>
          
          <div className="values-list">
            <div className="value-item">
              <h3 className="value-title">Empatía:</h3>
              <p className="value-description">Escuchamos, comprendemos y acompañamos sin juzgar.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Cuidado:</h3>
              <p className="value-description">Cada producto está pensado para abrazar emocionalmente a quien lo recibe.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Accesibilidad:</h3>
              <p className="value-description">Queremos que el bienestar esté al alcance de todas las personas.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Compromiso social:</h3>
              <p className="value-description">Creemos en la importancia de normalizar y hablar sobre la salud mental.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Creatividad:</h3>
              <p className="value-description">Diseñamos kits con alma, mezclando arte, palabras, aromas y emociones.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Bienestar:</h3>
              <p className="value-description">Creemos en el poder del autocuidado y diseñamos nuestros productos para promover la relajación, el equilibrio y la armonía.</p>
            </div>
            <div className="value-item">
              <h3 className="value-title">Comunidad:</h3>
              <p className="value-description">Fomentamos una comunidad de amantes del autocuidado, donde compartimos información, experiencias y consejos para vivir una vida más plena y consciente.</p>
            </div>
          </div>
        </div>
      </section>

      {/* Sección La familia de Nanai Kit */}
      <section className="nosotres-team">
        <div className="nosotres-container">
          <h2 className="section-title">La familia de Nanai Kit</h2>
          <p className="team-intro">
            Detrás de Nanai Kit hay un equipo de personas interesadas por el autocuidado y el bienestar, comprometidas con la 
            entrega y distribución de productos que marquen la diferencia en la vida de nuestros clientes.
          </p>
          <p className="team-intro">
            Cada miembro del equipo aporta su experiencia y conocimientos para garantizar la calidad y la excelencia de 
            nuestros productos y servicios.
          </p>
          <p className="team-intro">Conoce a algunos de los miembros clave de nuestro equipo:</p>
          
          <div className="team-section">
            <div className="team-image-container">
              <img 
                src="/assets/images/Nosotres.png" 
                alt="¿Quiénes somos? - Equipo de Nanai Kit" 
                className="team-image"
              />
            </div>
          </div>
          
          <p className="team-closing">
            Juntos, trabajamos con alegría y dedicación para ofrecerte productos y experiencias que te ayuden a cuidar de ti 
            mismo y reconectar contigo en esos momentos donde más lo necesitas.
          </p>
        </div>
      </section>
    </div>
  );
};

export default NosotresPage;