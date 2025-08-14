# 🗒️Base de Datos - Nanai Kit 🪻

## Descripción del Proyecto

Esta documentación describe la estructura y funcionamiento de la base de datos que soporta la plataforma de ecommerce responsiva de Nanai Kit.

## 🗄️ Tecnología

- **Sistema de Gestión de Base de Datos:** PostgreSQL
- **Número de Tablas:** 7 tablas principales
- **Arquitectura:** Base de datos relacional para ecommerce

## 📊 Estructura de la Base de Datos

### Diagrama Entidad-Relación

La base de datos está diseñada siguiendo un modelo relacional que permite gestionar usuarios, productos, kits de bienestar, compras y evaluaciones emocionales de manera eficiente.

### Tablas Principales

#### 👤 `usuario`
Almacena la información personal y de acceso de los usuarios registrados en la plataforma.

**Campos principales:**
- `usuario_id` (PK) - Identificador único del usuario
- `nombre` - Nombre del usuario
- `apellidos` - Apellidos del usuario
- `email` - Correo electrónico (único)
- `password` - Contraseña encriptada
- `telefono` - Número de contacto
- `direccion` - Dirección de entrega
- `edad` - Edad del usuario
- `genero` - Género del usuario
- `tipo_usuario` - Tipo de cuenta (cliente, admin, empresa, etc.)
- `activo` - Estado de la cuenta

#### 🛒 `compra`
Registra las transacciones realizadas por los usuarios.

**Campos principales:**
- `compra_id` (PK) - Identificador único de la compra
- `cliente_id` (FK) - Referencia al usuario que realiza la compra
- `fecha_compra` - Timestamp de la transacción
- `total` - Monto total de la compra
- `forma_pago` - Método de pago utilizado
- `estado_compra` - Estado actual del pedido

#### 📋 `compra_detalle`
Detalla los elementos específicos de cada compra.

**Campos principales:**
- `compra_detalle_id` (PK) - Identificador único del detalle
- `compra_id` (FK) - Referencia a la compra principal
- `kit_id` (FK) - Kit adquirido
- `cantidad` - Cantidad comprada

#### 🎁 `kit`
Catálogo de kits de bienestar emocional disponibles.

**Campos principales:**
- `kit_id` (PK) - Identificador único del kit
- `nombre` - Nombre del kit
- `nivel_ansiedad` - Nivel de ansiedad que aborda
- `descripcion` - Descripción detallada del kit
- `precio` - Precio del kit
- `stock` - Cantidad disponible
- `activo` - Estado de disponibilidad
- `tipo_contenido_digital` - Tipo de contenido digital incluido
- `url_contenido` - Enlace al contenido digital

#### 📦 `producto`
Inventario de productos individuales que componen los kits.

**Campos principales:**
- `producto_id` (PK) - Identificador único del producto
- `nombre` - Nombre del producto
- `tipo` - Categoría del producto
- `descripcion` - Descripción del producto
- `stock` - Inventario disponible
- `activo` - Estado del producto
- `fecha_creacion` - Fecha de registro

#### 🔗 `kit_producto`
Tabla de relación que define qué productos incluye cada kit.

**Campos principales:**
- `kit_producto_id` (PK) - Identificador único de la relación
- `kit_id` (FK) - Referencia al kit
- `producto_id` (FK) - Referencia al producto

#### 🧠 `test_emocional`
Registra las evaluaciones emocionales de los usuarios para personalizar recomendaciones.

**Campos principales:**
- `test_id` (PK) - Identificador único del test
- `usuario_id` (FK) - Usuario que realiza el test
- `resultado` - Resultado de la evaluación
- `fecha` - Fecha de realización

## 🔄 Relaciones Entre Tablas

El diseño de la base de datos implementa las siguientes relaciones:

- **Usuario → Test Emocional**: Un usuario puede realizar múltiples tests (`usuario.usuario_id` → `test_emocional.usuario_id`)
- **Usuario → Compra**: Un usuario puede tener múltiples compras (`usuario.usuario_id` → `compra.cliente_id`)
- **Compra → Compra Detalle**: Una compra puede tener múltiples detalles (`compra.compra_id` → `compra_detalle.compra_id`)
- **Kit → Compra Detalle**: Un kit puede estar en múltiples detalles de compra (`kit.kit_id` → `compra_detalle.kit_id`)
- **Kit → Kit Producto**: Relación muchos a muchos entre kits y productos (`kit.kit_id` → `kit_producto.kit_id`)
- **Producto → Kit Producto**: Un producto puede estar en múltiples kits (`producto.producto_id` → `kit_producto.producto_id`)

## 🚀 Configuración e Instalación

### Requisitos Previos

- Acceso de administrador para crear la base de datos
- Herramienta de administración de bases de datos como por ejemplo: DBeaver.

### Pasos de Instalación

1. **Crear la base de datos:**
   ```sql
   CREATE DATABASE nanai_kit;
   ```

2. **Ejecutar creación de tablas**
   El script de creación de tablas está disponible en el archivo `/database/seed.sql`

3. **Cargar datos iniciales:**
   El script de datos se encuentra disponible en el archivo `/database/schema.sql`

### Variables de Entorno

Para unir con el backend, no olvidar configurar las siguientes variables en tu archivo `.env`:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=nanai_kit
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```

## 📝 Consideraciones de Desarrollo

### Buenas Prácticas Implementadas

- **Integridad Referencial**: Todas las relaciones están definidas con claves foráneas
- **Auditoria**: Campos de fecha para rastrear creación y modificación

### Recomendaciones

- Realizar respaldos regulares de la base de datos.
- Monitorear el rendimiento de las consultas más frecuentes.

---

 Creado con 💖 para proyecto **Nanai Kit 🪻** 