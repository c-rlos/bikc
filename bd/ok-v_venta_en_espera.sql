DROP VIEW v_venta_en_espera;
CREATE VIEW v_venta_en_espera AS
SELECT v.id_venta,v.id_almacen,v.id_terminal,v.id_usuario,v.id_tienda,vp.estatus FROM venta v
INNER JOIN venta_producto vp ON vp.id_venta = v.id_venta WHERE estatus = 0 GROUP BY vp.id_venta