Drop VIEW v_buscar;
CREATE VIEW v_buscar AS
SELECT p.id_producto, p.barcode, p.descripcion, p.costo, p.iva, p.precio_uno, p.precio_dos, p.precio_tres, SUM(mp.cantidad) as cantidad, pp.	, pp.descuento_promocion 
FROM movimiento_producto mp 
INNER JOIN producto p ON p.id_producto = mp.id_producto
INNER JOIN producto_promocion pp ON pp.id_producto = p.id_producto GROUP BY p.id_producto 