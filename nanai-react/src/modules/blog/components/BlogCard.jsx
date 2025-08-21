import React from 'react';
import { Link } from 'react-router-dom';
import './BlogCard.css';

const BlogCard = ({ post }) => {
  const formatDate = (dateString) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('es-ES', options);
  };

  return (
    <div className="blog-card">
      <div className="blog-card-image">
        <img 
          src={post.image} 
          alt={post.title}
          onError={(e) => {
            e.target.src = '/assets/images/nanaiKitHome.png'; // Imagen por defecto
          }}
        />
        <div className="blog-card-category">
          {post.category}
        </div>
      </div>
      
      <div className="blog-card-content">
        <div className="blog-card-meta">
          <span className="blog-card-author">{post.author}</span>
          <span className="blog-card-date">{formatDate(post.date)}</span>
          <span className="blog-card-read-time">{post.readTime}</span>
        </div>
        
        <h3 className="blog-card-title">
          <Link to={`/blog/${post.id}`}>
            {post.title}
          </Link>
        </h3>
        
        <p className="blog-card-excerpt">
          {post.excerpt}
        </p>
        
        <div className="blog-card-tags">
          {post.tags.map((tag, index) => (
            <span key={index} className="blog-card-tag">
              #{tag}
            </span>
          ))}
        </div>
        
        <div className="blog-card-footer">
          <Link to={`/blog/${post.id}`} className="blog-card-read-more">
            Leer más
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M5 12H19M19 12L12 5M19 12L12 19" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
            </svg>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default BlogCard;