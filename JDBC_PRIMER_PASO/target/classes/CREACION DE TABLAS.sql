CREATE DATABASE vivero;
USE vivero;

DROP TABLE pago;
DROP TABLE oficina;

CREATE TABLE IF NOT EXISTS pago (
    id_pago INT AUTO_INCREMENT,
    id_cliente INT,
    forma_pago VARCHAR(40),
    id_transaccion VARCHAR(50),
    fecha_pago DATE,
    total DECIMAL(15,2),
    PRIMARY KEY (id_pago)
    );

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT AUTO_INCREMENT,
    codigo_cliente INT,
    nombre_cliente VARCHAR(60),
    nombre_contacto VARCHAR(30),
    apellido_contacto VARCHAR(30),
    telefono VARCHAR(15),
    fax VARCHAR(15),
    ciudad VARCHAR(50),
    region VARCHAR(50),
    pais VARCHAR(50),
    codigo_postal VARCHAR(10),
    id_empleado INT,
    limite_credito DECIMAL(15,2),
    PRIMARY KEY (id_cliente)
    );

CREATE TABLE IF NOT EXISTS oficina (
    id_oficina INT AUTO_INCREMENT,
    codigo_oficina VARCHAR(10),
    ciudad VARCHAR(30),
    pais VARCHAR(50),
    region VARCHAR(50),
    codigo_postal VARCHAR(10),
    telefono VARCHAR(20),
    PRIMARY KEY (id_oficina)
    );

CREATE TABLE IF NOT EXISTS empleado (
    id_empleado INT AUTO_INCREMENT,
    codigo_empleado INT,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    extension VARCHAR(10),
    email VARCHAR(100),
    id_oficina INT,
    id_jefe INT,
    puesto VARCHAR(50),
    PRIMARY KEY (id_empleado)
    );

CREATE TABLE IF NOT EXISTS pedido (
    id_pedido INT AUTO_INCREMENT,
    codigo_pedido INT,
    fecha_pedido DATE,
    fecha_esperada DATE,
    fecha_entrega DATE,
    estado VARCHAR(15),
    comentarios TEXT,
    id_cliente INT,
    PRIMARY KEY (id_pedido)
    );

CREATE TABLE IF NOT EXISTS detalle_pedido (
    id_detalle_pedido INT AUTO_INCREMENT,
    id_pedido INT,
    id_producto INT,
    cantidad INT,
    precio_unidad DECIMAL(15,2),
    numero_linea SMALLINT,
    PRIMARY KEY (id_detalle_pedido)
    );

CREATE TABLE IF NOT EXISTS producto (
    id_producto INT AUTO_INCREMENT,
    codigo_producto VARCHAR(15),
    nombre VARCHAR(70),
    id_gama INT,
    dimensiones VARCHAR(25),
    proveedor VARCHAR(50),
    descripcion TEXT,
    cantidad_en_stock SMALLINT,
    precio_venta DECIMAL(15,2),
    precio_proveedor DECIMAL(15,2),
    PRIMARY KEY (id_producto)
    );

CREATE TABLE IF NOT EXISTS gama_producto (
    id_gama INT AUTO_INCREMENT,
    gama VARCHAR(50),
    descripcion_texto TEXT,
    descripcion_html TEXT,
    imagen VARCHAR(256),
    PRIMARY KEY (id_gama)
    );

ALTER TABLE cliente ADD CONSTRAINT fk_cliente_empleado FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado);
ALTER TABLE empleado ADD CONSTRAINT fk_empleado_oficina FOREIGN KEY (id_oficina) REFERENCES oficina (id_oficina);
ALTER TABLE empleado ADD CONSTRAINT fk_empleado_jefe FOREIGN KEY (id_jefe) REFERENCES empleado (id_empleado);
ALTER TABLE pedido ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente);
ALTER TABLE detalle_pedido ADD CONSTRAINT fk_detalle_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido);
ALTER TABLE detalle_pedido ADD CONSTRAINT fk_detalle_pedido_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto);
ALTER TABLE producto ADD CONSTRAINT fk_producto_gama FOREIGN KEY (id_gama) REFERENCES gama_producto (id_gama);

SELECT * FROM cliente;


