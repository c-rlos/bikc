/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.dao;

import mx.com.kubez.bikc.dto.ClienteDTO;
import java.io.Serializable;
import mx.com.kubez.bikc.dto.VentaDTO;
import mx.com.kubez.bikc.dto.ProductoDTO;
import mx.com.kubez.bikc.dto.ConstanteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import mx.qubez.utilierias.conexion.Factory;
import mx.com.kubez.bikc.vo.PosVO;
import static mx.com.kubez.bikc.vo.PosVO.listaVenta;

/**
 *
 * @author Usuario1
 */
public class PosDAO implements Serializable {

    public void buscar(PosVO pos) {
        String query;
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        try {
            PreparedStatement ps = null;
            ResultSet rs;
            //con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
            try {
                int barcode = Integer.parseInt(pos.getCampoBusqueda());
                query = "SELECT * FROM v_buscar where barcode = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, barcode);
            } catch (NumberFormatException ex) {
                query = "SELECT * FROM v_buscar where LOWER(descripcion) = LOWER(?)";
                ps = con.prepareStatement(query);
                ps.setString(1, pos.getCampoBusqueda());
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    if (rs.getInt("inventario") > 0) {
                        ProductoDTO p = new ProductoDTO();
                        p.setIdProducto(rs.getInt("id_producto"));
                        p.setDescripcion(rs.getString("descripcion"));
                        p.setCosto(rs.getInt("costo"));

                        p.setBarcode(rs.getString("barcode"));
                        p.setPrecioUno(rs.getDouble("precio_uno"));
                        p.setPrecioDos(rs.getDouble("precio_dos"));
                        p.setPrecioTres(rs.getDouble("precio_tres"));
                        p.setIva(rs.getDouble("iva"));

                        p.setCategoria(rs.getString("categoria"));
                        p.setDepartamento(rs.getString("departamento"));
                        p.setUnidadMedida(rs.getString("unidad_medida"));
                        p.setCorte(rs.getString("corte"));
                        p.setForro(rs.getString("forro"));
                        p.setSuela(rs.getString("suela"));
                        p.setDimAlto(rs.getString("dim_alto"));
                        p.setDimAncho(rs.getString("dim_ancho"));
                        p.setDimLargo(rs.getString("dim_largo"));
                        p.setStockMinimo(rs.getInt("stock_minimo"));
                        p.setStockMaximo(rs.getInt("stock_maximo"));
                        p.setCosto(rs.getDouble("costo"));
                        p.setCodigoProveedor(rs.getString("codigo_proveedor"));
                        p.setIdPromocion(rs.getInt("id_promocion"));
                        p.setIdProductoComplemento(rs.getInt("id_producto_complemento"));
                        p.setAplicaPromocionCantidad(rs.getInt("aplica_promocion_cantidad"));
                        p.setDescuentoPromocion(rs.getInt("descuento_promocion"));

                        if (!pos.verificaLista(p)) {
                            listaVenta.add(p);
                        }
                        pos.setPlaceHolder("");
                        //pos.totalPagar();

                    }
                }
                //return p;
            } else {
                System.out.println("No se encontro el producto");
                pos.setPlaceHolder("El producto '" + pos.getCampoBusqueda() + "' no se encontro");
                pos.setCampoBusqueda("");
                //pos.totalPagar();
                //return p;
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }
        System.out.println("hola");
    }

    public void obtenerTerminales(PosVO pos, Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query = "select * from terminal where activo = 1";

            rs = st.executeQuery(query);
            if (rs.next()) {

                //pos.venta.setId_usuario(2);
                //List<SelectItem> terminales = new ArrayList<SelectItem>();
                //pos.setTerminales(terminales);
                pos.listaTerminales = new ArrayList<Terminal>();
                rs.previous();
                while (rs.next()) {
                    //SelectItem sI = new SelectItem(rs.getString("nombre"));
                    //terminales.add(sI);

                    Terminal t = new Terminal(rs.getInt("id_terminal"), rs.getString("nombre"),
                            rs.getString("descripcion"), rs.getInt("predeterminada"), rs.getInt("id_tienda"), rs.getInt("id_almacen"));
                    pos.listaTerminales.add(t);
                    if (t.getPredeterminada() == 1) {
                        pos.venta.setIdTermninal(t.getId_terminal());
                        pos.venta.setIdAlmacen(t.getId_almacen());
                        pos.venta.setIdTienda(t.getId_tienda());
                    }
                    //pos.venta.setId_cliente(Integer.parseInt("1"));
                }
                //pos.setTerminales(terminales);
            } else {
                System.out.println("No hay clientes");
            }
            rs.close();
            st.close();
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            //Factory.close(con);
        }
    }

    public void obtenerDocumentos(PosVO pos, Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query = "select tipo_elemento from catalogo_documento";

            rs = st.executeQuery(query);
            if (rs.next()) {
                List<SelectItem> documentos = new ArrayList<SelectItem>();
                pos.setDocumentos(documentos);
                rs.previous();
                while (rs.next()) {
                    SelectItem sI = new SelectItem(rs.getString("tipo_elemento"));
                    documentos.add(sI);
                }
                pos.setDocumentos(documentos);
            } else {
                System.out.println("No hay clientes");
            }
            rs.close();
            st.close();
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
        }
    }

    public String validaVendedor(PosVO pos, Connection con) {
        try {
            ResultSet rs;
            String query = "SELECT * FROM v_vendedor where usuario = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pos.getUsername());
            ps.setString(2, pos.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    pos.getVendedores().get(0).setLabel(rs.getString("nombre"));
                    pos.venta.setIdUsuario(rs.getInt("id_usuario"));
                }
                rs.close();
                ps.close();
            } else {
                System.out.println("No exite el cliente");
                Factory.close(con);
                return "No exite el cliente";
            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
        }
        System.out.println("cambio de caja");
        return "";
    }

    public void obtenerProductos(PosVO pos, Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query = "SELECT * FROM v_buscar";

            rs = st.executeQuery(query);
            if (rs.next()) {
                pos.listaDefault = new ArrayList<ProductoDTO>();
                rs.previous();
                while (rs.next()) {
                    if (rs.getInt("cantidad") > 0) {

                        ProductoDTO p = new ProductoDTO();
                        p.setIdProducto(rs.getInt("id_producto"));
                        p.setDescripcion(rs.getString("descripcion"));
                        p.setCosto(rs.getInt("costo"));

                        p.setBarcode(rs.getString("barcode"));
                        p.setPrecioUno(rs.getDouble("precio_uno"));
                        p.setPrecioDos(rs.getDouble("precio_dos"));
                        p.setPrecioTres(rs.getDouble("precio_tres"));
                        p.setIva(rs.getDouble("iva"));

                        p.setCategoria(rs.getString("categoria"));
                        p.setDepartamento(rs.getString("departamento"));
                        p.setUnidadMedida(rs.getString("unidad_medida"));
                        p.setCorte(rs.getString("corte"));
                        p.setForro(rs.getString("forro"));
                        p.setSuela(rs.getString("suela"));
                        p.setDimAlto(rs.getString("dim_alto"));
                        p.setDimAncho(rs.getString("dim_ancho"));
                        p.setDimLargo(rs.getString("dim_largo"));
                        p.setStockMinimo(rs.getInt("stock_minimo"));
                        p.setStockMaximo(rs.getInt("stock_maximo"));
                        p.setCosto(rs.getDouble("costo"));
                        p.setCodigoProveedor(rs.getString("codigo_proveedor"));
                        p.setIdPromocion(rs.getInt("id_promocion"));
                        p.setIdProductoComplemento(rs.getInt("id_producto_complemento"));
                        p.setAplicaPromocionCantidad(rs.getInt("aplica_promocion_cantidad"));
                        p.setDescuentoPromocion(rs.getInt("descuento_promocion"));
                        pos.listaDefault.add(p);
                    }
                }
                rs.close();
                st.close();
            } else {
                System.out.println("No exite el producto");
            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            //pos.listaProductos = pos.listaDefault;
        }
    }

    public void ventasSinCerrar(PosVO pos, Connection con) {
        //Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        try {
            Statement st = con.createStatement();
            System.out.println("entro");
            ResultSet rs;
            String query = "SELECT * FROM v_venta_en_espera";

            rs = st.executeQuery(query);
            if (rs.next()) {
                pos.ventasEspera = new ArrayList<VentaDTO>();
                rs.previous();
                while (rs.next()) {
                    VentaDTO v = new VentaDTO();
                    v.setIdVenta(rs.getInt("id_venta"));
                    v.setIdAlmacen(rs.getInt("id_almacen"));
                    v.setIdTienda(rs.getInt("id_tienda"));
                    v.setIdUsuario(pos.venta.getIdUsuario());
                    pos.ventasEspera.add(v);
                }
                rs.close();
                st.close();
            } else {
                System.out.println("No exite venta");
                pos.ventasEspera.clear();
            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAccesos: " + ex.getMessage());
        } finally {
            Factory.close(con);
        }
    }

    public void prodEnEspera(PosVO pos, Connection con, VentaDTO venta) {
        String query;
        try {
            PreparedStatement ps = null;
            ResultSet rs;
            query = "SELECT * FROM v_productos_en_espera WHERE id_venta = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, venta.getIdVenta());
            rs = ps.executeQuery();

            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    pos.setCampoBusqueda(rs.getString("barcode"));
                    buscar(pos);

                }
                //return p;
            } else {
                System.out.println("No se encontro barcode");
                //return p;
            }
            rs.close();
            ps.close();

        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }

    }

    public List<ProductoDTO> buscaProducto(String producto, Connection con) {
        String query;
        PreparedStatement ps = null;
        ResultSet rs;
        List<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
        try {
            query = "SELECT \n"
                    + "  id_promocion, id_producto_complemento, aplica_promocion_cantidad, descuento_promocion, inventario, id_producto, \n"
                    + "  barcode, marca, modelo, color, variante, corrida, descripcion, precio_uno, precio_dos, precio_tres, iva, \n"
                    + "  categoria, departamento, unidad_medida, corte, forro, suela, dim_alto, dim_ancho, dim_largo, stock_minimo, \n"
                    + "   stock_maximo, costo, codigo_proveedor \n"
                    + "FROM v_buscar \n"
                    + "WHERE LOWER(descripcion) LIKE LOWER(?) AND inventario > 0 ";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + producto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setMarca(rs.getString("marca"));
                p.setModelo(rs.getString("modelo"));
                p.setCosto(rs.getInt("costo"));
                p.setInventario(rs.getInt("inventario"));

                p.setBarcode(rs.getString("barcode"));
                p.setPrecioUno(rs.getDouble("precio_uno"));
                p.setPrecioDos(rs.getDouble("precio_dos"));
                p.setPrecioTres(rs.getDouble("precio_tres"));
                p.setIva(rs.getDouble("iva"));

                p.setCategoria(rs.getString("categoria"));
                p.setDepartamento(rs.getString("departamento"));
                p.setUnidadMedida(rs.getString("unidad_medida"));
                p.setCorte(rs.getString("corte"));
                p.setForro(rs.getString("forro"));
                p.setSuela(rs.getString("suela"));
                p.setDimAlto(rs.getString("dim_alto"));
                p.setDimAncho(rs.getString("dim_ancho"));
                p.setDimLargo(rs.getString("dim_largo"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
                p.setStockMaximo(rs.getInt("stock_maximo"));
                p.setCosto(rs.getDouble("costo"));
                p.setCodigoProveedor(rs.getString("codigo_proveedor"));
                p.setIdPromocion(rs.getInt("id_promocion"));
                p.setIdProductoComplemento(rs.getInt("id_producto_complemento"));
                p.setAplicaPromocionCantidad(rs.getInt("aplica_promocion_cantidad"));
                p.setDescuentoPromocion(rs.getInt("descuento_promocion"));
                listaProductos.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.buscaProducto: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }
        return listaProductos;
    }

    public List<ProductoDTO> buscaProducto(int producto, Connection con) {
        String query;
        PreparedStatement ps = null;
        ResultSet rs;
        List<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
        try {
            query = "SELECT \n"
                    + "  id_promocion, id_producto_complemento, aplica_promocion_cantidad, descuento_promocion, inventario, id_producto, \n"
                    + "  barcode, marca, modelo, color, variante, corrida, descripcion, precio_uno, precio_dos, precio_tres, iva, \n"
                    + "  categoria, departamento, unidad_medida, corte, forro, suela, dim_alto, dim_ancho, dim_largo, stock_minimo, \n"
                    + "  stock_maximo, costo, codigo_proveedor \n"
                    + "FROM v_buscar \n"
                    + "WHERE barcode = ? AND inventario > 0 ";
            ps = con.prepareStatement(query);
            ps.setInt(1, producto);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoDTO p = new ProductoDTO();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setMarca(rs.getString("marca"));
                p.setModelo(rs.getString("modelo"));
                p.setCosto(rs.getInt("costo"));
                p.setInventario(rs.getInt("inventario"));

                p.setBarcode(rs.getString("barcode"));
                p.setPrecioUno(rs.getDouble("precio_uno"));
                p.setPrecioDos(rs.getDouble("precio_dos"));
                p.setPrecioTres(rs.getDouble("precio_tres"));
                p.setIva(rs.getDouble("iva"));

                p.setCategoria(rs.getString("categoria"));
                p.setDepartamento(rs.getString("departamento"));
                p.setUnidadMedida(rs.getString("unidad_medida"));
                p.setCorte(rs.getString("corte"));
                p.setForro(rs.getString("forro"));
                p.setSuela(rs.getString("suela"));
                p.setDimAlto(rs.getString("dim_alto"));
                p.setDimAncho(rs.getString("dim_ancho"));
                p.setDimLargo(rs.getString("dim_largo"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
                p.setStockMaximo(rs.getInt("stock_maximo"));
                p.setCosto(rs.getDouble("costo"));
                p.setCodigoProveedor(rs.getString("codigo_proveedor"));
                p.setIdPromocion(rs.getInt("id_promocion"));
                p.setIdProductoComplemento(rs.getInt("id_producto_complemento"));
                p.setAplicaPromocionCantidad(rs.getInt("aplica_promocion_cantidad"));
                p.setDescuentoPromocion(rs.getInt("descuento_promocion"));
                listaProductos.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.buscaProducto: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }
        return listaProductos;
    }

    public List<SelectItem> getListaDocumentos(Connection con) {
        List<SelectItem> docs = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query
                    = "SELECT id_tipo_documento, tipo_elemento, valor_elemento \n"
                    + "FROM catalogo_documento ";
            rs = st.executeQuery(query);
            while (rs.next()) {
                docs.add(new SelectItem(rs.getInt("id_tipo_documento"), rs.getString("valor_elemento")));
            }
            rs.close();
            st.close();
        } catch (Exception ex) {
            System.out.println("Error en PosDAO.getListaDocumentos: " + ex.getMessage());
        } finally {
            return docs;
        }
    }

    public List<SelectItem> getListaClientes(Connection con) {
        List<SelectItem> clientes = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query
                    = "SELECT \n"
                    + "  id_cliente, nombre, a_paterno, a_materno \n"
                    + "FROM cliente \n"
                    + "ORDER BY predeterminado DESC ";
            rs = st.executeQuery(query);
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidoPaterno(rs.getString("a_paterno"));
                c.setApellidoMaterno(rs.getString("a_materno"));
                clientes.add(new SelectItem(c.getIdCliente(), c.getNombre()));
            }
            rs.close();
            st.close();
        } catch (Exception ex) {
            System.out.println("Error en PosDAO.getListaClientesDAO: " + ex.getMessage());
        } finally {
            return clientes;
        }
    }
}
