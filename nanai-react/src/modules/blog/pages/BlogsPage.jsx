import React, { useState, useEffect } from 'react';
import BlogCard from '../components/BlogCard';
import { blogPosts, blogCategories, getPostsByCategory } from '../data/blogData';
import './BlogsPage.css';

const BlogsPage = () => {
  const [selectedCategory, setSelectedCategory] = useState('Todos');
  const [filteredPosts, setFilteredPosts] = useState(blogPosts);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    let posts = getPostsByCategory(selectedCategory);
    
    if (searchTerm) {
      posts = posts.filter(post => 
        post.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        post.excerpt.toLowerCase().includes(searchTerm.toLowerCase()) ||
        post.tags.some(tag => tag.toLowerCase().includes(searchTerm.toLowerCase()))
      );
    }
    
    setFilteredPosts(posts);
  }, [selectedCategory, searchTerm]);

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  return (
    <div className="blogs-page">
      {/* Hero Section */}
      <section className="blogs-hero">
        <div className="container">
          <div className="blogs-hero-content">
            <h1 className="blogs-hero-title">
              Nanai Blog
            </h1>
            <p className="blogs-hero-subtitle">
              Descubre artículos, consejos y recursos para cuidar tu bienestar emocional
            </p>
            <div className="blogs-search">
              <div className="search-input-wrapper">
                <svg className="search-icon" width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M21 21L16.514 16.506L21 21ZM19 10.5C19 15.194 15.194 19 10.5 19C5.806 19 2 15.194 2 10.5C2 5.806 5.806 2 10.5 2C15.194 2 19 5.806 19 10.5Z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
                <input
                  type="text"
                  placeholder="Buscar artículos..."
                  value={searchTerm}
                  onChange={handleSearchChange}
                  className="search-input"
                />
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Filters Section */}
      <section className="blogs-filters">
        <div className="container">
          <div className="filter-categories">
            {blogCategories.map((category) => (
              <button
                key={category}
                className={`filter-btn ${
                  selectedCategory === category ? 'active' : ''
                }`}
                onClick={() => handleCategoryChange(category)}
              >
                {category}
              </button>
            ))}
          </div>
        </div>
      </section>

      {/* Blog Posts Grid */}
      <section className="blogs-content">
        <div className="container">
          {filteredPosts.length > 0 ? (
            <>
              <div className="blogs-results-info">
                <p>
                  {filteredPosts.length === 1 
                    ? '1 artículo encontrado' 
                    : `${filteredPosts.length} artículos encontrados`
                  }
                  {selectedCategory !== 'Todos' && ` en ${selectedCategory}`}
                  {searchTerm && ` para "${searchTerm}"`}
                </p>
              </div>
              
              <div className="blogs-grid">
                {filteredPosts.map((post) => (
                  <BlogCard key={post.id} post={post} />
                ))}
              </div>
            </>
          ) : (
            <div className="no-results">
              <div className="no-results-icon">
                <svg width="64" height="64" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M21 21L16.514 16.506L21 21ZM19 10.5C19 15.194 15.194 19 10.5 19C5.806 19 2 15.194 2 10.5C2 5.806 5.806 2 10.5 2C15.194 2 19 5.806 19 10.5Z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                </svg>
              </div>
              <h3>No se encontraron artículos</h3>
              <p>
                {searchTerm 
                  ? `No hay artículos que coincidan con "${searchTerm}"` 
                  : `No hay artículos en la categoría "${selectedCategory}"`
                }
              </p>
              <button 
                className="reset-filters-btn"
                onClick={() => {
                  setSearchTerm('');
                  setSelectedCategory('Todos');
                }}
              >
                Ver todos los artículos
              </button>
            </div>
          )}
        </div>
      </section>

      {/* Newsletter Section */}
      <section className="blogs-newsletter">
        <div className="container">
          <div className="newsletter-content">
            <h2>¿Te gustó nuestro contenido?</h2>
            <p>Suscríbete a nuestro newsletter y recibe los mejores artículos sobre salud mental directamente en tu correo.</p>
            <div className="newsletter-form">
              <input 
                type="email" 
                placeholder="Tu correo electrónico"
                className="newsletter-input"
              />
              <button className="newsletter-btn">
                Suscribirse
              </button>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default BlogsPage;