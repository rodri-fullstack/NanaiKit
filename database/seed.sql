
/*
╔════════════════════════════════════════════════════════════════════════════════╗
                                NANAI KIT 🪻                                   
                   Creación de Base de Datos en PostgreSQL                     
╠════════════════════════════════════════════════════════════════════════════════╣
  📋 Script para ingreso de datos sugeridos al inicio de la base de datos de 
  Nanai Kit.                 
  📝 Version: 1.0                                                              
╚════════════════════════════════════════════════════════════════════════════════╝
*/

-- Productos
INSERT INTO producto (nombre, tipo, descripcion, stock, activo) 
VALUES
	('Taza', 'Producto físico', 'Taza con diseño motivacional para acompañar momentos de calma y reflexión.', 50, true),
	('Infusión Calmante', 'Producto consumible', 'Mezcla de hierbas naturales para promover la relajación y reducir el estrés.', 100, true),
	('Cuaderno de Gratitud', 'Producto físico', 'Cuaderno diseñado para registrar pensamientos y agradecimientos diarios.', 40, true),
	('Posavasos', 'Producto físico', 'Juego de posavasos con frases inspiradoras para acompañar bebidas calientes.', 60, true),
	('Stickers', 'Producto físico', 'Set de pegatinas con diseños relacionados a bienestar y motivación.', 120, true),
	('Luz de calma', 'Producto físico', 'Lámpara con luz suave para crear ambientes relajantes en cualquier espacio.', 30, true),
	('Planner Semanal', 'Producto físico', 'Agenda semanal para planificar actividades, con espacio para metas y autocuidado.', 50, true),
	('Infusión energizante', 'Producto consumible', 'Té natural que ayuda a activar cuerpo y mente para el día a día.', 80, true),
	('Tarjetas para hábitos', 'Producto físico', 'Cartas diseñadas para fomentar la creación y seguimiento de hábitos saludables.', 70, true),
	('Aromaterapia energizante', 'Producto físico', 'Aceites esenciales para estimular energía y concentración.', 40, true),
	('Liga de ejercicio', 'Producto físico', 'Banda elástica para ejercicios de resistencia y tonificación.', 100, true),
	('Reloj', 'Producto físico', 'Reloj de pulsera con diseño funcional para ayudar a gestionar el tiempo.', 35, true),
	('Libro de respiración guiada', 'Producto físico', 'Guía con técnicas de respiración para reducir ansiedad y mejorar el bienestar.', 25, true),
	('Libro de Mandalas', 'Producto físico', 'Libro para colorear mandalas que ayuda a la relajación y concentración.', 40, true),
	('Lápices', 'Producto físico', 'Set de lápices de colores para actividades creativas y artísticas.', 90, true),
	('Aromaterapia', 'Producto físico', 'Aceites esenciales para promover el bienestar emocional y físico.', 50, true),
	('Fanzine', 'Producto físico', 'Publicación pequeña con contenido sobre autocuidado y bienestar emocional.', 20, true),
	('Cojín de contención emocional', 'Producto físico', 'Cojín diseñado para brindar confort y apoyo emocional en momentos difíciles.', 45, true),
	('Botella con frases', 'Producto físico', 'Botella reutilizable con mensajes motivacionales impresos para inspirar todo el día.', 55, true),
	('Cuaderno de autoexploración emocional', 'Producto físico', 'Cuaderno con ejercicios y preguntas para explorar y entender las emociones.', 30, true),
	('Guía de autocuidado', 'Producto físico/digital', 'Manual con estrategias prácticas para cuidar la salud emocional y física.', 100, true),
	('Semillas para plantar', 'Producto físico', 'Paquete de semillas para cultivo de plantas que fomentan la conexión con la naturaleza.', 70, true),
	('Antifaz para dormir', 'Producto físico', 'Máscara para los ojos que ayuda a bloquear la luz y mejorar el descanso.', 60, true),
	('Frases de contención', 'Producto físico/digital', 'Colección de frases motivacionales y de apoyo para momentos de crisis.', 100, true),
	('Diario Emocional', 'Producto físico', 'Diario para registrar emociones diarias y facilitar la autorreflexión.', 40, true),
	('Peluche de contención emocional', 'Producto físico', 'Peluche suave diseñado para ofrecer confort en situaciones de estrés o ansiedad.', 50, true),
	('Guía para episodios de crisis', 'Producto físico/digital', 'Manual con pasos y recomendaciones para manejar episodios emocionales críticos.', 75, true),
	('Juego de autorregulación', 'Producto físico', 'Juego educativo para aprender técnicas de regulación emocional de forma lúdica.', 25, true),
	('Tarjetas de calma', 'Producto físico', 'Cartas con ejercicios breves para inducir calma y control en momentos de estrés.', 80, true),
	('Libro de autoayuda', 'Producto físico', 'Texto con herramientas y consejos para mejorar la salud mental y emocional.', 40, true),
	('Flores de Bach', 'Producto consumible', 'Preparado natural para situaciones de emergencia emocional, basado en flores de Bach.', 60, true),
	('Velas aromáticas', 'Producto físico', 'Velas aromáticas para crear ambientes relajantes y propiciar el bienestar.', 70, true),
	('Infusión relajante', 'Producto consumible', 'Té de hierbas para promover la relajación y un sueño reparador.', 90, true);

-- Kits
INSERT INTO kit (nombre, nivel_ansiedad, descripcion, precio, stock, activo, tipo_contenido_digital, url)
VALUES
    ('Kit Gratitud', '1- Preventivo', 'Para valorar lo cotidiano y cultivar pensamientos positivos.', 9990.00, 20, TRUE, 'pdf_ejercicios', 'https://nanaikit.com/contenido/gratitud-ejercicios-diarios.pdf'),
    ('Kit Rutina', '1- Preventivo', 'Para recuperar hábitos y estructura diaria con amabilidad.', 12990.00, 20, TRUE, 'planificador_digital', 'https://nanaikit.com/contenido/rutina-planificador-habitos.pdf'),
    ('Kit Calma', '2- Alerta', 'Para bajar la intensidad, respirar y volver al cuerpo.', 15990.00, 20, TRUE, 'audio_meditacion', 'https://nanaikit.com/contenido/calma-meditacion-respiracion.mp3'),
    ('Kit Renace', '2- Alerta', 'Para dar sentido a los cambios, procesar lo vivido y comenzar de nuevo.', 17990.00, 20, TRUE, 'video_reflexion', 'https://nanaikit.com/contenido/renace-reflexiones-cambio.mp4'),
    ('Kit Contención', '3- SOS Urgencia', 'Para brindar compañía simbólica en estados de tristeza profunda, duelo o aislamiento emocional.', 18990.00, 20, TRUE, 'audio_contencion', 'https://nanaikit.com/contenido/contencion-voz-acompanamiento.mp3'),
    ('Kit Ansiedad, Insomio, Pánico', '3- SOS Urgencia', 'Primeros auxilios emocionales ante ataques de ansiedad, insomnio o pánico.', 19990.00, 20, TRUE, 'audio_sos', 'https://nanaikit.com/contenido/ansiedad-ejercicios-emergencia.mp3');


-- Kit_Producto
INSERT INTO kit_producto (kit_id, producto_id)
VALUES
    -- Kit Gratitud (kit_id = 1)
    (1, 1),  -- Taza
    (1, 2),  -- Infusión Calmante
    (1, 3),  -- Cuaderno de Gratitud
    (1, 4),  -- Posavasos
    (1, 5),  -- Stickers
    (1, 6),  -- Luz de calma

    -- Kit Rutina (kit_id = 2)
    (2, 7),  -- Planner Semanal
    (2, 8),  -- Infusión energizante
    (2, 5),  -- Stickers
    (2, 9),  -- Tarjetas para hábitos
    (2, 10), -- Aromaterapia energizante
    (2, 11), -- Liga de ejercicio
    (2, 12), -- Reloj

    -- Kit Calma (kit_id = 3)
    (3, 13), -- Libro de respiración guiada
    (3, 2),  -- Infusión Calmante
    (3, 14), -- Libro de Mandalas
    (3, 15), -- Lápices
    (3, 16), -- Aromaterapia
    (3, 17), -- Fanzine
    (3, 18), -- Cojín de contención emocional

    -- Kit Renace (kit_id = 4)
    (4, 19), -- Botella con frases
    (4, 20), -- Cuaderno de autoexploración emocional
    (4, 21), -- Guía de autocuidado
    (4, 22), -- Semillas para plantar
    (4, 23), -- Antifaz para dormir

    -- Kit Contención (kit_id = 5)
    (5, 24), -- Frases de contención
    (5, 25), -- Diario Emocional
    (5, 26), -- Peluche de contención emocional
    (5, 1),  -- Taza
    (5, 2),  -- Infusión Calmante
    (5, 27), -- Guía para episodios de crisis
    (5, 18), -- Cojín de contención emocional

    -- Kit Ansiedad, Insomnio, Pánico (kit_id = 6)
    (6, 28), -- Juego de autorregulación
    (6, 29), -- Tarjetas de calma
    (6, 30), -- Libro de autoayuda
    (6, 16), -- Aromaterapia (general)
    (6, 31), -- Flores de Bach
    (6, 32), -- Velas aromáticas
    (6, 26), -- Peluche de contención emocional
    (6, 1),  -- Taza
    (6, 33); -- Infusión relajante

