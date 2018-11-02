/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.vo;

import mx.com.kubez.bikc.dto.ConstanteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import mx.com.kubez.bikc.dto.ProductoDTO;
import mx.qubez.utilierias.conexion.Factory;
import static mx.com.kubez.bikc.vo.PosVO.listaDefault;

/**
 *
 * @author Usuario1
 */
@ManagedBean(name="inventario")
public class InventarioVO {
    
        public static final long serialVersionUID = 11233432428765L;
        
    private List<ProductoDTO> listaProductos;
    
    private List<ProductoDTO> listaProductosTemp;
    private static List<ProductoDTO> listaDefault;
    private String campoBusqueda;
    

    /**
     * Get the value of listaProductosTemp
     *
     * @return the value of listaProductosTemp
     */
    public List<ProductoDTO> getListaProductosTemp() {
        return listaProductosTemp;
    }

    /**
     * Set the value of listaProductosTemp
     *
     * @param listaProductosTemp new value of listaProductosTemp
     */
    public void setListaProductosTemp(List<ProductoDTO> listaProductosTemp) {
        this.listaProductosTemp = listaProductosTemp;
    }

    
    private List<ProductoDTO> filteredProducts;

    /**
     * Get the value of filteredProducts
     *
     * @return the value of filteredProducts
     */
    public List<ProductoDTO> getFilteredProducts() {
        return filteredProducts;
    }

    /**
     * Set the value of filteredProducts
     *
     * @param filteredProducts new value of filteredProducts
     */
    public void setFilteredProducts(List<ProductoDTO> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    
    @PostConstruct
    public void init() {
       filteredProducts = new ArrayList<ProductoDTO>();
       ProductoDTO p = new ProductoDTO();
       p.setIdProducto(1);
       p.setDescripcion("Gansito");
       p.setCosto(10);
       p.setCantidad(10);
       filteredProducts.add(p);
       Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
       consultarProductos(con);
    }
    
    public void consultarProductos(Connection con){
        try {
                //con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
                
                Statement st = con.createStatement();
                ResultSet rs;
                String query = "SELECT * FROM v_buscar";
                
                rs = st.executeQuery(query);
                if(rs.next()){
                    listaDefault = new ArrayList<ProductoDTO>();
                    rs.previous();
                    while(rs.next()){
                        ProductoDTO p = new ProductoDTO();
                        p.setIdProducto(rs.getInt("id_producto"));
                        p.setBarcode(rs.getInt("barcode")+"");
                        p.setDescripcion(rs.getString("descripcion"));
                        p.setPrecioUno(rs.getDouble("precio_uno"));
                        p.setPrecioDos(rs.getDouble("precio_dos"));
                        p.setPrecioTres(rs.getDouble("precio_tres"));
                        p.setInventario(rs.getInt("cantidad"));
                        //System.out.println(p.descripcion+"--");
                        listaDefault.add(p);
                    }
                }else{
                    System.out.println("No exite el producto");
                }
            } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }
        listaProductos = listaDefault;
    }

    /**
     * Get the value of listaProductos
     *
     * @return the value of listaProductos
     */
      public List<ProductoDTO> getListaProductos(){
        //List<Producto> lista = null;
        
        return listaProductos;
    }

    /**
     * Set the value of listaProductos
     *
     * @param listaProductos new value of listaProductos
     */
    public void setListaProductos(List<ProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }
    
    public void filtrar(){
        listaProductos = null;
        listaProductos = listaDefault;
        try{
            int barcode = Integer.parseInt(campoBusqueda);
            System.out.println("barcode-- "+barcode);
            for(int i=listaProductos.size()-1;i>0;i--){
                if(String.valueOf(listaProductos.get(i).getBarcode()).startsWith(campoBusqueda));
                else listaProductos.remove(i);
            }
            if(String.valueOf(listaProductos.get(0).getBarcode()).startsWith(barcode+""));
            else listaProductos.remove(0);
        }catch(NumberFormatException  ex){
            for(int i=listaProductos.size()-1;i>0;i--){
                if(listaProductos.get(i).getDescripcion().toLowerCase().startsWith(campoBusqueda.toLowerCase())){

                }else{
                    listaProductos.remove(i);
                }
            }
            if(listaProductos.get(0).getDescripcion().toLowerCase().startsWith(campoBusqueda.toLowerCase())){

            }else{
                listaProductos.remove(0);
            }
        }
        System.out.println("filtro-----------"+campoBusqueda+"--");
    }
    
         
}
