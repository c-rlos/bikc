/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.delegate;

import java.sql.Connection;
import java.util.List;
import javax.faces.model.SelectItem;
import mx.com.kubez.bikc.dao.PosDAO;
import mx.com.kubez.bikc.dto.ClienteDTO;
import mx.com.kubez.bikc.dto.ProductoDTO;

/**
 *
 * @author carlos.juarez
 */
public class PosDelegate {
    
    private PosDAO posDao;

    public PosDelegate() {
        posDao = new PosDAO();    
    }
    
    public List<ProductoDTO> buscaProducto(String producto, Connection con){
        try {
            int codigo = Integer.valueOf(producto);
            return posDao.buscaProducto(codigo, con);
        } catch (Exception e) {
            return posDao.buscaProducto(producto, con);
        }
    }

    public List<SelectItem> getListaDocumentos(Connection con) {
        return posDao.getListaDocumentos(con);
    }
    
    public List<SelectItem> getListaClientes(Connection con) {
        return posDao.getListaClientes(con);
    }
    
}
