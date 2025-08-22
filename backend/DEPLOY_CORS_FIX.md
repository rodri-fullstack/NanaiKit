# 🚀 Despliegue de Solución CORS - NanaiKit

## 🚨 **PROBLEMA ACTUAL:**
El backend en Render NO tiene la configuración CORS actualizada, por eso sigue fallando.

## ✅ **SOLUCIÓN IMPLEMENTADA:**
Hemos creado múltiples capas de configuración CORS para asegurar que funcione.

## 🔧 **ARCHIVOS CREADOS/MODIFICADOS:**

### 1. **CorsConfig.java** - Configuración Spring Security CORS
### 2. **SimpleCorsFilter.java** - Filtro personalizado de alta prioridad
### 3. **SecurityConfig.java** - Actualizado para usar CORS
### 4. **application-cors.yml** - Configuración específica CORS
### 5. **AuthController.java** - Anotaciones @CrossOrigin

## 🚀 **PASOS PARA DESPLEGAR:**

### **Opción 1: Despliegue Manual (Recomendado)**

#### 1. **Compilar el Proyecto**
```bash
# En tu máquina local con Maven instalado
cd backend
mvn clean package -DskipTests
```

#### 2. **Subir a Render**
- Ir a [Render Dashboard](https://dashboard.render.com)
- Seleccionar tu servicio `nanaikit-j1c8`
- En "Build & Deploy" → "Manual Deploy"
- Subir el archivo `.jar` generado en `target/`

#### 3. **Verificar Variables de Entorno**
```
DB_URL=tu_url_de_base_de_datos
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_password
JWT_SECRET=tu_secreto_jwt
```

### **Opción 2: Despliegue desde GitHub (Si tienes Maven en Render)**

#### 1. **Commit y Push de Cambios**
```bash
git add .
git commit -m "Fix CORS configuration for Vercel frontend"
git push origin main
```

#### 2. **Render se Desplegará Automáticamente**

## 🧪 **PRUEBAS DESPUÉS DEL DESPLIEGUE:**

### **1. Probar CORS Preflight (OPTIONS)**
```bash
curl -X OPTIONS https://nanaikit-j1c8.onrender.com/api/auth/register \
  -H "Origin: https://nanai-kit.vercel.app" \
  -H "Access-Control-Request-Method: POST" \
  -H "Access-Control-Request-Headers: Content-Type" \
  -v
```

**Respuesta Esperada:**
```
HTTP/1.1 200 OK
Access-Control-Allow-Origin: https://nanai-kit.vercel.app
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS, PATCH
```

### **2. Probar Registro Real (POST)**
```bash
curl -X POST https://nanaikit-j1c8.onrender.com/api/auth/register \
  -H "Origin: https://nanai-kit.vercel.app" \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test","email":"test@test.com","contrasena":"123456"}' \
  -v
```

## 🔍 **VERIFICACIÓN EN FRONTEND:**

### **1. Abrir DevTools en Vercel**
- Ir a `https://nanai-kit.vercel.app`
- F12 → Console
- Intentar registrar un usuario

### **2. Verificar en Network Tab**
- Debe aparecer petición OPTIONS (preflight)
- Debe aparecer petición POST (registro)
- Ambas deben tener status 200

## 🐛 **SI SIGUE FALLANDO:**

### **1. Verificar Logs del Backend**
- En Render Dashboard → Logs
- Buscar errores relacionados con CORS

### **2. Verificar Headers de Respuesta**
```bash
curl -I https://nanaikit-j1c8.onrender.com/api/auth/register
```

### **3. Probar con Postman/Insomnia**
- Crear petición OPTIONS
- Verificar headers de respuesta

## 📝 **NOTAS IMPORTANTES:**

- **El filtro CORS tiene prioridad más alta** (`@Order(Ordered.HIGHEST_PRECEDENCE)`)
- **Se maneja específicamente OPTIONS** para preflight
- **Los headers se establecen en cada respuesta**
- **La configuración es específica para Vercel**

## 🎯 **RESULTADO ESPERADO:**

Después del despliegue exitoso:
- ✅ Frontend de Vercel puede registrar usuarios
- ✅ Frontend de Vercel puede hacer login
- ✅ No más errores de CORS en la consola
- ✅ Peticiones OPTIONS y POST funcionan correctamente

## 🆘 **CONTACTO:**

Si después del despliegue sigue fallando:
1. Verificar logs del backend en Render
2. Confirmar que el archivo .jar se subió correctamente
3. Verificar que las variables de entorno estén configuradas
4. Probar con las herramientas de curl proporcionadas
