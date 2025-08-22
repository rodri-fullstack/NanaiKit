import React, { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import './SearchPage.css';

const SearchPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const [searchTerm, setSearchTerm] = useState(searchParams.get('q') || '');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [noResults, setNoResults] = useState(false);
  const [activeFilter, setActiveFilter] = useState('all');

  // Datos simulados para búsqueda
  const mockData = {
    kits: [
      {
        id: 1,
        type: 'kit',
        name: "Kit Rutina",
        description: "Para recuperar hábitos y estructura diaria con amabilidad.",
        price: 9990,
        image: "/assets/images/kitRutinaHome.png",
        category: "Nivel 1 Preventivo"
      },
      {
        id: 2,
        type: 'kit',
        name: "Kit Gratitud",
        description: ".Para valorar lo cotidiano y cultivar pensamientos positivos",
        price: 12990,
        image: "/assets/images/kitGratitudHome.png",
        category: "Nivel 1 Preventivo"
      },
      {
        id: 3,
        type: 'kit',
        name: "Kit Calma",
        description: "Para bajar la intensidad, respirar y volver al cuerpo.",
        price: 15990",
        image: "/assets/images/kitCalma.png",
        category: "Nivel 2 Alerta"
      },
      {
        id: 4,
        type: 'kit',
        name: "Kit Renace",
        description: "Kit esencial para comenzar tu journey",
        price: 32000,
        image: "/assets/images/KitRenace.png",
        category: "Esencial"
      },
      {
        id: 5,
        type: 'kit',
        name: "Kit Contención",
        description: "Kit relajante con esencia de lavanda",
        price: 25000,
        image: "/assets/images/KitContencion.png",
        category: "Relajación"
      }
    ],
    blog: [
      {
        id: 1,
        type: 'blog',
        title: "5 Rutinas de Autocuidado para Comenzar tu Día",
        excerpt: "Descubre cómo crear rutinas matutinas que transformen tu bienestar diario",
        category: "Bienestar",
        readTime: "5 min"
      },
      {
        id: 2,
        type: 'blog',
        title: "La Importancia de la Gratitud en tu Vida Diaria",
        excerpt: "Aprende cómo practicar la gratitud puede mejorar tu estado de ánimo",
        category: "Mindfulness",
        readTime: "7 min"
      },
      {
        id: 3,
        type: 'blog',
        title: "Aromaterapia: Guía Completa para Principiantes",
        excerpt: "Todo lo que necesitas saber sobre aceites esenciales y sus beneficios",
        category: "Aromaterapia",
        readTime: "10 min"
      }
    ],
    pages: [
      {
        id: 1,
        type: 'page',
        title: "Test de Bienestar",
        description: "Descubre qué kit es perfecto para ti con nuestro test personalizado",
        url: "/test"
      },
      {
        id: 2,
        type: 'page',
        title: "Sobre Nosotres",
        description: "Conoce la historia y misión de Nanai Kit",
        url: "/nosotres"
      }
    ]
  };

  // Función de distancia de Levenshtein para tolerancia a errores
  const levenshteinDistance = (str1, str2) => {
    const matrix = [];
    for (let i = 0; i <= str2.length; i++) {
      matrix[i] = [i];
    }
    for (let j = 0; j <= str1.length; j++) {
      matrix[0][j] = j;
    }
    for (let i = 1; i <= str2.length; i++) {
      for (let j = 1; j <= str1.length; j++) {
        if (str2.charAt(i - 1) === str1.charAt(j - 1)) {
          matrix[i][j] = matrix[i - 1][j - 1];
        } else {
          matrix[i][j] = Math.min(
            matrix[i - 1][j - 1] + 1,
            matrix[i][j - 1] + 1,
            matrix[i - 1][j] + 1
          );
        }
      }
    }
    return matrix[str2.length][str1.length];
  };

  // Función de búsqueda con tolerancia a errores
  const searchItems = (term) => {
    if (!term.trim()) {
      setResults([]);
      setNoResults(false);
      return;
    }

    const searchTermLower = term.toLowerCase();
    const allItems = [...mockData.kits, ...mockData.blog, ...mockData.pages];
    const filteredResults = [];

    allItems.forEach(item => {
      const searchableText = [
        item.name || item.title,
        item.description || item.excerpt,
        item.category
      ].filter(Boolean).join(' ').toLowerCase();

      const words = searchTermLower.split(' ');
      let hasMatch = false;

      words.forEach(word => {
        if (word.length <= 3) {
          // Para palabras cortas, búsqueda exacta
          if (searchableText.includes(word)) {
            hasMatch = true;
          }
        } else {
          // Para palabras largas, permitir hasta 2 errores
          const searchWords = searchableText.split(' ');
          searchWords.forEach(searchWord => {
            if (levenshteinDistance(word, searchWord) <= 2) {
              hasMatch = true;
            }
          });
        }
      });

      if (hasMatch) {
        filteredResults.push(item);
      }
    });

    // Filtrar por tipo si hay un filtro activo
    const finalResults = activeFilter === 'all' 
      ? filteredResults 
      : filteredResults.filter(item => item.type === activeFilter);

    setResults(finalResults);
    setNoResults(finalResults.length === 0);
  };

  // Manejar cambios en el input de búsqueda
  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearchTerm(value);
    setSearchParams(value ? { q: value } : {});
  };

  // Manejar filtros
  const handleFilterChange = (filter) => {
    setActiveFilter(filter);
  };

  // Limpiar búsqueda
  const clearSearch = () => {
    setSearchTerm('');
    setResults([]);
    setNoResults(false);
    setSearchParams({});
  };

  // Formatear precio
  const formatPrice = (price) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      minimumFractionDigits: 0
    }).format(price);
  };

  // Efecto para búsqueda automática
  useEffect(() => {
    const delayedSearch = setTimeout(() => {
      if (searchTerm) {
        setLoading(true);
        searchItems(searchTerm);
        setLoading(false);
      } else {
        setResults([]);
        setNoResults(false);
      }
    }, 300);

    return () => clearTimeout(delayedSearch);
  }, [searchTerm, activeFilter]);

  return (
    <div className="search-page">
      <div className="search-header">
        <div className="container">
          <h1 className="search-title">Buscar en Nanai Kit</h1>
          <p className="search-subtitle">Encuentra kits, artículos del blog y más</p>
          
          {/* Barra de búsqueda principal */}
          <div className="search-main-container">
            <div className="search-input-wrapper">
              <input
                type="text"
                className="search-main-input"
                placeholder="¿Qué estás buscando?"
                value={searchTerm}
                onChange={handleSearchChange}
                autoFocus
              />
              <div className="search-main-icon">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M21 21L16.514 16.506L21 21ZM19 10.5C19 15.194 15.194 19 10.5 19C5.806 19 2 15.194 2 10.5C2 5.806 5.806 2 10.5 2C15.194 2 19 5.806 19 10.5Z" stroke="#8B5CF6" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
              </div>
            </div>
            
            {searchTerm && (
              <button className="clear-search-btn" onClick={clearSearch}>
                Limpiar
              </button>
            )}
          </div>

          {/* Filtros */}
          <div className="search-filters">
            <button 
              className={`filter-btn ${activeFilter === 'all' ? 'active' : ''}`}
              onClick={() => handleFilterChange('all')}
            >
              Todo
            </button>
            <button 
              className={`filter-btn ${activeFilter === 'kit' ? 'active' : ''}`}
              onClick={() => handleFilterChange('kit')}
            >
              Kits
            </button>
            <button 
              className={`filter-btn ${activeFilter === 'blog' ? 'active' : ''}`}
              onClick={() => handleFilterChange('blog')}
            >
              Blog
            </button>
            <button 
              className={`filter-btn ${activeFilter === 'page' ? 'active' : ''}`}
              onClick={() => handleFilterChange('page')}
            >
              Páginas
            </button>
          </div>
        </div>
      </div>

      <div className="search-results">
        <div className="container">
          {loading && (
            <div className="search-loading">
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Buscando...</span>
              </div>
            </div>
          )}

          {searchTerm && !loading && (
            <div className="search-info">
              <p>Resultados para: <strong>"{searchTerm}"</strong></p>
              {results.length > 0 && (
                <p className="results-count">{results.length} resultado{results.length !== 1 ? 's' : ''} encontrado{results.length !== 1 ? 's' : ''}</p>
              )}
            </div>
          )}

          {noResults && (
            <div className="no-results">
              <div className="no-results-icon">
                <svg width="64" height="64" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M21 21L16.514 16.506L21 21ZM19 10.5C19 15.194 15.194 19 10.5 19C5.806 19 2 15.194 2 10.5C2 5.806 5.806 2 10.5 2C15.194 2 19 5.806 19 10.5Z" stroke="#9CA3AF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
              </div>
              <h4>No se encontraron resultados</h4>
              <p>No encontramos nada para "{searchTerm}". Intenta con otros términos de búsqueda.</p>
              <button className="btn btn-primary" onClick={clearSearch}>
                Ver todo el contenido
              </button>
            </div>
          )}

          {results.length > 0 && (
            <div className="search-results-grid">
              {results.map(item => (
                <div key={`${item.type}-${item.id}`} className={`result-card ${item.type}-card`}>
                  {item.type === 'kit' && (
                    <>
                      <div className="result-image">
                        <img src={item.image} alt={item.name} />
                      </div>
                      <div className="result-content">
                        <span className="result-type">Kit</span>
                        <h3 className="result-title">{item.name}</h3>
                        <p className="result-description">{item.description}</p>
                        <div className="result-meta">
                          <span className="result-price">{formatPrice(item.price)}</span>
                          <span className="result-category">{item.category}</span>
                        </div>
                        <Link to={`/productos/${item.id}`} className="result-link">
                          Ver Kit
                        </Link>
                      </div>
                    </>
                  )}
                  
                  {item.type === 'blog' && (
                    <div className="result-content">
                      <span className="result-type">Blog</span>
                      <h3 className="result-title">{item.title}</h3>
                      <p className="result-description">{item.excerpt}</p>
                      <div className="result-meta">
                        <span className="result-category">{item.category}</span>
                        <span className="result-read-time">{item.readTime}</span>
                      </div>
                      <Link to={`/blog/${item.id}`} className="result-link">
                        Leer Artículo
                      </Link>
                    </div>
                  )}
                  
                  {item.type === 'page' && (
                    <div className="result-content">
                      <span className="result-type">Página</span>
                      <h3 className="result-title">{item.title}</h3>
                      <p className="result-description">{item.description}</p>
                      <Link to={item.url} className="result-link">
                        Visitar Página
                      </Link>
                    </div>
                  )}
                </div>
              ))}
            </div>
          )}

          {!searchTerm && (
            <div className="search-suggestions">
              <h3>Sugerencias de búsqueda</h3>
              <div className="suggestions-grid">
                <div className="suggestion-item" onClick={() => setSearchTerm('rutina')}>
                  <span>Rutina</span>
                </div>
                <div className="suggestion-item" onClick={() => setSearchTerm('gratitud')}>
                  <span>Gratitud</span>
                </div>
                <div className="suggestion-item" onClick={() => setSearchTerm('lavanda')}>
                  <span>Lavanda</span>
                </div>
                <div className="suggestion-item" onClick={() => setSearchTerm('bienestar')}>
                  <span>Bienestar</span>
                </div>
                <div className="suggestion-item" onClick={() => setSearchTerm('autocuidado')}>
                  <span>Autocuidado</span>
                </div>
                <div className="suggestion-item" onClick={() => setSearchTerm('mindfulness')}>
                  <span>Mindfulness</span>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SearchPage;