
/*
╔════════════════════════════════════════════════════════════════════════════════╗
                                NANAI KIT 🪻                                   
                   Creación de Base de Datos en PostgreSQL                     
╠════════════════════════════════════════════════════════════════════════════════╣
  📋 Script para ingreso de datos sugeridos al inicio de la base de datos de 
  Nanai Kit.                 
  📝 Version: 1.1.1                                                              
╚════════════════════════════════════════════════════════════════════════════════╝
*/

-- ===========
-- Productos (inventario base)
-- ===========
INSERT INTO producto (sku, nombre, costo, stock) VALUES
-- Kit Gratitud
('TAZA', 'Taza', 1500, 100),
('INF_CALM', 'Infusión Calmante', 500, 200),
('CUAD_GRAT', 'Cuaderno de Gratitud', 1200, 80),
('POSA', 'Posavasos', 200, 150),
('STICKERS', 'Stickers', 100, 300),
('LUZ_CALMA', 'Luz de calma', 1800, 60),

-- Kit Rutina
('PLAN_SEM', 'Planner Semanal', 900, 90),
('INF_ENER', 'Infusión Energizante', 500, 120),
('HAB_TAR', 'Tarjetas para hábitos', 600, 110),
('AROMA_EN', 'Aromaterapia Energizante', 900, 70),
('LIGA_EJ', 'Liga de ejercicio', 700, 75),
('RELOJ', 'Reloj', 2500, 40),

-- Kit Calma
('LIB_RESP', 'Libro de respiración guiada', 2500, 50),
('LIB_MAND', 'Libro de Mandalas', 2000, 60),
('LAPICES', 'Lápices', 800, 100),
('AROMA_STD', 'Aromaterapia', 900, 70),
('FANZINE', 'Fanzine', 500, 80),
('COJIN', 'Cojín de contención emocional', 2500, 40),

-- Kit Renace
('BOTELLA', 'Botella con frases', 1500, 60),
('CUAD_AUTO', 'Cuaderno de autoexploración emocional', 1200, 70),
('GUIA_AUTO', 'Guía de autocuidado', 1000, 90),
('SEMILLAS', 'Semillas para plantar', 700, 100),
('ANTIFAZ', 'Antifaz para dormir', 800, 80),

-- Kit Contención
('FRASES', 'Frases de contención', 500, 200),
('DIARIO_EMO', 'Diario emocional', 1200, 70),
('PELUCHE', 'Peluche de contención emocional', 3000, 40),
('GUIA_CRISIS', 'Guía para episodios de crisis', 1000, 60),

-- Kit Ansiedad / Insomnio / Pánico
('JUEGO_AUTO', 'Juego de autorregulación', 1500, 60),
('TAR_CALMA', 'Tarjetas de calma', 600, 100),
('LIB_AUTO', 'Libro de autoayuda', 2000, 50),
('FLORES_BACH', 'Flores de Bach SOS', 2500, 30),
('VELAS', 'Velas', 700, 100),
('INF_RELAX', 'Infusión relajante', 600, 120);

-- ===========
-- Kits
-- ===========
INSERT INTO kit (codigo, nombre, nivel, precio, descripcion_breve) VALUES
('KIT_GRATITUD', 'Kit Gratitud', 'N1_PREVENTIVO', 9990, 'Para valorar lo cotidiano y cultivar pensamientos positivos.'),
('KIT_RUTINA', 'Kit Rutina', 'N1_PREVENTIVO', 12990, 'Para recuperar hábitos y estructura diaria con amabilidad.'),
('KIT_CALMA', 'Kit Calma', 'N2_ALERTA', 15990, 'Para bajar la intensidad, respirar y volver al cuerpo.'),
('KIT_RENACE', 'Kit Renace', 'N2_ALERTA', 17990, 'Para dar sentido a los cambios y comenzar de nuevo.'),
('KIT_CONTENCION', 'Kit Contención', 'N3_SOS', 18990, 'Para brindar compañía simbólica en estados de tristeza profunda.'),
('KIT_ANSIEDAD', 'Kit Ansiedad - Insomnio - Pánico', 'N3_SOS', 19990, 'Primeros auxilios emocionales ante ataques de ansiedad, insomnio o pánico.');

-- ===========
-- Recetas (Kit → Productos)
-- ===========
-- Gratitud
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='TAZA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='INF_CALM'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='CUAD_GRAT'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='POSA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='STICKERS'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_GRATITUD'), (SELECT id_producto FROM producto WHERE sku='LUZ_CALMA'), 1);

-- Rutina
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='PLAN_SEM'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='INF_ENER'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='STICKERS'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='HAB_TAR'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='AROMA_EN'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='LIGA_EJ'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RUTINA'), (SELECT id_producto FROM producto WHERE sku='RELOJ'), 1);

-- Calma
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='LIB_RESP'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='INF_CALM'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='LIB_MAND'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='LAPICES'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='AROMA_STD'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='FANZINE'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CALMA'), (SELECT id_producto FROM producto WHERE sku='COJIN'), 1);

-- Renace
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_RENACE'), (SELECT id_producto FROM producto WHERE sku='BOTELLA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RENACE'), (SELECT id_producto FROM producto WHERE sku='CUAD_AUTO'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RENACE'), (SELECT id_producto FROM producto WHERE sku='GUIA_AUTO'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RENACE'), (SELECT id_producto FROM producto WHERE sku='SEMILLAS'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_RENACE'), (SELECT id_producto FROM producto WHERE sku='ANTIFAZ'), 1);

-- Contención
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='FRASES'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='DIARIO_EMO'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='PELUCHE'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='TAZA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='INF_CALM'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='GUIA_CRISIS'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_CONTENCION'), (SELECT id_producto FROM producto WHERE sku='COJIN'), 1);

-- Ansiedad / Insomnio / Pánico
INSERT INTO kit_producto VALUES
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='JUEGO_AUTO'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='TAR_CALMA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='LIB_AUTO'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='AROMA_STD'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='FLORES_BACH'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='VELAS'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='PELUCHE'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='TAZA'), 1),
((SELECT id_kit FROM kit WHERE codigo='KIT_ANSIEDAD'), (SELECT id_producto FROM producto WHERE sku='INF_RELAX'), 1);

