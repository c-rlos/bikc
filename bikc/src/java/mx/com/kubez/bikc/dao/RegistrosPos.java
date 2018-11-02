/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.dao;

import mx.com.kubez.bikc.dto.VentaDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.qubez.utilierias.conexion.Factory;
import mx.com.kubez.bikc.vo.PosVO;
import static mx.com.kubez.bikc.vo.PosVO.listaVenta;
import static mx.com.kubez.bikc.vo.PosVO.venta;

/**
 *
 * @author Usuario1
 */
public class RegistrosPos {

    public void registarCompra(PosVO pos, Connection con) {
        int id_venta = 0;
        Statement st = null;
        try {
            st = con.createStatement();
            String query;
            int estatus = 0;
            PreparedStatement ps;

            st.executeUpdate("BEGIN");
            if (!pos.v_en_espera) {
                query = query = "INSERT INTO venta (fecha_venta,id_cliente,id_usuario,id_terminal,id_almacen,id_tienda) VALUES (?,?,?,?,?,?)";
                ps = con.prepareStatement(query);
                ps.setDate(1, venta.getFechaVenta());
                ps.setInt(2, venta.getIdCliente());
                ps.setInt(3, venta.getIdUsuario());
                ps.setInt(4, venta.getIdTermninal());
                ps.setInt(5, venta.getIdAlmacen());
                ps.setInt(6, venta.getIdTienda());
                ps.executeUpdate();

                ps = con.prepareStatement("select last_insert_id() as id");
                System.out.println("paso---");
                ResultSet rs = ps.executeQuery();
                rs.next();
                id_venta = rs.getInt("id");
                venta.setIdVenta(id_venta);
                ps.close();
            }

            for (int i = 0; i < listaVenta.size(); i++) {
                if (listaVenta.get(i).getEstatus() == 1 || (!pos.v_en_espera & listaVenta.get(i).getEstatus() == 0)) {
                    query = "INSERT INTO venta_producto (id_venta,id_producto,cantidad,costo,precio_unitario,id_promocion,estatus,precio_venta,precio_venta_sin_iva,iva,barcode,descuento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps = con.prepareStatement(query);
                    //ps = con.prepareStatement(query);
                    ps.setInt(1, venta.getIdVenta());
                    ps.setInt(2, listaVenta.get(i).getIdProducto());
                    ps.setInt(3, listaVenta.get(i).getCantidad());
                    ps.setDouble(4, listaVenta.get(i).getCosto());
                    ps.setDouble(5, listaVenta.get(i).getPrecioUno());
                    ps.setInt(6, 1);
                    ps.setInt(7, listaVenta.get(i).getEstatus());
                    estatus = listaVenta.get(i).getEstatus();
                    ps.setDouble(8, (listaVenta.get(i).getCosto() * listaVenta.get(i).getCantidad()) + listaVenta.get(i).getIva());
                    ps.setDouble(9, (listaVenta.get(i).getCosto() * listaVenta.get(i).getCantidad()));
                    ps.setDouble(10, listaVenta.get(i).getIva());
                    ps.setString(11, listaVenta.get(i).getBarcode());
                    ps.setDouble(12, listaVenta.get(i).getDescuentoPromocion());
                    ps.executeUpdate();
                    ps.close();
                }
            }

            //if(estatus == 1){
            for (int i = 0; i < listaVenta.size(); i++) {
                if (listaVenta.get(i).getEstatus() == 1) {
                    query = "INSERT INTO movimiento_producto (id_tipo_transaccion, id_producto, barcode, cantidad, fecha_registro, numero_documento_externo,"
                            + "id_tipo_documento,id_almacen,id_tienda, id_usuario_registro) VALUES (1,?,?,?,?,1,1,?,?,?)";
                    ps = con.prepareStatement(query);
                    //ps = con.prepareStatement(query);
                    ps.setInt(1, listaVenta.get(i).getIdProducto());
                    ps.setString(2, listaVenta.get(i).getBarcode());
                    ps.setInt(3, -listaVenta.get(i).getCantidad());
                    java.util.Date d = new java.util.Date();
                    Date fechaActual = new Date(d.getTime());
                    ps.setDate(4, fechaActual);
                    ps.setInt(5, venta.getIdAlmacen());
                    ps.setInt(6, venta.getIdTienda());
                    ps.setInt(7, venta.getIdUsuario());
                    ps.executeUpdate();
                    ps.close();
                }
            }
            st.executeUpdate("COMMIT");
            st.close();
            //}
            if (estatus == 0) {
                VentaDTO v = new VentaDTO();
                v.setIdVenta(id_venta);
                pos.ventasEspera.add(v);
            }

        } catch (Exception ex) {
            try {
                st.executeUpdate("ROLLBACK");
            } catch (SQLException ex1) {
                Logger.getLogger(RegistrosPos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            //Factory.close(con);
        }
        pos.listaVenta.clear();
    }

    public void regisVentaEnEspera(PosVO pos, Connection con) {
        for (int i = 0; i < listaVenta.size(); i++) {
            listaVenta.get(i).setEstatus(0);
        }
        registarCompra(pos, con);
        pos.listaVenta.clear();
    }

    public void ventasEnEspera(PosVO pos, Connection con) {
        int id_venta = 0;
        if (pos.v_en_espera) {
            try {
                String query = "UPDATE venta SET fecha_venta = ?, id_cliente = ?, id_usuario = ?, id_terminal = ?, id_almacen= ?, id_tienda = ? WHERE id_venta = ?";
                int estatus = 0;
                PreparedStatement ps;
                ps = con.prepareStatement(query);
                ps.setDate(1, venta.getFechaVenta());
                ps.setInt(2, venta.getIdCliente());
                ps.setInt(3, venta.getIdUsuario());
                ps.setInt(4, venta.getIdTermninal());
                ps.setInt(5, venta.getIdAlmacen());
                ps.setInt(6, venta.getIdTienda());
                ps.setInt(7, venta.getIdVenta());
                ps.executeUpdate();
                id_venta = venta.getIdVenta();

                for (int i = 0; i < listaVenta.size(); i++) {
                    if (listaVenta.get(i).getEstatus() == 0) {
                        query = "UPDATE venta_producto SET id_producto = ?, cantidad = ?, costo = ?,precio_unitario = ?,id_promocion = ?,estatus = ?,precio_venta = ?,precio_venta_sin_iva = ?,iva = ?,barcode = ?, descuento = ? WHERE id_venta = ?";
                        ps = con.prepareStatement(query);
                        //ps = con.prepareStatement(query);

                        ps.setInt(1, listaVenta.get(i).getIdProducto());
                        ps.setInt(2, listaVenta.get(i).getCantidad());
                        ps.setDouble(3, listaVenta.get(i).getCosto());
                        ps.setDouble(4, listaVenta.get(i).getPrecioUno());
                        ps.setInt(5, 1);
                        ps.setInt(6, 1);
                        //estatus = 1;
                        ps.setDouble(7, (listaVenta.get(i).getCosto() * listaVenta.get(i).getCantidad()) + listaVenta.get(i).getIva());
                        ps.setDouble(8, (listaVenta.get(i).getCosto() * listaVenta.get(i).getCantidad()));
                        ps.setDouble(9, listaVenta.get(i).getIva());
                        ps.setString(10, listaVenta.get(i).getBarcode());
                        ps.setDouble(11, listaVenta.get(i).getDescuentoPromocion());
                        ps.setInt(12, id_venta);
                        ps.executeUpdate();
                        ps.close();
                    }
                }

                System.out.println("aqui si");
                for (int i = 0; i < listaVenta.size(); i++) {
                    if (listaVenta.get(i).getEstatus() == 0) {
                        query = "INSERT INTO movimiento_producto (id_tipo_transaccion,id_producto,barcode,cantidad,fecha_registro,numero_documento_externo,"
                                + "id_tipo_documento,id_almacen, id_tienda,id_usuario_registro) VALUES (1,?,?,?,?,1,?,?,?,?)";
                        ps = con.prepareStatement(query);
                        //ps = con.prepareStatement(query);
                        //.setInt(1, id_venta);
                        ps.setInt(1, listaVenta.get(i).getIdProducto());
                        ps.setString(2, listaVenta.get(i).getBarcode());
                        ps.setInt(3, -listaVenta.get(i).getCantidad());
                        java.util.Date d = new java.util.Date();
                        Date fechaActual = new Date(d.getTime());
                        ps.setDate(4, fechaActual);
                        ps.setInt(5, 1);
                        ps.setInt(6, venta.getIdAlmacen());
                        ps.setInt(7, venta.getIdTienda());
                        ps.setInt(8, venta.getIdUsuario());
                        ps.executeUpdate();
                        ps.close();
                    }
                }
                VentaDTO v = new VentaDTO();
                v.setIdVenta(id_venta);
                pos.ventasEspera.add(v);

                for (int i = 0; i < pos.ventasEspera.size(); i++) {
                    if (pos.ventasEspera.get(i).getIdVenta() == id_venta) {
                        pos.ventasEspera.remove(i);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
            } finally {

            }
            //pos.listaVenta.clear();
        }
    }

    public void cancelarEnEspera(PosVO pos, Connection con) {
        int id_venta = venta.getIdVenta();
        try {
            String query;
            PreparedStatement ps;
            for (int i = 0; i < listaVenta.size(); i++) {
                query = "DELETE FROM venta_producto WHERE id_venta = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id_venta);
                ps.executeUpdate();
                ps.close();
            }

            query = "DELETE FROM venta WHERE id_venta = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, venta.getIdVenta());
            ps.executeUpdate();
            id_venta = venta.getIdVenta();
            ps.close();

            VentaDTO v = new VentaDTO();
            v.setIdVenta(id_venta);
            pos.ventasEspera.add(v);

            for (int i = 0; i < pos.ventasEspera.size(); i++) {
                if (pos.ventasEspera.get(i).getIdVenta() == id_venta) {
                    pos.ventasEspera.remove(i);
                }
            }

            if (pos.ventasEspera.get(0).getIdVenta() == id_venta) {
                pos.ventasEspera.remove(0);
            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            try {
                con.close();
                //Factory.close(con);
            } catch (SQLException ex) {
                Logger.getLogger(PosVO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        pos.listaVenta.clear();
    }
}
