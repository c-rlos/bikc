DROP VIEW v_vendedor;
CREATE VIEW v_vendedor AS
SELECT su.id_usuario,su.usuario,su.`password`,CONCAT(su.nombre,' ',su.a_paterno,' ',su.a_materno) as nombre, sp.nombre as perfil FROM ss_usuario su 
INNER JOIN ss_perfil sp ON sp.id_perfil = su.id_perfil