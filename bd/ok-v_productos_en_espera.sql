CREATE VIEW v_productos_en_espera AS
SELECT id_venta,barcode FROM venta_producto WHERE estatus = 0