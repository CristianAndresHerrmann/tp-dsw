-- Insertar datos en la tabla 'coordenadas'
INSERT INTO coordenada (latitud, longitud) VALUES (40.7128, -74.0060);
INSERT INTO coordenada (latitud, longitud) VALUES (34.0522, -118.2437);
INSERT INTO coordenada (latitud, longitud) VALUES (41.8781, -87.6298);
INSERT INTO coordenada (latitud, longitud) VALUES (29.7604, -95.3698);
INSERT INTO coordenada (latitud, longitud) VALUES (39.7392, -104.9903);
INSERT INTO coordenada (latitud, longitud) VALUES (33.4484, -112.0740);
INSERT INTO coordenada (latitud, longitud) VALUES (47.6062, -122.3321);
INSERT INTO coordenada (latitud, longitud) VALUES (32.7157, -117.1611);
INSERT INTO coordenada (latitud, longitud) VALUES (25.7617, -80.1918);
INSERT INTO coordenada (latitud, longitud) VALUES (37.7749, -122.4194);
INSERT INTO coordenada (latitud, longitud) VALUES (36.1627, -86.7816);

-- Insertar datos en la tabla 'categorias'
INSERT INTO categoria (nombre, descripcion, tipo_categoria) VALUES ('Bebida Con Alcohol', 'Esta bebida es con alcohol, prohibido su consumo en menores', 'BEBIDA');
INSERT INTO categoria (nombre, descripcion, tipo_categoria) VALUES ('Bebida Sin Alcohol', 'Esta bebida es sin alcohol', 'BEBIDA');
INSERT INTO categoria (nombre, descripcion, tipo_categoria) VALUES ('Comida Vegana', 'Esta comida es vegana', 'COMIDA');
INSERT INTO categoria (nombre, descripcion, tipo_categoria) VALUES ('Comida Vegetariana', 'Esta comida es vegetariana, no apta para veganos', 'COMIDA');
INSERT INTO categoria (nombre, descripcion, tipo_categoria) VALUES ('Comida Clasica', 'Esta comida no es apta para vegetarianos, ni apta para veganos', 'COMIDA');

-- Insertar datos en la tabla 'vendedores'
INSERT INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 1, NULL, CURDATE(), 'Avenida Siempre Viva 742', 'Gaia');
INSERT INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 2, NULL, CURDATE(), 'Calle Principal 123', 'La Buena Pizza');
INSERT INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 3, NULL, CURDATE(), 'Boulevard Central 456', 'Kiosko Pancho');
INSERT INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 4, NULL, CURDATE(), 'Avenida del Sol 789', 'Cafe Amanecer');
INSERT INTO vendedor (activo, coordenada_Id, fecha_eliminacion, fecha_registro, direccion, nombre) VALUES (1, 5, NULL, CURDATE(), 'Plaza Mayor 101', 'Sabor y Tradicion');

-- Insertar datos en la tabla 'clientes'
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 6, NULL, CURDATE(), '20-12345678-9', 'Calle Ficticia 123', 'cliente@example.com', 'Juan Perez');
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 7, NULL, CURDATE(), '20-23456789-0', 'Calle Imaginaria 456', 'cliente2@example.com', 'Maria Lopez');
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 8, NULL, CURDATE(), '20-34567890-1', 'Calle Ejemplo 789', 'cliente3@example.com', 'Carlos Martinez');
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 9, NULL, CURDATE(), '20-45678901-2', 'Avenida Falsa 321', 'cliente4@example.com', 'Ana Gomez');
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 10, NULL, CURDATE(), '20-56789012-3', 'Boulevard Real 654', 'cliente5@example.com', 'Jose Fernandez');
INSERT INTO cliente (activo, coordenada_Id, fecha_eliminacion, fecha_registro, cuit, direccion, email, nombre) VALUES (1, 11, NULL, CURDATE(), '20-67890123-4', 'Calle Nueva 789', 'cliente6@example.com', 'Sofia Ramirez');

-- Insertar datos en la tabla 'item_menu'
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco de cola', 'Coca Cola', 1);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco sabor lima', 'Sprite', 1);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco sabor naranja', 'Fanta', 1);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 25.00, 'Refresco sabor uva', 'Manaos de Uva', 1);
--5
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 1, NULL, 300.00, 'Fernet con Coca-Cola', 'Fernet con Coca', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 1, NULL, 210.00, 'Campari', 'Camari', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 1, NULL, 220.00, 'VodKa con bebida energizante', 'Vodka con Speed', 2);
--9
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Bebida energizante', 'Speed', 3);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 180.00, 'Bebida energizante Monster sabor comun', 'Monster', 3);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 180.00, 'Bebida energizante Monster sin azucar', 'Monster Blanca', 3);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 200.00, 'Bebida energizante Monster sabor mango', 'Monster Mango', 3);
--13
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 140.00, 'Cafe negro', 'Cafe Cortado', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 160.00, 'Cafe Latte', 'Latte', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 200.00, 'Cafe de especialidad', 'Cafe Especial', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 110.00, 'Jugo de naranja exprimido a mano', 'Jugo de naranja', 4);
--17
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco de cola', 'Pepsi', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco sabor lima', 'Seven-Up', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 2, NULL, 150.00, 'Refresco sabor naranja', 'Mirinda', 5);
--20//platos
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 300.00, 'Empanadas de carne de res', 'Empanadas de carne', 1);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 300.00, 'Empanadas de jamon y queso', 'Empanadas de jamon y queso', 1);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 300.00, 'Sanguche de milanesa con tomate, lechuga y huevo', 'Sanguche de milanesa', 1);
--23
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 400.00, 'Pizza Muzzarella', 'Pizza Muzzarella', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 450.00, 'Pizza con tomate y albahaca', 'Pizza Napolitana', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 500.00, 'Pizza jamon, queso y morron', 'Pizza Especial', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 400.00, 'Hamburguesa simple', 'Hamburguesa Simple', 2);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 600.00, 'Hamburguesa con tomate, lechuga, cebolla crispy, huevo y panceta', 'Hamburguesa Completa', 2);
--28
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 80.00, 'Alfajor triple de la marca oreo', 'Alfajor Oreo triple', 3);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 80.00, 'Alfajor triple de la marca terrabusi', 'Alfajor Terrabusi triple', 3);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 70.00, 'Alfajor simple de la marca Rasta', 'Alfajor Rasta', 3);
--31
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 110.00, 'Tostado de jamon y queso', 'Tostado Simple', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 100.00, 'Alfajor triple de la marca Havana', 'Alfajor Havana', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 50.00, 'Medialuna', 'Medialuna Salada', 4);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 3, NULL, 300.00, 'Cookies sin ningun alimento origen animal', 'Cookies Veganas', 4);
--35
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 300.00, 'Empanadas de queso y choclo', 'Empanada de humita', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 400.00, 'Sanguche de lomo', 'Sanguche de lomo', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 3, NULL, 600.00, 'Hamburguesa de lenteja', 'Hamburguesa Vegana', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 5, NULL, 350.00, 'Papas fritas con cheddar y panceta', 'Papas con cheddar y panceta', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 3, NULL, 200.00, 'Ensalada de quinoa, lentejas, tomate, pepino, pimiento, apio, cebolla morada y vinagreta', 'Ensalada Especial', 5);
INSERT INTO item_menu (activo, categoria_id, fecha_eliminacion, precio, descripcion, nombre, vendedor_id) VALUES (1, 4, NULL, 300.00, 'Papas fritas con cheddar', 'Papas con cheddar', 5);

-- Insertar datos en la tabla 'Bebidas'
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 1, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 2, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 3, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 4, 1500, 1500);

INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (30.0, 5, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (15.0, 6, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (20.0, 7, 500, 500);

INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 8, 500, 500);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 9, 473, 473);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 10, 473, 473);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 11, 473, 473);

INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 12, 300, 300);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 13, 300, 300);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 14, 300, 300);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 15, 500, 500);

INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 16, 1000, 1000);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 17, 1000, 1000);
INSERT INTO Bebida (graduacion_alcoholica, id, tamano, volumen) VALUES (0.0, 18, 1000, 1000);

-- Insertar datos en la tabla 'platos'
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 250.0, 19, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 200.0, 20, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 300.0, 21, 500);

INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 300.0, 22, 300);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 300.0, 23, 300);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 370.0, 24, 350);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 350.0, 25, 250);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 360.0, 26, 260);

INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 100.0, 27, 100);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 100.0, 28, 100);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 80.0, 29, 80);

INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 0, 100.0, 30, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 100.0, 31, 80);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 1, 0, 80.0, 32, 50);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 1, 0, 350.0, 33, 250);

INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 200.0, 34, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 0, 1, 300.0, 35, 290);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (0, 1, 0, 120.0, 36, 250);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (1, 0, 0, 250.0, 37, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (1, 1, 0, 80.0, 38, 200);
INSERT INTO plato (aptoCeliaco, aptoVegano, aptoVegetariano, calorias, id, peso) VALUES (1, 0, 1, 230.0, 39, 200);

-- Insertar datos en la tabla 'Pago'
INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 300.00, 'Transferencia', 'Alias1', '1234567890', '20-12345678-9');

INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 700.00, 'MercadoPago', 'Alias2', NULL, NULL);

INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 480.00, 'Transferencia', 'Alias3', '1234567789', '20-34567890-1');

INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 840.00, 'MercadoPago', 'Alias4', NULL, NULL);

INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 200.00, 'MercadoPago', 'Alias5', NULL, NULL);

INSERT INTO Pago (fecha, monto, tipo_pago, alias, cbu, cuit) VALUES (CURDATE(), 700.00, 'Transferencia', 'Alias6', '1234567789', '20-67890123-4');

-- Insertar datos en la tabla 'pedidos'
INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (1, 1, 'PAGADO');

INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (2, 2, 'PAGADO');

INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (3, 3, 'PAGADO');

INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (4, 4, 'PAGADO');

INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (5, 5, 'PAGADO');

INSERT INTO pedido (cliente_id, formaPago_id, estado) VALUES (6, 6, 'PAGADO');

-- Insertar datos en la tabla 'item_pedidos'
INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (2, 1, NULL);

INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (1, 5, NULL);
INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (2, 23, NULL);

INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (6, 28, NULL);

INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (2, 14, NULL);
INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (2, 31, NULL);
INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (1, 34, NULL);

INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (1, 39, NULL);

INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (1, 36, NULL);
INSERT INTO item_pedido (cantidad, itemMenu_id, pedido_item_pedido_id) VALUES (2, 5, NULL);

-- Insertar datos en la tabla 'pedido_item_pedido'
INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (1, 1);

INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (2, 2);
INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (3, 2);

INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (4, 3);

INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (5, 4);
INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (6, 4);
INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (7, 4);

INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (8, 5);

INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (9, 6);
INSERT INTO pedido_item_pedido (item_pedido_id, pedido_id) VALUES (10, 6);

-- Updates
UPDATE item_pedido SET pedido_item_pedido_id = 1 WHERE id = 1;

UPDATE item_pedido SET pedido_item_pedido_id = 2 WHERE id = 2;
UPDATE item_pedido SET pedido_item_pedido_id = 3 WHERE id = 3;

UPDATE item_pedido SET pedido_item_pedido_id = 4 WHERE id = 4;

UPDATE item_pedido SET pedido_item_pedido_id = 5 WHERE id = 5;
UPDATE item_pedido SET pedido_item_pedido_id = 6 WHERE id = 6;
UPDATE item_pedido SET pedido_item_pedido_id = 7 WHERE id = 7;

UPDATE item_pedido SET pedido_item_pedido_id = 8 WHERE id = 8;

UPDATE item_pedido SET pedido_item_pedido_id = 9 WHERE id = 9;
UPDATE item_pedido SET pedido_item_pedido_id = 10 WHERE id = 10;