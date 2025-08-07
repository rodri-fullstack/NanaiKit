DROP DATABASE IF EXISTS nanai_kit;
CREATE DATABASE nanai_kit;
USE nanai_kit;

 
CREATE TABLE Usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100),
  edad INT,
  genero VARCHAR(20),
  tipo_usuario VARCHAR(20)
);
 
CREATE TABLE Organizacion (
  id_organizacion INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  rubro VARCHAR(100),
  correo_contacto VARCHAR(100)
);
 
CREATE TABLE Kit (
  id_kit INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  nivel_ansiedad ENUM('bajo', 'medio', 'alto'),
  descripcion TEXT,
  precio DECIMAL(10,2)
);
 
CREATE TABLE Producto (
  id_producto INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  tipo VARCHAR(50),
  descripcion TEXT
);
 
CREATE TABLE Kit_Producto (
  id_kit_producto INT AUTO_INCREMENT PRIMARY KEY,
  id_kit INT,
  id_producto INT,
  FOREIGN KEY (id_kit) REFERENCES Kit(id_kit),
  FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);
 
CREATE TABLE Pedido (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  fecha_pedido DATE,
  total DECIMAL(10,2),
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
 
CREATE TABLE Pedido_Kit (
  id_pedido_kit INT AUTO_INCREMENT PRIMARY KEY,
  id_pedido INT,
  id_kit INT,
  cantidad INT,
  FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
  FOREIGN KEY (id_kit) REFERENCES Kit(id_kit)
);
 
CREATE TABLE Contenido_Digital (
  id_contenido INT AUTO_INCREMENT PRIMARY KEY,
  id_kit INT,
  tipo_contenido VARCHAR(50),
  url VARCHAR(255),
  FOREIGN KEY (id_kit) REFERENCES Kit(id_kit)
);
 
CREATE TABLE Test_Emocional (
  id_test INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  resultado ENUM('bajo', 'medio', 'alto'),
  fecha DATE,
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

DROP TABLE Organizacion;