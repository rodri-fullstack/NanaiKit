# 🚀 Backend API - Nanai Kit 🪻

## Descripción del Proyecto

API REST desarrollada en Java con Spring Boot para la plataforma de ecommerce de kits de bienestar emocional Nanai Kit. La API gestiona usuarios, autenticación, catálogo de kits, pedidos y administración del inventario siguiendo una arquitectura limpia en capas.

## 🛠️ Stack Tecnológico

- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.5.4
- **Base de Datos:** PostgreSQL con Spring Data JPA + Hibernate
- **Autenticación:** Spring Security + JWT
- **Gestor de Dependencias:** Maven
- **Arquitectura:** Patrón MVC con capas (Controller → Service → Repository → Model)

## 📁 Estructura del Proyecto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/nanai_kit/
│   │   │   ├── exception/                    # Manejo de excepciones
│   │   │   ├── features/
│   │   │   │   ├── kit/
│   │   │   │   │   ├── controller/           # KitController
│   │   │   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   │   │   ├── mapper/               # Mapeo entre DTOs y entidades
│   │   │   │   │   ├── model/                # Entidad Kit
│   │   │   │   │   ├── repository/           # Repositorio JPA
│   │   │   │   │   └── service/              # Lógica de negocio
│   │   │   │   ├── pedido/                   # Gestión de pedidos
│   │   │   │   ├── pedidoDetalle/            # Detalles de pedidos
│   │   │   │   ├── producto/                 # Gestión de productos
│   │   │   │   └── usuario/                  # Gestión de usuarios
│   │   │   ├── security/                     # Configuración de seguridad
│   │   │   │   ├── jwt/                      # Configuración JWT
│   │   │   │   ├── config/                   # Security Config
│   │   │   │   └── service/                  # UserDetailsService
│   │   │   └── NanaiKitApplication.java      # Clase principal
│   │   └── resources/
│   │       ├── application.yml               # Configuración principal
│   │       └── application-dev.yml           # Configuración desarrollo
│   └── test/                                 # Tests unitarios e integración
├── target/                                   # Archivos compilados
├── .env                                      # Variables de entorno
├── .gitignore
├── .gitattributes
├── pom.xml                                   # Configuración Maven
├── HELP.md
└── README.md
```

## 🏗️ Arquitectura en Capas

### Controller (Controladores)
- **Responsabilidad:** Manejo de peticiones HTTP y respuestas
- **Ubicación:** `features/{modulo}/controller/`
- **Funciones:** Validación de entrada, mapeo de DTOs, manejo de códigos de estado

### Service (Servicios)
- **Responsabilidad:** Lógica de negocio y reglas de la aplicación
- **Ubicación:** `features/{modulo}/service/`
- **Funciones:** Procesamiento de datos, validaciones complejas, coordinación entre repositorios

### Repository (Repositorios)
- **Responsabilidad:** Acceso a datos y operaciones de persistencia
- **Ubicación:** `features/{modulo}/repository/`
- **Funciones:** Consultas JPA, operaciones CRUD, queries personalizadas

### Model (Modelos)
- **Responsabilidad:** Definición de entidades y estructura de datos
- **Ubicación:** `features/{modulo}/model/`
- **Funciones:** Mapeo de tablas, relaciones JPA, validaciones de campo

## ⚙️ Configuración e Instalación

### Requisitos Previos

- Java 21 JDK instalado
- Maven 3.8+ 
- PostgreSQL 12+ ejecutándose
- IDE (IntelliJ IDEA, Eclipse, VS Code)
- Base de datos Nanai Kit configurada

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone <repository-url>
   cd nanai-kit-backend
   ```

2. **Configurar variables de entorno:**
   Crear archivo `.env` en la raíz:
   ```env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=nanai_kit
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_contraseña
   JWT_SECRET=tu_clave_secreta_muy_segura_aqui
   JWT_EXPIRATION=86400000
   ```

3. **Configurar `application.yml`:**
   ```yaml
   server:
     port: 8080
   
   spring:
     datasource:
       url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:nanai_kit}
       username: ${DB_USERNAME}
       password: ${DB_PASSWORD}
       driver-class-name: org.postgresql.Driver
     
     jpa:
       hibernate:
         ddl-auto: validate
       show-sql: false
       database-platform: org.hibernate.dialect.PostgreSQLDialect
       properties:
         hibernate:
           format_sql: true
   
   jwt:
     secret: ${JWT_SECRET}
     expiration: ${JWT_EXPIRATION:86400000}
   
   ```

4. **Instalar dependencias y compilar:**
   ```bash
   mvn clean install
   ```

5. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

   O desde tu IDE ejecutando `NanaiKitApplication.java`

## 📦 Dependencias Principales (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Base de Datos -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Seguridad Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- JWT (jjwt) -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🔐 Seguridad y Autenticación

### Spring Security + JWT

La aplicación implementa autenticación JWT con Spring Security:

- **Registro e Inicio de Sesión:** Endpoints públicos
- **Token JWT:** Generado al login exitoso
- **Autorización:** Se que validan tokens en endpoints protegidos
- **Roles:** `USER` y `ADMIN` con diferentes permisos


## 🛣️ API Endpoints

### Autenticación (`/api/auth`)
```
POST   /api/auth/register     # Registro de usuario
POST   /api/auth/login        # Inicio de sesión
```

### Usuarios (`/api/usuarios`)
```
GET    /api/usuarios/profile          # Perfil del usuario (AUTH)
PUT    /api/usuarios/profile          # Actualizar perfil (AUTH)
DELETE /api/usuarios/profile          # Eliminar cuenta (AUTH)
```

### Kits (`/api/kits`)
```
GET    /api/kits                      # Listar todos los kits disponibles
GET    /api/kits/{id}                 # Detalle de kit específico
GET    /api/kits/nivel/{nivel}        # Kits por nivel (N1_PREVENTIVO, N2_ALERTA, N3_SOS)
```

### Pedidos (`/api/pedidos`)
```
GET    /api/pedidos                   # Historial de pedidos del usuario (AUTH)
POST   /api/pedidos                   # Crear nuevo pedido (AUTH)
GET    /api/pedidos/{id}              # Detalle de pedido específico (AUTH)
```

### Administración (`/api/admin`) - Solo ADMIN
```
GET    /api/admin/usuarios            # Gestionar usuarios
GET    /api/admin/productos           # Gestionar inventario de productos
POST   /api/admin/productos           # Crear nuevo producto
PUT    /api/admin/productos/{id}      # Actualizar producto
GET    /api/admin/kits               # Gestionar kits
POST   /api/admin/kits               # Crear nuevo kit
PUT    /api/admin/kits/{id}          # Actualizar kit
GET    /api/admin/pedidos            # Ver todos los pedidos
PUT    /api/admin/pedidos/{id}/estado # Actualizar estado de pedido
```

## 📊 DTOs y Mapeo

### Patrón DTO (Data Transfer Object)

La aplicación utiliza DTOs para separar la representación interna (entidades JPA) de la API externa:

```java
// DTO para respuesta de Kit
public class KitResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String nivel;
    private BigDecimal precio;
    private String descripcionBreve;
    private List<ProductoDTO> productos;
    // getters y setters
}

// DTO para crear pedido
public class CrearPedidoDTO {
    @NotEmpty
    private List<PedidoDetalleDTO> detalles;
    // getters y setters
}
```

### Mappers

Los mappers convierten entre entidades y DTOs:

```java
public class KitMapper {
    
    public KitResponseDTO toResponseDTO(Kit kit) {
        // Lógica de mapeo
    }
    
    public Kit toEntity(CrearKitDTO dto) {
        // Lógica de mapeo
    }
}
```

## 🛡️ Validación y Manejo de Errores

### Validaciones - ejemplo

```java
    
    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

```

### Estructura de Respuestas

```java
// Respuesta exitosa
{
  "success": true,
  "data": { ... },
  "message": "Operación completada exitosamente"
}

// Respuesta con error
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Los datos proporcionados no son válidos",
    "details": ["El email es requerido", "La contraseña debe tener al menos 8 caracteres"]
  },
  "timestamp": "2025-01-01T12:00:00Z"
}
```

## 🧪 Testing

### Estructura de Tests

```
src/test/java/com/nanai_kit/
├── features/
│   ├── kit/
│   │   ├── controller/KitControllerTest.java
│   │   ├── service/KitServiceTest.java
│   │   └── repository/KitRepositoryTest.java
│   └── usuario/
│       ├── controller/UsuarioControllerTest.java
│       └── service/UsuarioServiceTest.java
└── integration/
    ├── AuthIntegrationTest.java
    └── KitIntegrationTest.java
```

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Tests específicos
mvn test -Dtest=KitControllerTest

# Tests de integración
mvn test -Dtest=*IntegrationTest

# Con coverage
mvn test jacoco:report
```

## 🚀 Despliegue

### Perfiles de Spring

```yaml
# application-dev.yml (Desarrollo)
spring:
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop

logging:
  level:
    com.nanai_kit: DEBUG

# application-prod.yml (Producción)
spring:
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: validate

logging:
  level:
    com.nanai_kit: INFO
    root: WARN
```

### Build para Producción

```bash
# Generar JAR ejecutable
mvn clean package -Pprod

# Ejecutar en producción
java -jar -Dspring.profiles.active=prod target/nanai-kit-0.0.1-SNAPSHOT.jar
```

### Variables de Entorno para Producción

```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_HOST=tu-servidor-bd
export DB_NAME=nanai_kit_prod
export DB_USERNAME=usuario_prod
export DB_PASSWORD=password_seguro
export JWT_SECRET=clave_jwt_muy_segura_produccion
export JWT_EXPIRATION=86400000
```

## 📚 Recursos y Documentación

### Spring Boot
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)

### Herramientas de Desarrollo
- [Maven Documentation](https://maven.apache.org/guides/)
- [Hibernate ORM](https://hibernate.org/orm/documentation/)
- [JWT.io](https://jwt.io/)



## 🤝 Convenciones de Código

### Estilo de Código
- **Naming:** CamelCase para clases, camelCase para métodos y variables
- **Packages:** Lowercase con estructura por features
- **DTOs:** Terminan en `DTO` (ej: `KitResponseDTO`)
- **Servicios:** Terminan en `Service` (ej: `KitService`)
- **Repositorios:** Terminan en `Repository` (ej: `KitRepository`)

### Estructura de Commits
```bash
feat: agregar endpoint para crear pedidos
fix: corregir validación de email en registro
docs: actualizar README con ejemplos de API
refactor: reorganizar estructura de DTOs
```

## 🔄 Acciones a Futuro

### Funcionalidades Planificadas
- [ ] Sistema de pagos integrado
- [ ] Notificaciones por email
- [ ] Contenido digital descargable
- [ ] Sistema de reviews y calificaciones
- [ ] Dashboard administrativo avanzado

### VERSION ACTUAL: 1.0.0
Características implementadas:
- ✅ API REST completa con Spring Boot 3.5.4
- ✅ Autenticación JWT con Spring Security
- ✅ Sistema de roles (USER/ADMIN)
- ✅ CRUD completo para usuarios, kits y pedidos
- ✅ Validación robusta con Bean Validation
- ✅ Manejo global de excepciones
- ✅ Arquitectura en capas (Controller → Service → Repository → Model)
- ✅ Mapeo DTO/Entity pattern
- ✅ Base de datos PostgreSQL con JPA/Hibernate

---

Desarrollado con 💖 para el proyecto **Nanai Kit 🪻**