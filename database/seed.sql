-- Insertar datos en Usuario (personas y organizaciones)
INSERT INTO Usuario (nombre, email, edad, genero, tipo_usuario) VALUES
('Marcela Ortega', 'marcela@nanai.cl', 27, 'Femenino', 'individual'),
('Diego Soto', 'diego@nanai.cl', 32, 'Masculino', 'individual'),
('Tais Villanueva', 'tais@nanai.cl', 22, 'Femenino', 'individual'),
('Red Apoyo Mental', 'contacto@apoyomental.cl', 0, 'Otro', 'organizacion'),
('MindUp SPA', 'contacto@mindup.cl', 0, 'Otro', 'organizacion'),
('Fundación Emoción', 'hola@fundacionemocion.cl', 0, 'Otro', 'organizacion'),
('Universidad Bienestar', 'bienestar@ubienestar.cl', 0, 'Otro', 'organizacion'),
('Empresa CalmApp', 'contacto@calmapp.com', 0, 'Otro', 'organizacion');
 
-- Insertar datos en Kit
INSERT INTO Kit (nombre, nivel_ansiedad, descripcion, precio) VALUES
('Kit Nanai Básico', 'bajo', 'Incluye cuaderno de emociones, infusión y acceso a ejercicios de respiración', 12990),
('Kit Ansiedad Media', 'medio', 'Cartas terapéuticas, spray aromático y meditación guiada online', 14990),
('Kit Crisis Emocional', 'alto', 'Herramientas para manejar crisis, contenido digital de apoyo urgente', 18990),
('Kit Oficina Consciente', 'medio', 'Diseñado para entornos laborales: bola antiestrés y rutina de pausas mentales', 13990),
('Kit Buen Dormir', 'bajo', 'Incluye guía para conciliar el sueño, infusión noche tranquila y audio relajante', 11990);
 
-- Insertar datos en Producto
INSERT INTO Producto (nombre, tipo, descripcion) VALUES
('Cuaderno de emociones', 'Cuaderno', 'Guía para registrar emociones y pensamientos diarios'),
('Infusión de calma', 'Infusión', 'Mezcla natural de hierbas para relajar cuerpo y mente'),
('Cartas terapéuticas', 'Cartas', 'Cartas con frases, ejercicios y reflexiones de autocuidado'),
('Spray aromático', 'Sensorial', 'Spray con aceites esenciales para generar calma inmediata'),
('Bola antiestrés', 'Sensorial', 'Objeto táctil que ayuda a liberar tensión acumulada');
 
-- Insertar datos en Kit_Producto
INSERT INTO Kit_Producto (id_kit, id_producto) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 3), (3, 4),
(4, 5), (4, 1),
(5, 2), (5, 4);
 
-- Insertar datos en Contenido_Digital
INSERT INTO Contenido_Digital (id_kit, tipo_contenido, url) VALUES
(1, 'Respiración', 'https://nanai.cl/contenido/respiracion-basica'),
(2, 'Meditación Guiada', 'https://nanai.cl/contenido/meditacion-media'),
(3, 'Audio Apoyo', 'https://nanai.cl/contenido/crisis'),
(4, 'Pausa Activa', 'https://nanai.cl/contenido/pausa-laboral'),
(5, 'Audio Relajación', 'https://nanai.cl/contenido/sueno-tranquilo');
 
-- Insertar datos en Test_Emocional
INSERT INTO Test_Emocional (id_usuario, resultado, fecha) VALUES
(1, 'medio', '2025-07-01'),
(2, 'bajo', '2025-07-02'),
(3, 'alto', '2025-07-03'),
(4, 'medio', '2025-07-04'),
(5, 'bajo', '2025-07-05');
 
-- Insertar datos en Pedido
INSERT INTO Pedido (id_usuario, fecha_pedido, total) VALUES
(1, '2025-07-10', 12990),
(2, '2025-07-11', 14990),
(3, '2025-07-12', 18990),
(1, '2025-07-13', 11990),
(5, '2025-07-14', 13990);
 
-- Insertar datos en Pedido_Kit
INSERT INTO Pedido_Kit (id_pedido, id_kit, cantidad) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 5, 1),
(5, 4, 1);