import React from 'react';
import Navbar from './Navbar';
import Footer from './Footer';
import AlertBox from './AlertBox';
import { Outlet } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const Layout = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Navbar />
      <main className="flex-grow-1 container py-1">
        <AlertBox />
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Layout;