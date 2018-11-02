-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.15-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bikc
--

CREATE DATABASE IF NOT EXISTS bikc;
USE bikc;

--
-- Temporary table structure for view `v_buscar`
--
DROP TABLE IF EXISTS `v_buscar`;
DROP VIEW IF EXISTS `v_buscar`;
CREATE TABLE `v_buscar` (
  `id_promocion` int(11),
  `id_producto_complemento` varchar(100),
  `aplica_promocion_cantidad` int(11),
  `descuento_promocion` decimal(10,2),
  `inventario` decimal(32,2),
  `id_producto` int(11),
  `barcode` varchar(20),
  `marca` varchar(20),
  `modelo` varchar(20),
  `color` varchar(20),
  `variante` varchar(20),
  `corrida` varchar(20),
  `categoria` varchar(20),
  `departamento` varchar(30),
  `unidad_medida` varchar(10),
  `corte` varchar(50),
  `forro` varchar(50),
  `suela` varchar(50),
  `dim_alto` varchar(100),
  `dim_ancho` varchar(100),
  `dim_largo` varchar(20),
  `stock_minimo` int(11),
  `stock_maximo` int(11),
  `codigo_proveedor` varchar(20),
  `descripcion` varchar(100),
  `costo` decimal(10,2),
  `iva` int(11),
  `precio_uno` decimal(10,2),
  `precio_dos` decimal(10,2),
  `precio_tres` decimal(10,2)
);

--
-- Temporary table structure for view `v_inventario`
--
DROP TABLE IF EXISTS `v_inventario`;
DROP VIEW IF EXISTS `v_inventario`;
CREATE TABLE `v_inventario` (
  `id_producto` int(11),
  `barcode` varchar(20),
  `variante` varchar(20),
  `id_almacen` int(11),
  `id_tienda` int(11),
  `inventario` decimal(32,2)
);

--
-- Definition of table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
CREATE TABLE `almacen` (
  `id_almacen` int(11) NOT NULL AUTO_INCREMENT,
  `almacen` varchar(20) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `id_tienda` int(11) NOT NULL,
  PRIMARY KEY (`id_almacen`,`id_tienda`),
  UNIQUE KEY `id_almacen` (`id_almacen`),
  KEY `Relationship16` (`id_tienda`),
  CONSTRAINT `Relationship16` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `almacen`
--

/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` (`id_almacen`,`almacen`,`descripcion`,`id_tienda`) VALUES 
 (1,'almacen 001','Almacen principal',1);
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;


--
-- Definition of table `catalogo_documento`
--

DROP TABLE IF EXISTS `catalogo_documento`;
CREATE TABLE `catalogo_documento` (
  `id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_elemento` varchar(50) DEFAULT NULL,
  `valor_elemento` varchar(50) DEFAULT NULL,
  `orden_elemento` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `predeterminado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`),
  UNIQUE KEY `id_documento` (`id_tipo_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `catalogo_documento`
--

/*!40000 ALTER TABLE `catalogo_documento` DISABLE KEYS */;
INSERT INTO `catalogo_documento` (`id_tipo_documento`,`tipo_elemento`,`valor_elemento`,`orden_elemento`,`activo`,`predeterminado`) VALUES 
 (1,'FAC','Factura',2,1,0),
 (2,'ODC','Orden de compra',3,1,0),
 (3,'NDV','Nota de venta',1,1,1);
/*!40000 ALTER TABLE `catalogo_documento` ENABLE KEYS */;


--
-- Definition of table `catalogo_transaccion`
--

DROP TABLE IF EXISTS `catalogo_transaccion`;
CREATE TABLE `catalogo_transaccion` (
  `id_tipo_transaccion` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_transaccion` varchar(20) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_transaccion`),
  UNIQUE KEY `id_transaccion` (`id_tipo_transaccion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `catalogo_transaccion`
--

/*!40000 ALTER TABLE `catalogo_transaccion` DISABLE KEYS */;
INSERT INTO `catalogo_transaccion` (`id_tipo_transaccion`,`codigo_transaccion`,`descripcion`,`estatus`) VALUES 
 (1,'VTA','Venta',1),
 (2,'DEV','Devolucion',1),
 (3,'ENT','Entrada de inventario',NULL);
/*!40000 ALTER TABLE `catalogo_transaccion` ENABLE KEYS */;


--
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `a_paterno` varchar(25) DEFAULT NULL,
  `a_materno` varchar(25) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `predeterminado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`,`nombre`,`a_paterno`,`a_materno`,`fecha_nacimiento`,`telefono`,`email`,`direccion`,`predeterminado`) VALUES 
 (1,'Público en general','Público en general','Público en general','1900-01-01','n/a','','Sin dirección',1),
 (2,'ana victoria','delgado','palomino','1983-01-01','2223449832','anam@gmail.com','Privada Alamos 43, Sta Ana Xalmimilulco',0);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `movimiento_producto`
--

DROP TABLE IF EXISTS `movimiento_producto`;
CREATE TABLE `movimiento_producto` (
  `id_movimiento_producto` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipo_transaccion` int(11) NOT NULL,
  `id_tipo_documento` int(11) NOT NULL,
  `ref_no_documento` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `barcode` varchar(20) DEFAULT NULL,
  `variante` varchar(20) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `numero_documento_externo` varchar(20) DEFAULT NULL,
  `id_almacen` int(11) DEFAULT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  `id_usuario_registro` int(11) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_movimiento_producto`,`id_tipo_transaccion`,`id_tipo_documento`),
  UNIQUE KEY `id_movimiento_producto` (`id_movimiento_producto`),
  KEY `Relationship27` (`id_tipo_transaccion`),
  KEY `Relationship29` (`id_tipo_documento`),
  CONSTRAINT `Relationship27` FOREIGN KEY (`id_tipo_transaccion`) REFERENCES `catalogo_transaccion` (`id_tipo_transaccion`),
  CONSTRAINT `Relationship29` FOREIGN KEY (`id_tipo_documento`) REFERENCES `catalogo_documento` (`id_tipo_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `movimiento_producto`
--

/*!40000 ALTER TABLE `movimiento_producto` DISABLE KEYS */;
INSERT INTO `movimiento_producto` (`id_movimiento_producto`,`id_tipo_transaccion`,`id_tipo_documento`,`ref_no_documento`,`id_producto`,`barcode`,`variante`,`cantidad`,`fecha_registro`,`numero_documento_externo`,`id_almacen`,`id_tienda`,`id_usuario_registro`,`observaciones`) VALUES 
 (2,2,2,62666,1,'0001320','320','100.00','2018-04-10 00:00:00','ODC100418-001',1,1,1,'sin observaciones'),
 (3,2,2,32233,2,'0002240','240','150.00','2018-04-14 00:00:00','ODC140418-002',1,1,1,'n/a');
/*!40000 ALTER TABLE `movimiento_producto` ENABLE KEYS */;


--
-- Definition of table `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(20) DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `variante` varchar(20) DEFAULT NULL,
  `corrida` varchar(20) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio_uno` decimal(10,2) DEFAULT NULL,
  `precio_dos` decimal(10,2) DEFAULT NULL,
  `precio_tres` decimal(10,2) DEFAULT NULL,
  `iva` int(11) NOT NULL,
  `categoria` varchar(20) DEFAULT NULL,
  `departamento` varchar(30) DEFAULT NULL,
  `unidad_medida` varchar(10) DEFAULT NULL,
  `corte` varchar(50) DEFAULT NULL,
  `forro` varchar(50) DEFAULT NULL,
  `suela` varchar(50) DEFAULT NULL,
  `dim_alto` varchar(100) DEFAULT NULL,
  `dim_ancho` varchar(100) DEFAULT NULL,
  `dim_largo` varchar(20) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `stock_maximo` int(11) DEFAULT NULL,
  `costo` decimal(10,2) DEFAULT NULL,
  `codigo_proveedor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `id_producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id_producto`,`barcode`,`marca`,`modelo`,`color`,`variante`,`corrida`,`descripcion`,`precio_uno`,`precio_dos`,`precio_tres`,`iva`,`categoria`,`departamento`,`unidad_medida`,`corte`,`forro`,`suela`,`dim_alto`,`dim_ancho`,`dim_largo`,`stock_minimo`,`stock_maximo`,`costo`,`codigo_proveedor`) VALUES 
 (1,'00001','Dockers','N230','Beige','320','280-360','Pantalón de vestir para caballero','250.00','240.00','235.00',16,'Ropa','Personal','PZA','','','','','','',1,10,'199.00','PRV001'),
 (2,'00002','Dockers','N240','Negro','320','280-360','Pantalón de vestir para caballero','250.00','240.00','235.00',16,'Ropa','Personal','PZA','','','','','','',1,10,'199.00','PRV001'),
 (3,'00003','Yelini','Pulsera Apolo','Plata','UNI','UNI-UNI','Pulsera de plata 16 oz. para dama','230.00','220.00','215.00',16,'Accesorios','Personal','PZA','','','','','','',1,10,'120.00','PRV002'),
 (4,'00004','Adidas','Brazuka','Blanco','UNI','UNI-UNI','Balon futbool del numero 4','290.00','285.00','280.00',16,'Accesorios','Deporte','PZA','','','','','','',1,10,'210.00','PRV003');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;


--
-- Definition of table `producto_promocion`
--

DROP TABLE IF EXISTS `producto_promocion`;
CREATE TABLE `producto_promocion` (
  `id_producto_promocion` int(11) NOT NULL,
  `id_promocion` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_producto_complemento` varchar(100) DEFAULT NULL,
  `aplica_promocion_cantidad` int(11) DEFAULT NULL,
  `descuento_promocion` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_producto_promocion`),
  KEY `IX_Relationship35` (`id_promocion`),
  KEY `IX_Relationship36` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto_promocion`
--

/*!40000 ALTER TABLE `producto_promocion` DISABLE KEYS */;
INSERT INTO `producto_promocion` (`id_producto_promocion`,`id_promocion`,`id_producto`,`id_producto_complemento`,`aplica_promocion_cantidad`,`descuento_promocion`) VALUES 
 (1,1,1,'',0,'0.00'),
 (2,1,2,'',0,'0.00'),
 (3,1,3,'',0,'0.00'),
 (4,1,4,'',0,'0.00'),
 (5,2,3,'',2,'50.00');
/*!40000 ALTER TABLE `producto_promocion` ENABLE KEYS */;


--
-- Definition of table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
CREATE TABLE `promocion` (
  `id_promocion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_vigencia_inicio` datetime DEFAULT NULL,
  `fecha_vigencia_fin` datetime DEFAULT NULL,
  PRIMARY KEY (`id_promocion`),
  UNIQUE KEY `id_promocion` (`id_promocion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `promocion`
--

/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
INSERT INTO `promocion` (`id_promocion`,`nombre`,`descripcion`,`fecha_vigencia_inicio`,`fecha_vigencia_fin`) VALUES 
 (1,'N/A','Sin promocion','1900-01-01 00:00:00','2100-01-01 00:00:00'),
 (2,'Agua y media','Llevate 2 y paga 1 y medio','2018-03-01 00:00:00','2018-03-05 00:00:00');
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;


--
-- Definition of table `ss_campo`
--

DROP TABLE IF EXISTS `ss_campo`;
CREATE TABLE `ss_campo` (
  `id_campo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_campo`),
  UNIQUE KEY `id_campo` (`id_campo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_campo`
--

/*!40000 ALTER TABLE `ss_campo` DISABLE KEYS */;
INSERT INTO `ss_campo` (`id_campo`,`nombre`,`descripcion`) VALUES 
 (1,'cobrar','Botón cobrar'),
 (2,'documento','Botón selecciona el tipo de documento'),
 (3,'cliente','Botón para seleccionar el tipo de cliente'),
 (4,'cajero','Botón para seleccionar el tiipo de cajero'),
 (5,'esperar','Botón poner venta en espera'),
 (6,'continuar','Botón retomar venta'),
 (7,'buscar','Botón buscar producto'),
 (8,'cantidad','Botón cantidad'),
 (9,'precio','Botón cambiar el precio del producto'),
 (10,'eliminar','Botón quitar producto de la venta actual'),
 (11,'verificar','Botón verificar precio'),
 (12,'cancelar','Botón cancelar venta');
/*!40000 ALTER TABLE `ss_campo` ENABLE KEYS */;


--
-- Definition of table `ss_modulo`
--

DROP TABLE IF EXISTS `ss_modulo`;
CREATE TABLE `ss_modulo` (
  `id_modulo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`),
  UNIQUE KEY `id_modulo` (`id_modulo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_modulo`
--

/*!40000 ALTER TABLE `ss_modulo` DISABLE KEYS */;
INSERT INTO `ss_modulo` (`id_modulo`,`nombre`,`descripcion`,`url`) VALUES 
 (1,'main','Menu principal','main.jsf'),
 (2,'pos','Punto de venta','pos.jsf'),
 (3,'inventario','administracion de inventario','inventario.jsf'),
 (4,'ajuste','Ajustes de inventario','ajuste.jsf'),
 (5,'configuracion','Configuracion de la POS','configuracion.jsf'),
 (6,'salir','Salir','logout.jsf');
/*!40000 ALTER TABLE `ss_modulo` ENABLE KEYS */;


--
-- Definition of table `ss_perfil`
--

DROP TABLE IF EXISTS `ss_perfil`;
CREATE TABLE `ss_perfil` (
  `id_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `modulo_inicio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_perfil`),
  UNIQUE KEY `id_perfil` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_perfil`
--

/*!40000 ALTER TABLE `ss_perfil` DISABLE KEYS */;
INSERT INTO `ss_perfil` (`id_perfil`,`nombre`,`descripcion`,`modulo_inicio`) VALUES 
 (1,'Administrador','Administrador del sistema','main.jsf'),
 (2,'Gerente','Gerente de tienda','main.jsf'),
 (3,'Vendedor','Vendedor de tienda','main.jsf');
/*!40000 ALTER TABLE `ss_perfil` ENABLE KEYS */;


--
-- Definition of table `ss_perfil_modulo`
--

DROP TABLE IF EXISTS `ss_perfil_modulo`;
CREATE TABLE `ss_perfil_modulo` (
  `id_perfil` int(11) NOT NULL,
  `id_modulo` int(11) NOT NULL,
  PRIMARY KEY (`id_perfil`,`id_modulo`),
  KEY `Relationship23` (`id_modulo`),
  CONSTRAINT `Relationship2` FOREIGN KEY (`id_perfil`) REFERENCES `ss_perfil` (`id_perfil`),
  CONSTRAINT `Relationship23` FOREIGN KEY (`id_modulo`) REFERENCES `ss_modulo` (`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_perfil_modulo`
--

/*!40000 ALTER TABLE `ss_perfil_modulo` DISABLE KEYS */;
INSERT INTO `ss_perfil_modulo` (`id_perfil`,`id_modulo`) VALUES 
 (1,1),
 (2,1),
 (3,1),
 (1,2),
 (2,2),
 (3,2),
 (1,3),
 (2,3),
 (3,3),
 (1,4),
 (2,4),
 (1,5),
 (2,5),
 (1,6);
/*!40000 ALTER TABLE `ss_perfil_modulo` ENABLE KEYS */;


--
-- Definition of table `ss_perfil_modulo_campo`
--

DROP TABLE IF EXISTS `ss_perfil_modulo_campo`;
CREATE TABLE `ss_perfil_modulo_campo` (
  `id_perfil` int(11) NOT NULL,
  `id_modulo` int(11) NOT NULL,
  `id_campo` int(11) NOT NULL,
  PRIMARY KEY (`id_campo`,`id_perfil`,`id_modulo`),
  KEY `Relationship25` (`id_perfil`,`id_modulo`),
  CONSTRAINT `Relationship24` FOREIGN KEY (`id_campo`) REFERENCES `ss_campo` (`id_campo`),
  CONSTRAINT `Relationship25` FOREIGN KEY (`id_perfil`, `id_modulo`) REFERENCES `ss_perfil_modulo` (`id_perfil`, `id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_perfil_modulo_campo`
--

/*!40000 ALTER TABLE `ss_perfil_modulo_campo` DISABLE KEYS */;
INSERT INTO `ss_perfil_modulo_campo` (`id_perfil`,`id_modulo`,`id_campo`) VALUES 
 (1,2,1),
 (1,2,2),
 (1,2,3),
 (1,2,4),
 (1,2,5),
 (1,2,6),
 (1,2,7),
 (1,2,8),
 (1,2,9),
 (1,2,10),
 (1,2,11),
 (1,2,12),
 (1,3,1);
/*!40000 ALTER TABLE `ss_perfil_modulo_campo` ENABLE KEYS */;


--
-- Definition of table `ss_usuario`
--

DROP TABLE IF EXISTS `ss_usuario`;
CREATE TABLE `ss_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `a_paterno` varchar(20) DEFAULT NULL,
  `a_materno` varchar(30) DEFAULT NULL,
  `e_mail` varchar(100) DEFAULT NULL,
  `direccion` varchar(250) DEFAULT NULL,
  `telefono_1` varchar(50) DEFAULT NULL,
  `telefono_2` varchar(50) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `estatus` smallint(6) DEFAULT NULL,
  `id_perfil` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  KEY `IX_Relationship13` (`id_perfil`),
  CONSTRAINT `Relationship13` FOREIGN KEY (`id_perfil`) REFERENCES `ss_perfil` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ss_usuario`
--

/*!40000 ALTER TABLE `ss_usuario` DISABLE KEYS */;
INSERT INTO `ss_usuario` (`id_usuario`,`usuario`,`password`,`nombre`,`a_paterno`,`a_materno`,`e_mail`,`direccion`,`telefono_1`,`telefono_2`,`fecha_nacimiento`,`estatus`,`id_perfil`) VALUES 
 (2,'cjuarez','cjuarez','Carlos','Juarez','Ramirez','cjuarez.uth@gmail.com','Palma 51','222 705 9239',NULL,'1982-03-07',1,1);
/*!40000 ALTER TABLE `ss_usuario` ENABLE KEYS */;


--
-- Definition of table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
CREATE TABLE `terminal` (
  `id_terminal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `activo` bit(1) DEFAULT NULL,
  `predeterminada` bit(1) DEFAULT NULL,
  `id_almacen` int(11) NOT NULL,
  `id_tienda` int(11) NOT NULL,
  PRIMARY KEY (`id_terminal`,`id_almacen`,`id_tienda`),
  UNIQUE KEY `id_terminal` (`id_terminal`),
  KEY `Relationship17` (`id_almacen`,`id_tienda`),
  CONSTRAINT `Relationship17` FOREIGN KEY (`id_almacen`, `id_tienda`) REFERENCES `almacen` (`id_almacen`, `id_tienda`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `terminal`
--

/*!40000 ALTER TABLE `terminal` DISABLE KEYS */;
INSERT INTO `terminal` (`id_terminal`,`nombre`,`descripcion`,`activo`,`predeterminada`,`id_almacen`,`id_tienda`) VALUES 
 (2,'j','j',0x01,0x01,1,1);
/*!40000 ALTER TABLE `terminal` ENABLE KEYS */;


--
-- Definition of table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
CREATE TABLE `tienda` (
  `id_tienda` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `direccion` varchar(250) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `codigo_postal` int(11) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_tienda`),
  UNIQUE KEY `id_tienda` (`id_tienda`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tienda`
--

/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
INSERT INTO `tienda` (`id_tienda`,`nombre`,`direccion`,`estado`,`codigo_postal`,`telefono`) VALUES 
 (1,'001 Puebla centro','5 de Mayo, 3338','Puebla',72100,'2224322211'),
 (2,'002 Puebla plaza dorada','Blvd. 5 de mayo 3312, interior 554 local 1233','Puebla',72100,'2224322133'),
 (3,'003 Puebla galerias','Blvd. Hermanos Serdan, 655, interior 33 local 332','Puebla',72100,'2224327733');
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;


--
-- Definition of table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_venta` datetime DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_terminal` int(11) DEFAULT NULL,
  `id_almacen` int(11) DEFAULT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  UNIQUE KEY `id_venta` (`id_venta`),
  KEY `IX_Relationship6` (`id_cliente`),
  KEY `IX_Relationship11` (`id_terminal`,`id_almacen`,`id_tienda`),
  KEY `IX_Relationship8` (`id_usuario`),
  CONSTRAINT `Relationship11` FOREIGN KEY (`id_terminal`, `id_almacen`, `id_tienda`) REFERENCES `terminal` (`id_terminal`, `id_almacen`, `id_tienda`),
  CONSTRAINT `Relationship6` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `Relationship8` FOREIGN KEY (`id_usuario`) REFERENCES `ss_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `venta`
--

/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` (`id_venta`,`fecha_venta`,`id_cliente`,`id_usuario`,`id_terminal`,`id_almacen`,`id_tienda`) VALUES 
 (4,'1900-01-01 00:00:00',1,2,2,1,1),
 (5,'1900-01-01 00:00:00',1,2,2,1,1);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;


--
-- Definition of table `venta_producto`
--

DROP TABLE IF EXISTS `venta_producto`;
CREATE TABLE `venta_producto` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) NOT NULL,
  `barcode` varchar(20) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `costo` decimal(10,2) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `precio_venta` decimal(10,2) DEFAULT NULL,
  `precio_venta_sin_iva` decimal(10,2) DEFAULT NULL,
  `iva` decimal(10,2) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT NULL,
  `estatus` int(11) DEFAULT NULL,
  `devolucion` bit(1) DEFAULT NULL,
  `motivo_devolucion` varchar(100) DEFAULT NULL,
  `id_promocion` int(11) NOT NULL,
  PRIMARY KEY (`id_venta`,`id_producto`,`id_promocion`),
  KEY `IX_Relationship26` (`id_promocion`),
  KEY `Relationship19` (`id_producto`),
  CONSTRAINT `Relationship18` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `Relationship19` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `Relationship26` FOREIGN KEY (`id_promocion`) REFERENCES `promocion` (`id_promocion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `venta_producto`
--

/*!40000 ALTER TABLE `venta_producto` DISABLE KEYS */;
INSERT INTO `venta_producto` (`id_venta`,`id_producto`,`barcode`,`cantidad`,`costo`,`precio_unitario`,`precio_venta`,`precio_venta_sin_iva`,`iva`,`descuento`,`estatus`,`devolucion`,`motivo_devolucion`,`id_promocion`) VALUES 
 (4,1,'yyy','1.00','7.00','7.00','7.00','7.00','7.00','7.00',1,NULL,NULL,1),
 (4,2,'yyy','7.00','7.00','7.00','7.00','7.00','7.00','7.00',1,NULL,NULL,1);
/*!40000 ALTER TABLE `venta_producto` ENABLE KEYS */;


--
-- Definition of view `v_buscar`
--

DROP TABLE IF EXISTS `v_buscar`;
DROP VIEW IF EXISTS `v_buscar`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_buscar` AS select `pp`.`id_promocion` AS `id_promocion`,`pp`.`id_producto_complemento` AS `id_producto_complemento`,`pp`.`aplica_promocion_cantidad` AS `aplica_promocion_cantidad`,`pp`.`descuento_promocion` AS `descuento_promocion`,ifnull(`i`.`inventario`,0) AS `inventario`,`p`.`id_producto` AS `id_producto`,`p`.`barcode` AS `barcode`,`p`.`marca` AS `marca`,`p`.`modelo` AS `modelo`,`p`.`color` AS `color`,`p`.`variante` AS `variante`,`p`.`corrida` AS `corrida`,`p`.`categoria` AS `categoria`,`p`.`departamento` AS `departamento`,`p`.`unidad_medida` AS `unidad_medida`,`p`.`corte` AS `corte`,`p`.`forro` AS `forro`,`p`.`suela` AS `suela`,`p`.`dim_alto` AS `dim_alto`,`p`.`dim_ancho` AS `dim_ancho`,`p`.`dim_largo` AS `dim_largo`,`p`.`stock_minimo` AS `stock_minimo`,`p`.`stock_maximo` AS `stock_maximo`,`p`.`codigo_proveedor` AS `codigo_proveedor`,`p`.`descripcion` AS `descripcion`,`p`.`costo` AS `costo`,`p`.`iva` AS `iva`,`p`.`precio_uno` AS `precio_uno`,`p`.`precio_dos` AS `precio_dos`,`p`.`precio_tres` AS `precio_tres` from ((`producto` `p` left join `producto_promocion` `pp` on((`p`.`id_producto` = `pp`.`id_producto`))) left join `v_inventario` `i` on((`p`.`id_producto` = `i`.`id_producto`)));

--
-- Definition of view `v_inventario`
--

DROP TABLE IF EXISTS `v_inventario`;
DROP VIEW IF EXISTS `v_inventario`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_inventario` AS select `mp`.`id_producto` AS `id_producto`,`mp`.`barcode` AS `barcode`,`mp`.`variante` AS `variante`,`mp`.`id_almacen` AS `id_almacen`,`mp`.`id_tienda` AS `id_tienda`,sum(`mp`.`cantidad`) AS `inventario` from `movimiento_producto` `mp` group by `mp`.`id_producto`,`mp`.`barcode`,`mp`.`variante`,`mp`.`id_almacen`,`mp`.`id_tienda`;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
