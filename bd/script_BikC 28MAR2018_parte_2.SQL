INSERT INTO `tienda` (`id_tienda`,`nombre`,`direccion`,`estado`,`codigo_postal`,`telefono`) VALUES
 (1,'001 Puebla centro','5 de Mayo, 3338','Puebla',72100,'2224322211'),
 (2,'002 Puebla plaza dorada','Blvd. 5 de mayo 3312, interior 554 local 1233','Puebla',72100,'2224322133'),
 (3,'003 Puebla galerias','Blvd. Hermanos Serdan, 655, interior 33 local 332','Puebla',72100,'2224327733');
 
INSERT INTO `almacen` (`id_almacen`,`almacen`,`descripcion`,`id_tienda`) VALUES 
 (1,'almacen 001','Almacen principal',1);

 INSERT INTO `cliente` (`id_cliente`,`nombre`,`a_paterno`,`a_materno`,`fecha_nacimiento`,`telefono`,`email`,`direccion`,`predeterminado`) VALUES
 (1,'carlos','juarez','ramirez','1982-01-01','2223442298','carlos@gmail.com','Palma 51, Sta Ana Xalmimilulco',1),
 (2,'ana maria','delgado','palomino','1983-01-01','2223449832','anam@gmail.com','Privada Alamos 43, Sta Ana Xalmimilulco',0);
 

 INSERT INTO `producto` (`id_producto`,`barcode`,`marca`,`modelo`,`color`,`variante`,`corrida`,`descripcion`,`precio_uno`,`precio_dos`,`precio_tres`,`iva`,`categoria`,`departamento`,`unidad_medida`,`corte`,`forro`,`suela`,`dim_alto`,`dim_ancho`,`dim_largo`,`stock_minimo`,`stock_maximo`,`costo`,`codigo_proveedor`) VALUES 
 (1,'00001','Dockers','N230','Beige','320','280-360','Pantalón de vestir para caballero','250','240','235',16,'Ropa','Personal','PZA','','','','','','',1,10,'199','PRV001'),
 (2,'00002','Dockers','N240','Negro','320','280-360','Pantalón de vestir para caballero','250','240','235',16,'Ropa','Personal','PZA','','','','','','',1,10,'199','PRV001'),
 (3,'00003','Yelini','Pulsera Apolo','Plata','UNI','UNI-UNI','Pulsera de plata 16 oz. para dama','230','220','215',16,'Accesorios','Personal','PZA','','','','','','',1,10,'120','PRV002'),
 (4,'00004','Adidas','Brazuka','Blanco','UNI','UNI-UNI','Balon futbool del numero 4','290','285','280',16,'Accesorios','Deporte','PZA','','','','','','',1,10,'210','PRV003');

 INSERT INTO `promocion` (`id_promocion`,`nombre`,`descripcion`,`fecha_vigencia_inicio`,`fecha_vigencia_fin`) VALUES
 (1,'N/A','Sin promocion','1900-01-01','2100-01-01'),
 (2,'Agua y media','Llevate 2 y paga 1 y medio','2018-03-01','2018-03-05');
 
 INSERT INTO `producto_promocion` (`id_producto_promocion`,`id_promocion`,`id_producto`,`id_producto_complemento`,`aplica_promocion_cantidad`,`descuento_promocion`) VALUES
 (1,1,1,'',0, 0),
 (2,1,2,'',0, 0),
 (3,1,3,'',0, 0),
 (4,1,4,'',0, 0),
 (5,2,3,'',2, 50);
 

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

 INSERT INTO `ss_modulo` (`id_modulo`,`nombre`,`descripcion`,`url`) VALUES 
 (1,'main','Menu principal','main.jsf'),
 (2,'pos','Punto de venta','pos.jsf'),
 (3,'inventario','administracion de inventario','inventario.jsf'),
 (4,'ajuste','Ajustes de inventario','ajuste.jsf'),
 (5,'configuracion','Configuracion de la POS','configuracion.jsf'),
 (6,'salir','Salir','logout.jsf');

 INSERT INTO `ss_perfil` (`id_perfil`,`nombre`,`descripcion`,`modulo_inicio`) VALUES 
 (1,'Administrador','Administrador del sistema','main.jsf'),
 (2,'Gerente','Gerente de tienda','main.jsf'),
 (3,'Vendedor','Vendedor de tienda','main.jsf');

 INSERT INTO `ss_perfil_modulo` (`id_perfil`,`id_modulo`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (1,5),
 (1,6),
 (2,1),
 (2,2),
 (2,3),
 (2,4),
 (2,5),
 (3,1),
 (3,2),
 (3,3);

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

 INSERT INTO `ss_usuario` (`id_usuario`,`usuario`,`password`,`nombre`,`a_paterno`,`a_materno`,`e_mail`,`direccion`,`telefono_1`,`telefono_2`,`fecha_nacimiento`,`estatus`,`id_perfil`) VALUES 
 (2,'cjuarez','cjuarez','Carlos','Juarez','Ramirez','cjuarez.uth@gmail.com','Palma 51','222 705 9239',NULL,'1982-03-07',1,1);
 
 INSERT INTO `terminal` (`id_terminal`,`nombre`,`descripcion`,`activo`,`predeterminada`,`id_almacen`,`id_tienda`) VALUES
 (2,'j','j',0x01,0x01,1,1);

 INSERT INTO `venta` (`id_venta`,`fecha_venta`,`id_cliente`,`id_usuario`,`id_terminal`,`id_almacen`,`id_tienda`) VALUES
 (4,'1900-01-01 00:00:00',1,2,2,1,1),
 (5,'1900-01-01 00:00:00',1,2,2,1,1);

 INSERT INTO `venta_producto` (`id_venta`,`id_producto`,`barcode`,`cantidad`,`costo`,`precio_unitario`,`precio_venta`,`precio_venta_sin_iva`,`iva`,`descuento`,`estatus`,`id_promocion`,`devolucion`,`motivo_devolucion`) VALUES
 (4,1,'yyy','1.00','7.00','7.00','7.00','7.00','7.00','7.00',1,1,NULL,NULL),
 (4,2,'yyy','7.00','7.00','7.00','7.00','7.00','7.00','7.00',1,1,NULL,NULL);
