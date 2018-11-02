/*
Created: 04/12/2017
Modified: 28/03/2018
Project: bik_qubez
Model: model_bik_qubez
Database: MySQL 5.7
*/


-- Create tables section -------------------------------------------------

-- Table Producto

CREATE TABLE `Producto`
(
  `id_producto` Int NOT NULL AUTO_INCREMENT,
  `barcode` Varchar(20),
  `marca` Varchar(20),
  `modelo` Varchar(20),
  `color` Varchar(20),
  `variante` Varchar(20),
  `corrida` Varchar(20),
  `descripcion` Varchar(100),
  `precio_uno` Decimal(10,2),
  `precio_dos` Decimal(10,2),
  `precio_tres` Decimal(10,2),
  `iva` Int NOT NULL,
  `categoria` Varchar(20),
  `departamento` Varchar(30),
  `unidad_medida` Varchar(10),
  `corte` Varchar(50),
  `forro` Varchar(50),
  `suela` Varchar(50),
  `dim_alto` Varchar(100),
  `dim_ancho` Varchar(100),
  `dim_largo` Varchar(20),
  `stock_minimo` Int,
  `stock_maximo` Int,
  `costo` Decimal(10,2),
  `codigo_proveedor` Varchar(20),
  PRIMARY KEY (`id_producto`),
 UNIQUE `id_producto` (`id_producto`)
)
;

-- Table Cliente

CREATE TABLE `Cliente`
(
  `id_cliente` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(30),
  `a_paterno` Varchar(25),
  `a_materno` Varchar(25),
  `fecha_nacimiento` Date,
  `telefono` Varchar(20),
  `email` Varchar(100),
  `direccion` Varchar(100),
  `predeterminado` Bit(1),
  PRIMARY KEY (`id_cliente`),
 UNIQUE `id_cliente` (`id_cliente`)
)
;

-- Table almacen

CREATE TABLE `almacen`
(
  `id_almacen` Int NOT NULL AUTO_INCREMENT,
  `almacen` Varchar(20),
  `descripcion` Varchar(100),
  `id_tienda` Int NOT NULL,
  PRIMARY KEY (`id_almacen`,`id_tienda`),
 UNIQUE `id_almacen` (`id_almacen`)
)
;

-- Table tienda

CREATE TABLE `tienda`
(
  `id_tienda` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(50),
  `direccion` Varchar(250),
  `estado` Varchar(20),
  `codigo_postal` Int,
  `telefono` Varchar(20),
  PRIMARY KEY (`id_tienda`),
 UNIQUE `id_tienda` (`id_tienda`)
)
;

-- Table terminal

CREATE TABLE `terminal`
(
  `id_terminal` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(30),
  `descripcion` Varchar(100),
  `activo` Bit(1),
  `predeterminada` Bit(1),
  `id_almacen` Int NOT NULL,
  `id_tienda` Int NOT NULL,
  PRIMARY KEY (`id_terminal`,`id_almacen`,`id_tienda`),
 UNIQUE `id_terminal` (`id_terminal`)
)
;

-- Table ss_perfil_modulo

CREATE TABLE `ss_perfil_modulo`
(
  `id_perfil` Int NOT NULL,
  `id_modulo` Int NOT NULL
)
;

ALTER TABLE `ss_perfil_modulo` ADD  PRIMARY KEY (`id_perfil`,`id_modulo`)
;

-- Table ss_modulo

CREATE TABLE `ss_modulo`
(
  `id_modulo` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(30),
  `descripcion` Varchar(100),
  `url` Varchar(100),
  PRIMARY KEY (`id_modulo`),
 UNIQUE `id_modulo` (`id_modulo`)
)
;

-- Table ss_perfil

CREATE TABLE `ss_perfil`
(
  `id_perfil` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(20),
  `descripcion` Varchar(100),
  `modulo_inicio` Varchar(50),
  PRIMARY KEY (`id_perfil`),
 UNIQUE `id_perfil` (`id_perfil`)
)
;

-- Table ss_usuario

CREATE TABLE `ss_usuario`
(
  `id_usuario` Int NOT NULL AUTO_INCREMENT,
  `usuario` Varchar(50),
  `password` Varchar(50),
  `nombre` Varchar(30),
  `a_paterno` Varchar(20),
  `a_materno` Varchar(30),
  `e_mail` Varchar(100),
  `direccion` Varchar(250),
  `telefono_1` Varchar(50),
  `telefono_2` Varchar(50),
  `fecha_nacimiento` Date,
  `estatus` Smallint,
  `id_perfil` Int,
  PRIMARY KEY (`id_usuario`),
 UNIQUE `id_usuario` (`id_usuario`)
)
;

CREATE INDEX `IX_Relationship13` ON `ss_usuario` (`id_perfil`)
;

-- Table venta

CREATE TABLE `venta`
(
  `id_venta` Int NOT NULL AUTO_INCREMENT,
  `fecha_venta` Datetime,
  `id_cliente` Int,
  `id_usuario` Int NOT NULL,
  `id_terminal` Int,
  `id_almacen` Int,
  `id_tienda` Int,
  PRIMARY KEY (`id_venta`),
 UNIQUE `id_venta` (`id_venta`)
)
;

CREATE INDEX `IX_Relationship6` ON `venta` (`id_cliente`)
;

CREATE INDEX `IX_Relationship11` ON `venta` (`id_terminal`,`id_almacen`,`id_tienda`)
;

CREATE INDEX `IX_Relationship8` ON `venta` (`id_usuario`)
;

-- Table movimiento_producto

CREATE TABLE `movimiento_producto`
(
  `id_movimiento_producto` Int NOT NULL AUTO_INCREMENT,
  `id_tipo_transaccion` Int NOT NULL,
  `id_tipo_documento` Int NOT NULL,
  `ref_no_documento` Int,
  `id_producto` Int,
  `barcode` Varchar(20),
  `variante` Varchar(20),
  `cantidad` Decimal(10,2),
  `fecha_registro` Datetime,
  `numero_documento_externo` Varchar(20),
  `id_almacen` Int,
  `id_tienda` Int,
  `id_usuario_registro` Int,
  `observaciones` Varchar(200),
  PRIMARY KEY (`id_movimiento_producto`,`id_tipo_transaccion`,`id_tipo_documento`),
 UNIQUE `id_movimiento_producto` (`id_movimiento_producto`)
)
;

-- Table catalogo_documento

CREATE TABLE `catalogo_documento`
(
  `id_tipo_documento` Int NOT NULL AUTO_INCREMENT,
  `tipo_elemento` Varchar(50),
  `valor_elemento` Varchar(50),
  `orden_elemento` Int,
  `activo` Bit(1),
  `predeterminado` Bit(1),
  PRIMARY KEY (`id_tipo_documento`),
 UNIQUE `id_documento` (`id_tipo_documento`)
)
;

-- Table catalogo_transaccion

CREATE TABLE `catalogo_transaccion`
(
  `id_tipo_transaccion` Int NOT NULL AUTO_INCREMENT,
  `codigo_transaccion` Varchar(20),
  `descripcion` Varchar(50),
  `estatus` Int,
  PRIMARY KEY (`id_tipo_transaccion`),
 UNIQUE `id_transaccion` (`id_tipo_transaccion`)
)
;

-- Table venta_producto

CREATE TABLE `venta_producto`
(
  `id_venta` Int NOT NULL AUTO_INCREMENT,
  `id_producto` Int NOT NULL,
  `barcode` Varchar(20),
  `cantidad` Decimal(10,2),
  `costo` Decimal(10,2),
  `precio_unitario` Decimal(10,2),
  `precio_venta` Decimal(10,2),
  `precio_venta_sin_iva` Decimal(10,2),
  `iva` Decimal(10,2),
  `descuento` Decimal(10,2),
  `estatus` Int,
  `devolucion` Bit(1),
  `motivo_devolucion` Varchar(100),
  `id_promocion` Int,
  PRIMARY KEY (`id_venta`,`id_producto`,`id_promocion`)
)
;

CREATE INDEX `IX_Relationship26` ON `venta_producto` (`id_promocion`)
;

-- Table promocion

CREATE TABLE `promocion`
(
  `id_promocion` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(20),
  `descripcion` Varchar(100),
  `fecha_vigencia_inicio` Datetime,
  `fecha_vigencia_fin` Datetime,
  PRIMARY KEY (`id_promocion`),
 UNIQUE `id_promocion` (`id_promocion`)
)
;

-- Table ss_campo

CREATE TABLE `ss_campo`
(
  `id_campo` Int NOT NULL AUTO_INCREMENT,
  `nombre` Varchar(20),
  `descripcion` Varchar(50),
  PRIMARY KEY (`id_campo`),
 UNIQUE `id_campo` (`id_campo`)
)
;

-- Table ss_perfil_modulo_campo

CREATE TABLE `ss_perfil_modulo_campo`
(
  `id_perfil` Int NOT NULL,
  `id_modulo` Int NOT NULL,
  `id_campo` Int NOT NULL
)
;

ALTER TABLE `ss_perfil_modulo_campo` ADD  PRIMARY KEY (`id_campo`,`id_perfil`,`id_modulo`)
;

-- Table producto_promocion

CREATE TABLE `producto_promocion`
(
  `id_producto_promocion` Int NOT NULL,
  `id_promocion` Int,
  `id_producto` Int,
  `id_producto_complemento` Varchar(100),
  `aplica_promocion_cantidad` Int,
  `descuento_promocion` Decimal(10,2)
)
;

CREATE INDEX `IX_Relationship35` ON `producto_promocion` (`id_promocion`)
;

CREATE INDEX `IX_Relationship36` ON `producto_promocion` (`id_producto`)
;

ALTER TABLE `producto_promocion` ADD  PRIMARY KEY (`id_producto_promocion`)
;


-- Create relationships section ------------------------------------------------- 

ALTER TABLE `ss_perfil_modulo` ADD CONSTRAINT `Relationship2` FOREIGN KEY (`id_perfil`) REFERENCES `ss_perfil` (`id_perfil`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta` ADD CONSTRAINT `Relationship6` FOREIGN KEY (`id_cliente`) REFERENCES `Cliente` (`id_cliente`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta` ADD CONSTRAINT `Relationship8` FOREIGN KEY (`id_usuario`) REFERENCES `ss_usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta` ADD CONSTRAINT `Relationship11` FOREIGN KEY (`id_terminal`, `id_almacen`, `id_tienda`) REFERENCES `terminal` (`id_terminal`, `id_almacen`, `id_tienda`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `ss_usuario` ADD CONSTRAINT `Relationship13` FOREIGN KEY (`id_perfil`) REFERENCES `ss_perfil` (`id_perfil`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `almacen` ADD CONSTRAINT `Relationship16` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `terminal` ADD CONSTRAINT `Relationship17` FOREIGN KEY (`id_almacen`, `id_tienda`) REFERENCES `almacen` (`id_almacen`, `id_tienda`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta_producto` ADD CONSTRAINT `Relationship18` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta_producto` ADD CONSTRAINT `Relationship19` FOREIGN KEY (`id_producto`) REFERENCES `Producto` (`id_producto`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `ss_perfil_modulo` ADD CONSTRAINT `Relationship23` FOREIGN KEY (`id_modulo`) REFERENCES `ss_modulo` (`id_modulo`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `ss_perfil_modulo_campo` ADD CONSTRAINT `Relationship24` FOREIGN KEY (`id_campo`) REFERENCES `ss_campo` (`id_campo`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `ss_perfil_modulo_campo` ADD CONSTRAINT `Relationship25` FOREIGN KEY (`id_perfil`, `id_modulo`) REFERENCES `ss_perfil_modulo` (`id_perfil`, `id_modulo`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `venta_producto` ADD CONSTRAINT `Relationship26` FOREIGN KEY (`id_promocion`) REFERENCES `promocion` (`id_promocion`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `movimiento_producto` ADD CONSTRAINT `Relationship27` FOREIGN KEY (`id_tipo_transaccion`) REFERENCES `catalogo_transaccion` (`id_tipo_transaccion`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `movimiento_producto` ADD CONSTRAINT `Relationship29` FOREIGN KEY (`id_tipo_documento`) REFERENCES `catalogo_documento` (`id_tipo_documento`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

