# Configuración CORS para NanaiKit

## 🚨 Problema Resuelto
Error de CORS que impedía que el frontend de Vercel se comunicara con el backend de Render.

## ✅ Soluciones Implementadas

### 1. **Filtro CORS Personalizado** (`CorsFilter.java`)
- Maneja peticiones preflight OPTIONS
- Establece headers CORS correctos
- Prioridad más alta para interceptar todas las peticiones

### 2. **Configuración Web** (`WebConfig.java`)
- Configuración CORS a nivel de Spring MVC
- Permite múltiples orígenes (Vercel + localhost)

### 3. **Anotaciones en Controladores**
- `@CrossOrigin` en `AuthController`
- Orígenes específicos permitidos

### 4. **Configuración de Seguridad**
- CORS habilitado en `SecurityConfig`
- Headers de autorización permitidos

## 🌐 Orígenes Permitidos
- `https://nanai-kit.vercel.app` (Producción)
- `https://nanai-kit-git-main-nanai-kit.vercel.app` (Main branch)
- `https://nanai-kit-git-develop-nanai-kit.vercel.app` (Develop branch)
- `http://localhost:3000` (React dev)
- `http://localhost:5173` (Vite dev)

## 🔧 Headers CORS Configurados
```
Access-Control-Allow-Origin: https://nanai-kit.vercel.app
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS, PATCH
Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept, Authorization, Cache-Control
Access-Control-Max-Age: 3600
```

## 🚀 Pasos para Desplegar

### 1. **Compilar el Proyecto**
```bash
cd backend
mvn clean package -DskipTests
```

### 2. **Desplegar en Render**
- Subir el archivo `.jar` generado
- Configurar variables de entorno:
  - `DB_URL`
  - `DB_USERNAME` 
  - `DB_PASSWORD`
  - `JWT_SECRET`

### 3. **Verificar Despliegue**
```bash
curl -X OPTIONS https://nanaikit-j1c8.onrender.com/api/auth/register \
  -H "Origin: https://nanai-kit.vercel.app" \
  -H "Access-Control-Request-Method: POST" \
  -H "Access-Control-Request-Headers: Content-Type"
```

## 🧪 Pruebas de CORS

### **Petición Preflight (OPTIONS)**
```bash
curl -X OPTIONS https://nanaikit-j1c8.onrender.com/api/auth/register \
  -H "Origin: https://nanai-kit.vercel.app" \
  -H "Access-Control-Request-Method: POST" \
  -H "Access-Control-Request-Headers: Content-Type" \
  -v
```

### **Petición Real (POST)**
```bash
curl -X POST https://nanaikit-j1c8.onrender.com/api/auth/register \
  -H "Origin: https://nanai-kit.vercel.app" \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test","email":"test@test.com","contrasena":"123456"}' \
  -v
```

## 🔍 Verificación de Headers
Los headers de respuesta deben incluir:
- `Access-Control-Allow-Origin: https://nanai-kit.vercel.app`
- `Access-Control-Allow-Credentials: true`
- `Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS, PATCH`

## 🐛 Solución de Problemas

### **Si CORS sigue fallando:**
1. Verificar que el filtro esté registrado
2. Revisar logs del servidor
3. Confirmar que el dominio de Vercel esté correcto
4. Verificar que no haya conflictos con otras configuraciones

### **Para desarrollo local:**
- Usar `http://localhost:3000` o `http://localhost:5173`
- Verificar que el backend esté corriendo en el puerto correcto

## 📝 Notas Importantes
- El filtro CORS tiene prioridad más alta (`@Order(Ordered.HIGHEST_PRECEDENCE)`)
- Se maneja específicamente la petición OPTIONS preflight
- Los headers se establecen en cada respuesta HTTP
- La configuración es específica para el dominio de Vercel
