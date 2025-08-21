import axios from 'axios';
import { API_URL, API_CONFIG } from './apiConfig';

// Crear instancia de axios con configuración base
const axiosInstance = axios.create({
  baseURL: API_URL,
  ...API_CONFIG
});

// Interceptor de solicitud
axiosInstance.interceptors.request.use(
  (config) => {
    // Obtener token del almacenamiento local
    const storedUser = localStorage.getItem('usuario');
    if (storedUser) {
      try {
        const user = JSON.parse(storedUser);
        if (user && user.token) {
          // Agregar token a los headers
          config.headers.Authorization = `Bearer ${user.token}`;
        }
      } catch (error) {
        console.error('Error al parsear usuario almacenado:', error);
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor de respuesta
axiosInstance.interceptors.response.use(
  (response) => {
    // Procesar respuesta exitosa
    return response;
  },
  (error) => {
    // Manejar errores de respuesta
    if (error.response) {
      // El servidor respondió con un código de estado fuera del rango 2xx
      const { status } = error.response;
      
      // Manejar error de autenticación
      if (status === 401) {
        // Token expirado o inválido
        localStorage.removeItem('usuario');
        // Redirigir a la página de login (esto se maneja mejor con un contexto global)
        window.location.href = '/login';
      }
      
      // Personalizar mensaje de error según el código de estado
      switch (status) {
        case 400:
          error.message = 'Solicitud incorrecta';
          break;
        case 401:
          error.message = 'No autorizado';
          break;
        case 403:
          error.message = 'Acceso prohibido';
          break;
        case 404:
          error.message = 'Recurso no encontrado';
          break;
        case 500:
          error.message = 'Error interno del servidor';
          break;
        default:
          error.message = `Error en la solicitud (${status})`;
      }
    } else if (error.request) {
      // La solicitud fue hecha pero no se recibió respuesta
      error.message = 'No se recibió respuesta del servidor';
    } else {
      // Error al configurar la solicitud
      error.message = 'Error al realizar la solicitud';
    }
    
    return Promise.reject(error);
  }
);

export default axiosInstance;