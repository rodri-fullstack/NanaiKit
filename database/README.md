# 🗒️Base de Datos - Nanai Kit 🪻

## Descripción del Proyecto

Esta documentación describe la estructura y funcionamiento de la base de datos que soporta la plataforma de ecommerce responsiva de Nanai Kit.

## 🗄️ Tecnología

- **Sistema de Gestión de Base de Datos:** PostgreSQL
- **Esquema:** nanai
- **Número de Tablas:** 5 tablas principales + 1 tabla de relación
- **Arquitectura:** Base de datos relacional optimizada para ecommerce de bienestar emocional


## 📊 Estructura de la Base de Datos

### Diagrama Entidad-Relación

La base de datos está diseñada siguiendo un modelo relacional que permite gestionar usuarios, productos, kits de bienestar, compras y evaluaciones emocionales de manera eficiente.

### Tablas Principales

#### 👤 `usuario`
Almacena la información personal y de acceso de los usuarios registrados en la plataforma.

**Campos principales:**
- `id_usuario` (BIGSERIAL, PK) - Identificador único del usuario
- `nombre` (VARCHAR(100)) - Nombre completo del usuario
- `email` (VARCHAR(255), UNIQUE) - Correo electrónico único
- `contrasena_hash` (TEXT) - Contraseña encriptada (bcrypt/argon2)
- `direccion` (VARCHAR(100)) - Dirección de entrega
- `comuna` (VARCHAR(100)) - Comuna de residencia
- `telefono` (VARCHAR(50)) - Número de contacto
- `rol` (rol_usuario_enum) - Rol del usuario (ADMIN, USUARIO)
- `activo` (BOOLEAN) - Estado de la cuenta (por defecto TRUE)

#### 📦 `producto`
Inventario de productos individuales que se utilizan para armar los kits. Los productos no se venden por separado.

**Campos principales:**
- `id_producto` (BIGSERIAL, PK) - Identificador único del producto
- `sku` (VARCHAR(60), UNIQUE) - Código único del producto
- `nombre` (VARCHAR(120)) - Nombre descriptivo del producto
- `costo` (NUMERIC(10,2)) - Costo unitario del producto
- `stock` (INTEGER) - Cantidad disponible en inventario
- `activo` (BOOLEAN) - Estado de disponibilidad del producto

#### 🎁 `kit`
Catálogo de kits de bienestar emocional disponibles para la venta. Es el único producto que los clientes pueden comprar.

**Campos principales:**
- `id_kit` (BIGSERIAL, PK) - Identificador único del kit
- `codigo` (VARCHAR(60), UNIQUE) - Código único del kit (ej: KIT_GRATITUD)
- `nombre` (VARCHAR(120)) - Nombre comercial del kit
- `nivel` (nivel_kit_enum) - Nivel del kit según intensidad emocional:
  - `N1_PREVENTIVO` - Para prevención y bienestar general
  - `N2_ALERTA` - Para situaciones de alerta emocional
  - `N3_SOS` - Para crisis y primeros auxilios emocionales
- `precio` (NUMERIC(10,2)) - Precio de venta del kit
- `descripcion_breve` (TEXT) - Descripción corta para marketing
- `descripcion` (TEXT) - Descripción detallada del kit
- `activo` (BOOLEAN) - Estado de disponibilidad para la venta

#### 🔗 `kit_producto`
Tabla de relación que define la "receta" o composición de cada kit (Bill of Materials - BOM).

**Campos principales:**
- `id_kit` (BIGINT, FK) - Referencia al kit
- `id_producto` (BIGINT, FK) - Referencia al producto incluido
- `cantidad` (INTEGER) - Cantidad del producto en el kit
- **Clave Primaria Compuesta:** (id_kit, id_producto)

#### 🛒 `pedido`
Registra los pedidos realizados por los usuarios (cabecera de la transacción).

**Campos principales:**
- `id_pedido` (BIGSERIAL, PK) - Identificador único del pedido
- `id_usuario` (BIGINT, FK) - Usuario que realiza el pedido
- `estado` (estado_pedido_enum) - Estado del pedido:
  - `pendiente` - Pedido creado, pendiente de pago
  - `pagado` - Pedido pagado y confirmado
  - `cancelado` - Pedido cancelado
- `total` (NUMERIC(10,2)) - Monto total del pedido
- `fecha_creacion` (TIMESTAMPTZ) - Fecha y hora de creación del pedido

#### 📋 `pedido_detalle`
Detalla los kits específicos incluidos en cada pedido.

**Campos principales:**
- `id_pedido_detalle` (BIGSERIAL, PK) - Identificador único del detalle
- `id_pedido` (BIGINT, FK) - Referencia al pedido principal
- `id_kit` (BIGINT, FK) - Kit incluido en el pedido
- `cantidad` (INTEGER) - Cantidad de kits solicitados
- `precio_unitario` (NUMERIC(10,2)) - Precio del kit al momento de la compra
- `nombre_kit` (VARCHAR(120)) - Snapshot del nombre del kit (opcional)


## 🔄 Relaciones Entre Tablas

El diseño de la base de datos implementa las siguientes relaciones:

- **Usuario → Pedido**: Un usuario puede tener múltiples pedidos (`usuario.id_usuario` → `pedido.id_usuario`)
- **Pedido → Pedido Detalle**: Un pedido puede tener múltiples detalles (`pedido.id_pedido` → `pedido_detalle.id_pedido`)
- **Kit → Pedido Detalle**: Un kit puede estar en múltiples detalles de pedido (`kit.id_kit` → `pedido_detalle.id_kit`)
- **Kit ↔ Producto**: Relación muchos a muchos a través de `kit_producto`:
  - Un kit puede contener múltiples productos (`kit.id_kit` → `kit_producto.id_kit`)
  - Un producto puede estar en múltiples kits (`producto.id_producto` → `kit_producto.id_producto`)


## 🚀 Configuración e Instalación

### Requisitos Previos
- PostgreSQL instalado y en funcionamiento
- Acceso de administrador para crear la base de datos
- Herramienta de administración de bases de datos (DBeaver, pgAdmin, etc.)

### Pasos de Instalación

1. **Crear la base de datos:**
   ```sql
   CREATE DATABASE nanai_kit;
   ```
2. **Ejecutar creación de estructura:**
   Ejecutar el script de creación de tablas disponible en `/database/schema.sql`

3. **Cargar datos iniciales:**
   Ejecutar el script de datos disponible en `/database/seed.sql`

### Variables de Entorno

Para conectar con el backend, configurar las siguientes variables en tu archivo `.env`:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=nanai_kit
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
DB_SCHEMA=nanai
```

## 🔍 Consideraciones de Desarrollo
### Buenas Prácticas Implementadas

- **Integridad Referencial**: Todas las relaciones están definidas con claves foráneas
- **Tipos Enumerados**: Uso de ENUMs para campos con valores predefinidos
- **Índices Optimizados**: Índices estratégicos en campos de consulta frecuente
- **Constraints**: Validaciones de datos a nivel de base de datos
- **Esquema Separado**: Uso del esquema `nanai` para organización
- **Auditoria**: Campo de timestamp para rastrear creación de pedidos

### VERSION ACTUAL: 1.1.1
Modificaciones realizadas:
- Reestructuración completa de la base de datos
- Eliminación de tablas innecesarias (test_emocional, compra/compra_detalle)
- Optimización para modelo de negocio basado en kits
- Implementación de ENUMs para mejor control de datos
- Mejora en la estructura de pedidos y detalles
- Adición de productos y kits de ejemplo
- Implementación de esquema `nanai` para mejor organización

---

 Creado con 💖 para proyecto **Nanai Kit 🪻**
