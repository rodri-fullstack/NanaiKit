-- Tabla Usuario
CREATE TABLE IF NOT EXISTS usuario (
    usuario_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    direccion TEXT,
    edad INT,
    genero VARCHAR(20),
    tipo_usuario VARCHAR(20),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla Producto
CREATE TABLE IF NOT EXISTS producto (
    producto_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    descripcion TEXT,
    stock INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Kit
CREATE TABLE IF NOT EXISTS kit (
    kit_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nivel_ansiedad TEXT,
    descripcion TEXT,
    precio DECIMAL(10,2),
    stock INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla Pedido
CREATE TABLE IF NOT EXISTS pedido (
    pedido_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuario(usuario_id),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2)
);

-- Tabla Test_Emocional
CREATE TABLE IF NOT EXISTS test_emocional (
    test_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuario(usuario_id),
    resultado TEXT,
    fecha DATE
);

-- Tabla Kit_Producto (relación N:M entre kit y producto)
CREATE TABLE IF NOT EXISTS kit_producto (
    kit_producto_id SERIAL PRIMARY KEY,
    kit_id INT REFERENCES kit(kit_id),
    producto_id INT REFERENCES producto(producto_id)
);

-- Tabla Contenido_Digital (asociado a un kit)
CREATE TABLE IF NOT EXISTS contenido_digital (
    contenido_id SERIAL PRIMARY KEY,
    kit_id INT REFERENCES kit(kit_id),
    tipo_contenido VARCHAR(50),
    url VARCHAR(255)
);

-- Tabla Pedido_Kit (relación N:M entre pedido y kit)
CREATE TABLE IF NOT EXISTS pedido_kit (
    pedido_kit_id SERIAL PRIMARY KEY,
    pedido_id INT REFERENCES pedido(pedido_id),
    kit_id INT REFERENCES kit(kit_id),
    cantidad INT
);


