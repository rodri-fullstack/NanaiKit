import React from 'react';
import HeroSection from '../components/HeroSection';
import FeaturedProducts from '../components/FeaturedProducts';
import TestimonialSection from '../components/TestimonialSection';
import AboutSection from '../components/AboutSection';

const HomePage = () => {
  return (
    <div className="home-page">
      <HeroSection />
      <FeaturedProducts />
      <TestimonialSection />
      <AboutSection />
    </div>
  );
};

export default HomePage;