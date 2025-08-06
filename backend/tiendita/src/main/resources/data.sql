-- Usuarios
INSERT INTO usuario (nombre, email, contrasena, telefono, direccion, edad, genero, tipo_usuario, activo)
VALUES
    ('Ana Torres', 'ana@example.com', 'hashed_password1', '987654321', 'Av. Siempre Viva 123', 28, 'Femenino', 'cliente', TRUE),
    ('Luis Pérez', 'luis@example.com', 'hashed_password2', '912345678', 'Calle Falsa 456', 35, 'Masculino', 'cliente', TRUE);

-- Productos
INSERT INTO producto (nombre, tipo, descripcion, stock, activo)
VALUES
    ('Aceite esencial de lavanda', 'Aromaterapia', 'Aceite relajante natural', 50, TRUE),
    ('Velas aromáticas', 'Decoración', 'Velas con fragancia de vainilla', 30, TRUE),
    ('Cuaderno de gratitud', 'Papelería', 'Cuaderno para reflexionar y agradecer', 40, TRUE);

-- Kits
INSERT INTO kit (nombre, nivel_ansiedad, descripcion, precio, stock, activo)
VALUES
    ('Kit Tranquilidad', 'Moderado', 'Incluye velas, aceite esencial y guía de meditación', 19990.00, 20, TRUE),
    ('Kit Energía Positiva', 'Leve', 'Cuaderno, lápices de colores y afirmaciones positivas', 14990.00, 15, TRUE);

-- Kit_Producto
INSERT INTO kit_producto (kit_id, producto_id)
VALUES
    (1, 1), -- Kit Tranquilidad incluye Aceite
    (1, 2), -- Kit Tranquilidad incluye Velas
    (2, 3); -- Kit Energía Positiva incluye Cuaderno

-- Contenido Digital
INSERT INTO contenido_digital (kit_id, tipo_contenido, url)
VALUES
    (1, 'audio', 'https://cdn.example.com/meditacion_5min.mp3'),
    (2, 'pdf', 'https://cdn.example.com/afirmaciones_positivas.pdf');

-- Test Emocional
INSERT INTO test_emocional (usuario_id, resultado, fecha)
VALUES
    (1, 'Ansiedad moderada, se recomienda relajación guiada', CURRENT_DATE - INTERVAL '5 days'),
    (2, 'Ansiedad leve, mantener rutinas positivas', CURRENT_DATE);

-- Pedidos
INSERT INTO pedido (usuario_id, fecha_creacion, total)
VALUES
    (1, CURRENT_TIMESTAMP, 19990.00),
    (2, CURRENT_TIMESTAMP, 14990.00);

-- Pedido_Kit
INSERT INTO pedido_kit (pedido_id, kit_id, cantidad)
VALUES
    (1, 1, 1),  -- Ana pidió 1 Kit Tranquilidad
    (2, 2, 1);  -- Luis pidió 1 Kit Energía Positiva
