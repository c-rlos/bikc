/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.vo;

import mx.com.kubez.bikc.dto.ConstanteDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import static mx.com.kubez.bikc.dao.ElementosPos.listaVenta;
import mx.com.kubez.bikc.dto.ProductoDTO;
import mx.com.kubez.bikc.dto.VentaDTO;
import mx.qubez.utilierias.conexion.Factory;
import static mx.com.kubez.bikc.vo.PosVO.venta;

/**
 *
 * @author Usuario1
 */
@ManagedBean(name="ajustes")
public class AjusteInventarioVO {
    public static final long serialVersionUID = 11233432428765L;
    private String campoBusqueda;
    private String placeholder;
    private String campoCantidad;
    private String campoRazon;
    private String descripcion;
    private String cantidad;
    private String barcode;
    private String pedido;
    private static ProductoDTO producto = new ProductoDTO();
    
    public void verCon(){
        //this.barcode = "10";
        System.out.println("cambiar");
    }
    
    @PostConstruct
    public void init() {
       
    }
    
    public boolean verificaLista(ProductoDTO p){
        for(int i = 0;i<listaVenta.size();i++){
            if(listaVenta.get(i).getBarcode().equals(p.getBarcode())){
                return true;
            }
        }
        return false;
    }
    
    
    
//    public void buscar(){
//        String query;
//        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
//        try {
//            PreparedStatement ps = null;
//                ResultSet rs;
//                //con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
//                try{
//                    int barcode = Integer.parseInt(this.campoBusqueda);
//                    query = "SELECT * FROM v_buscar where barcode = ?";
//                    ps = con.prepareStatement(query);
//                    ps.setInt(1, barcode);
//                }catch(NumberFormatException ex){
//                    query = "SELECT * FROM v_buscar where LOWER(descripcion) = LOWER(?)";
//                    ps = con.prepareStatement(query);
//                    ps.setString(1, this.campoBusqueda);
//                }
//                rs = ps.executeQuery();
//                
//                if(rs.next()){
//                    rs.previous();
//                    while(rs.next()){
//                            producto.setId(rs.getInt("id_producto"));
//                            producto.descripcion =rs.getString("descripcion");
//                            producto.importe = rs.getInt("costo");
//                            producto.setIva(rs.getDouble("iva"));
//                            producto.setBarcode(rs.getString("barcode"));
//                            producto.setPrecioUni(rs.getDouble("costo"));
//                            producto.setPrecioVenta(rs.getDouble("precio_uno"));
//                            producto.setPrecioMayoreo1(rs.getDouble("precio_dos"));
//                            producto.setPrecioMayoreo2(rs.getDouble("precio_tres"));
//                            producto.setCantidadInv(rs.getInt("cantidad"));
//                            producto.setId_promocion(1);
//                            producto.setEstatus(1);
//                            this.descripcion = rs.getString("descripcion");
//                            this.cantidad = rs.getInt("cantidad")+"";
//                            this.pedido = "0";
//                            this.barcode = rs.getString("barcode")+"";
//                            placeholder = "";
//                    }
//                    //return p;
//                }else{
//                    System.out.println("No se encontro el producto");
//                    placeholder = "El producto '"+this.campoBusqueda+"' no se encontro";
//                    this.campoBusqueda = "";
//                    //return p;
//                }
//                rs.close();
//                            ps.close();
//            } catch (Exception ex) {
//            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
//        }
//        finally {
//            Factory.close(con);
//
//        }
//        System.out.println("hola");
//    }

    public String getCampoBusqueda() {
        System.out.println("campoBusqueda -- get"+campoBusqueda);
        return campoBusqueda;
    }

    public void setCampoBusqueda(String campoBusqueda) {
        System.out.println("campoBusqueda -- set"+campoBusqueda);
        this.campoBusqueda = campoBusqueda;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getCampoCantidad() {
        return campoCantidad;
    }

    public void setCampoCantidad(String campoCantidad) {
        this.campoCantidad = campoCantidad;
    }

    public String getCampoRazon() {
        return campoRazon;
    }

    public void setCampoRazon(String campoRazon) {
        this.campoRazon = campoRazon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }
    
    public void ajustar(){
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        try{
            String query ;
            producto.setCantidad(Integer.parseInt(this.campoCantidad));
            String ob = this.campoRazon;System.out.println("...........");
            PreparedStatement ps;
                    query = "INSERT INTO movimiento_producto (id_tipo_transaccion, id_producto, barcode, cantidad, fecha_registro, numero_documento_externo,id_tipo_documento,observaciones) VALUES (4,?,?,?,?,1,1,?)";
                    ps = con.prepareStatement(query);
                    //ps = con.prepareStatement(query);
                    ps.setInt(1, producto.getIdProducto());
                    ps.setString(2, producto.getBarcode());
                    ps.setInt(3, producto.getCantidad());
                    java.util.Date d = new java.util.Date();
                    Date fechaActual = new Date(d.getTime());
                    ps.setDate(4, fechaActual);
                    ps.setString(5, ob);
                    ps.executeUpdate();
                    ps.close();
                    //buscar();
                    
        }
        catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
           Factory.close(con);
        }
    }
}
