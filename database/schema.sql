
/*
╔════════════════════════════════════════════════════════════════════════════════╗
                                NANAI KIT 🪻                                   
                   Creación de Base de Datos en PostgreSQL                     
╠════════════════════════════════════════════════════════════════════════════════╣
  📋 Script para implementación de base de datos de Nanai Kit.                 
  📝 Version: 1.1.1                                                              
╚════════════════════════════════════════════════════════════════════════════════╝
*/

CREATE SCHEMA IF NOT EXISTS nanai;
SET search_path TO nanai, public;

-- =================
-- Tipos (ENUM)
-- =================
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'rol_usuario_enum') THEN
    CREATE TYPE rol_usuario_enum AS ENUM ('ADMIN','USUARIO');
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'nivel_kit_enum') THEN
    CREATE TYPE nivel_kit_enum AS ENUM ('N1_PREVENTIVO','N2_ALERTA','N3_SOS');
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_pedido_enum') THEN
    CREATE TYPE estado_pedido_enum AS ENUM ('pendiente','pagado','cancelado');
  END IF;
END$$;

-- =================
-- 1) Usuario
-- =================
CREATE TABLE IF NOT EXISTS usuario (
  id_usuario       BIGSERIAL PRIMARY KEY,
  nombre           VARCHAR(100)   NOT NULL,
  email            VARCHAR(255)   NOT NULL UNIQUE,
  contrasena_hash  TEXT           NOT NULL,              -- hash (bcrypt/argon2), nunca texto plano
  direccion	   VARCHAR(100)   NOT NULL,
  comuna 	   VARCHAR(100)   NOT NULL,
  telefono 	   VARCHAR(50)    NOT NULL,
  rol              rol_usuario_enum NOT NULL DEFAULT 'USUARIO',
  activo           BOOLEAN        NOT NULL DEFAULT TRUE
);

-- =================
-- 2) Producto (inventario para armar kits; no se vende suelto)
-- =================
CREATE TABLE IF NOT EXISTS producto (
  id_producto  BIGSERIAL PRIMARY KEY,
  sku          VARCHAR(60)    NOT NULL UNIQUE,
  nombre       VARCHAR(120)   NOT NULL,
  costo        NUMERIC(10,2)  NOT NULL DEFAULT 0 CHECK (costo >= 0),
  stock        INTEGER        NOT NULL DEFAULT 0 CHECK (stock >= 0),
  activo       BOOLEAN        NOT NULL DEFAULT TRUE
);

-- =================
-- 3) Kit (lo único que se vende)
-- =================
CREATE TABLE IF NOT EXISTS kit (
  id_kit            BIGSERIAL PRIMARY KEY,
  codigo            VARCHAR(60)     NOT NULL UNIQUE,     -- ej: KIT_GRATITUD
  nombre            VARCHAR(120)    NOT NULL,
  nivel             nivel_kit_enum  NOT NULL,            -- N1_PREVENTIVO | N2_ALERTA | N3_SOS
  precio            NUMERIC(10,2)   NOT NULL CHECK (precio >= 0),
  descripcion_breve TEXT,
  descripcion       TEXT,
  activo            BOOLEAN         NOT NULL DEFAULT TRUE
);

-- =================
-- 4) Receta del kit (BOM): qué productos y cuántas unidades lleva
-- =================
CREATE TABLE IF NOT EXISTS kit_producto (
  id_kit       BIGINT  NOT NULL REFERENCES kit(id_kit) ON DELETE CASCADE,
  id_producto  BIGINT  NOT NULL REFERENCES producto(id_producto) ON DELETE RESTRICT,
  cantidad     INTEGER NOT NULL CHECK (cantidad > 0),
  PRIMARY KEY (id_kit, id_producto)
);

-- Índices útiles mínimos
CREATE INDEX IF NOT EXISTS idx_kit_producto_producto ON kit_producto(id_producto);

-- =================
-- 5) Pedido (cabecera)
-- =================
CREATE TABLE IF NOT EXISTS pedido (
  id_pedido      BIGSERIAL PRIMARY KEY,
  id_usuario     BIGINT REFERENCES usuario(id_usuario) ON DELETE SET NULL,
  estado         estado_pedido_enum NOT NULL DEFAULT 'pendiente',
  total          NUMERIC(10,2)      NOT NULL DEFAULT 0 CHECK (total >= 0),
  fecha_creacion TIMESTAMPTZ        NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_pedido_usuario ON pedido(id_usuario);
CREATE INDEX IF NOT EXISTS idx_pedido_estado  ON pedido(estado);

-- =================
-- 6) Pedido detalle (solo kits)
-- =================
CREATE TABLE IF NOT EXISTS pedido_detalle (
  id_pedido_detalle BIGSERIAL PRIMARY KEY,
  id_pedido         BIGINT  NOT NULL REFERENCES pedido(id_pedido) ON DELETE CASCADE,
  id_kit            BIGINT  NOT NULL REFERENCES kit(id_kit) ON DELETE RESTRICT,
  cantidad          INTEGER NOT NULL CHECK (cantidad > 0),
  precio_unitario   NUMERIC(10,2) NOT NULL CHECK (precio_unitario >= 0),
  nombre_kit        VARCHAR(120)  -- snapshot opcional
);

CREATE INDEX IF NOT EXISTS idx_detalle_pedido ON pedido_detalle(id_pedido);
CREATE INDEX IF NOT EXISTS idx_detalle_kit    ON pedido_detalle(id_kit);
