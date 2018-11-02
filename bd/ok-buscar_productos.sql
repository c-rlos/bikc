SELECT p.id_producto, p.barcode, p.descripcion, p.costo, p.iva, p.precio_uno, p.precio_dos, p.precio_tres, SUM(mp.cantidad) as cantidad 
FROM movimiento_producto mp
INNER JOIN producto p ON p.id_producto = mp.id_producto