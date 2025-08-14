
/*
╔════════════════════════════════════════════════════════════════════════════════╗
                                NANAI KIT 🪻                                   
                   Creación de Base de Datos en PostgreSQL                     
╠════════════════════════════════════════════════════════════════════════════════╣
  📋 Script para implementación de base de datos de Nanai Kit.                 
  📝 Version: 1.0                                                              
╚════════════════════════════════════════════════════════════════════════════════╝
*/

-- Tabla Usuario
CREATE TABLE IF NOT EXISTS usuario (
    usuario_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    direccion TEXT NOT NULL,
    edad INTEGER CHECK (edad > 0 AND edad < 150),
    genero VARCHAR(30) CHECK (genero IN ('Femenino', 'Masculino', 'No binario', 'Otro', 'Prefiero no decirlo')),
    tipo_usuario VARCHAR(20) DEFAULT 'cliente' CHECK (tipo_usuario IN ('cliente', 'empresa')),
    activo BOOLEAN DEFAULT true,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla test_emocional
CREATE TABLE IF NOT EXISTS test_emocional (
    test_id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuario(usuario_id),
    resultado TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla compra
CREATE TABLE compra (
    compra_id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12,2) NOT NULL CHECK (total >= 0),
    forma_pago VARCHAR(50) DEFAULT 'efectivo',
    estado_compra VARCHAR(30) DEFAULT 'pendiente' CHECK (estado_compra IN ('pendiente', 'procesando', 'enviado', 'entregado', 'cancelado')),
    
    -- Clave foránea
    CONSTRAINT fk_compra_cliente 
        FOREIGN KEY (cliente_id) 
        REFERENCES usuario(usuario_id) 
        ON DELETE CASCADE
);

-- Tabla compra_detalle (tabla intermedia entre compra y kit)
CREATE TABLE compra_detalle (
    compra_detalle_id SERIAL PRIMARY KEY,
    compra_id INTEGER NOT NULL,
    kit_id INTEGER NOT NULL,
    cantidad INTEGER NOT NULL DEFAULT 1 CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario >= 0),
    
    -- Claves foráneas
    CONSTRAINT fk_detalle_compra 
        FOREIGN KEY (compra_id) 
        REFERENCES compra(compra_id) 
        ON DELETE CASCADE,
    
    CONSTRAINT fk_detalle_kit 
        FOREIGN KEY (kit_id) 
        REFERENCES kit(kit_id) 
        ON DELETE RESTRICT
);

-- Tabla Kit
CREATE TABLE IF NOT EXISTS kit (
    kit_id SERIAL PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    nivel_ansiedad VARCHAR(50) CHECK (nivel_ansiedad IN ('1- Preventivo', '2- Alerta', '3- SOS Urgencia')),
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 9990),
    stock INTEGER DEFAULT 0 CHECK (stock >= 0),
    activo BOOLEAN DEFAULT true,
    tipo_contenido_digital VARCHAR(100),
    url_contenido VARCHAR(500),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Producto
CREATE TABLE IF NOT EXISTS producto (
    producto_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    descripcion TEXT,
    stock INTEGER DEFAULT 0 CHECK (stock >= 0),
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Kit_Producto (relación N:M entre kit y producto)
CREATE TABLE IF NOT EXISTS kit_producto (
    kit_producto_id SERIAL PRIMARY KEY,
    kit_id INT REFERENCES kit(kit_id),
    producto_id INT REFERENCES producto(producto_id)
);










-- Índices para mejorar rendimiento
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_usuario_activo ON usuario(activo);
CREATE INDEX idx_producto_activo ON producto(activo);
CREATE INDEX idx_compra_cliente ON compra(cliente_id);
CREATE INDEX idx_compra_fecha ON compra(fecha_compra);
CREATE INDEX idx_compra_estado ON compra(estado_compra);
CREATE INDEX idx_kit_activo ON kit(activo);
CREATE INDEX idx_kit_nivel_ansiedad ON kit(nivel_ansiedad);
CREATE INDEX idx_test_usuario ON test_emocional(usuario_id);
CREATE INDEX idx_test_fecha ON test_emocional(fecha);

-- Función para actualizar automáticamente el total de la compra
CREATE OR REPLACE FUNCTION actualizar_total_compra()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE compra 
    SET total = (
        SELECT COALESCE(SUM(subtotal), 0)
        FROM compra_detalle 
        WHERE compra_id = COALESCE(NEW.compra_id, OLD.compra_id)
    )
    WHERE compra_id = COALESCE(NEW.compra_id, OLD.compra_id);
    
    RETURN COALESCE(NEW, OLD);
END;
$$ LANGUAGE plpgsql;

-- Trigger para actualizar el total automáticamente
CREATE TRIGGER trigger_actualizar_total_compra
    AFTER INSERT OR UPDATE OR DELETE ON compra_detalle
    FOR EACH ROW
    EXECUTE FUNCTION actualizar_total_compra();

-- Comentarios en las tablas
COMMENT ON TABLE usuario IS 'Tabla de usuarios del sistema';
COMMENT ON TABLE producto IS 'Catálogo de productos disponibles';
COMMENT ON TABLE compra IS 'Registro de compras realizadas';
COMMENT ON TABLE kit IS 'Kits de productos para diferentes niveles de ansiedad';
COMMENT ON TABLE compra_detalle IS 'Detalle de productos en cada compra';
COMMENT ON TABLE kit_producto IS 'Relación entre kits y productos';
COMMENT ON TABLE test_emocional IS 'Resultados de tests emocionales de usuarios';