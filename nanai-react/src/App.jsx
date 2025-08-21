import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from './modules/layouts/Layout';
import HomePage from './modules/home/pages/HomePage';
import LoginPage from './modules/auth/pages/LoginPage';
import RegisterPage from './modules/auth/pages/RegisterPage';
import ProfilePage from './modules/auth/pages/ProfilePage';
import CartPage from './modules/cart/pages/CartPage';
import ProductPage from './modules/products/pages/ProductPage';
import KitsPage from './modules/products/pages/KitsPage';
import BlogsPage from './modules/blog/pages/BlogsPage';
import NosotresPage from './modules/about/pages/NosotresPage';
import TestPage from './modules/test/pages/TestPage';
import SearchPage from './modules/search/pages/SearchPage';
import { useAuth } from './modules/auth/hook/useAuth';
import './App.css';

// Componente para rutas protegidas
const ProtectedRoute = ({ children }) => {
  const { isAuthenticated, loading } = useAuth();
  
  // Mostrar indicador de carga mientras se verifica la autenticación
  if (loading) {
    return <div className="d-flex justify-content-center p-5"><div className="spinner-border" role="status"></div></div>;
  }
  
  // Redirigir a login si no está autenticado
  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }
  
  return children;
};

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* Rutas públicas */}
        <Route index element={<HomePage />} />
        <Route path="login" element={<LoginPage />} />
        <Route path="registro" element={<RegisterPage />} />
        <Route path="carrito" element={<CartPage />} />
        <Route path="kits" element={<KitsPage />} />
        <Route path="productos/:productId" element={<ProductPage />} />
        <Route path="blog" element={<BlogsPage />} />
        <Route path="nosotres" element={<NosotresPage />} />
        <Route path="test" element={<TestPage />} />
        <Route path="buscar" element={<SearchPage />} />
        
        {/* Rutas protegidas (requieren autenticación) */}
        <Route path="perfil" element={
          <ProtectedRoute>
            <ProfilePage />
          </ProtectedRoute>
        } />
        
        {/* Rutas de placeholder para desarrollo */}
        <Route path="*" element={
          <div className="p-5 text-center">
            <h2>Página en Construcción</h2>
            <p className="lead">Esta sección estará disponible próximamente</p>
          </div>
        } />
      </Route>
    </Routes>
  )
}

export default App
