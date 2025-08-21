import axios from 'axios';
import { API_URL } from '../config/apiConfig';
import { users } from '../../auth/utils/dummydata';

const authService = {
  /**
   * Iniciar sesión de usuario
   * @param {Object} credentials - Credenciales del usuario
   * @param {string} credentials.email - Correo electrónico
   * @param {string} credentials.password - Contraseña
   * @returns {Promise<Object>} - Datos del usuario autenticado
   */
  login: async (credentials) => {
    try {
      // En un entorno real, esto sería una llamada a la API
      // const response = await axios.post(`${API_URL}/auth/login`, credentials);
      // return response.data;
      
      // Simulación de respuesta para desarrollo usando dummydata
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          // Buscar usuario en los datos de prueba
          const user = users.find(u => 
            (u.username === credentials.username || u.username === credentials.email) && 
            u.password === credentials.password
          );
          
          // También mantener compatibilidad con el usuario de ejemplo anterior
          if (credentials.email === 'usuario@ejemplo.com' && credentials.password === '123456') {
            resolve({
              id: 1,
              nombre: 'Usuario',
              apellido: 'Ejemplo',
              email: credentials.email,
              username: 'usuario@ejemplo.com',
              token: 'token-simulado-123456'
            });
          } else if (user) {
            resolve({
              id: user.id,
              nombre: user.username === 'demo' ? 'Demo' : 'Test',
              apellido: user.username === 'demo' ? 'User' : 'User',
              email: `${user.username}@nanaikit.com`,
              username: user.username,
              token: `token-${user.username}-${Date.now()}`
            });
          } else {
            reject(new Error('Credenciales incorrectas'));
          }
        }, 1000); // Simular delay de red
      });
    } catch (error) {
      console.error('Error en login:', error);
      throw new Error(error.response?.data?.message || 'Error al iniciar sesión');
    }
  },

  /**
   * Registrar nuevo usuario
   * @param {Object} userData - Datos del nuevo usuario
   * @returns {Promise<Object>} - Datos del usuario creado
   */
  register: async (userData) => {
    try {
      // En un entorno real, esto sería una llamada a la API
      // const response = await axios.post(`${API_URL}/auth/register`, userData);
      // return response.data;
      
      // Simulación de respuesta para desarrollo
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          // Simular validación de email único
          if (userData.email === 'usuario@ejemplo.com') {
            reject(new Error('El correo electrónico ya está registrado'));
          } else {
            resolve({
              id: Math.floor(Math.random() * 1000),
              ...userData,
              token: 'token-registro-' + Date.now()
            });
          }
        }, 1000); // Simular delay de red
      });
    } catch (error) {
      console.error('Error en registro:', error);
      throw new Error(error.response?.data?.message || 'Error al registrar usuario');
    }
  },

  /**
   * Verificar token de autenticación
   * @returns {Promise<Object>} - Datos del usuario autenticado
   */
  verifyToken: async () => {
    try {
      // En un entorno real, esto sería una llamada a la API con el token
      // const token = localStorage.getItem('token');
      // const response = await axios.get(`${API_URL}/auth/verify`, {
      //   headers: { Authorization: `Bearer ${token}` }
      // });
      // return response.data;
      
      // Simulación para desarrollo
      return new Promise((resolve) => {
        setTimeout(() => {
          const storedUser = localStorage.getItem('usuario');
          if (storedUser) {
            resolve(JSON.parse(storedUser));
          } else {
            resolve(null);
          }
        }, 500);
      });
    } catch (error) {
      console.error('Error al verificar token:', error);
      throw new Error('Sesión inválida');
    }
  }
};

export default authService;