/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.dao;

import mx.com.kubez.bikc.dto.ClienteDTO;
import mx.com.kubez.bikc.dto.VentaDTO;
import mx.com.kubez.bikc.dto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Usuario1
 */
public class ElementosPos {

    
    public static String cliente;
    public static String terminal;
    public List<ClienteDTO> listaClientes;
    public List<Terminal> listaTerminales;
    public List<SelectItem> vendedores;
    public List<SelectItem> terminales;
    public static List<ProductoDTO> listaVenta = new ArrayList<ProductoDTO>();
    public List<ProductoDTO> listaProductos;
    public static List<VentaDTO> ventasEspera;
    
    public ClienteDTO selectedClient;

    
    public static VentaDTO selectEspera;

    
    public String campoBusqueda2;
    public String busquedaClientes;

    public String campoFiltro;

    public int campoCantidad;

    public String selectedPrecio;

    public static double campoPreVenta;
    public static double campoPreMay1;
    public static double campoPreMay2;

    public double campoTotal;
    public double campoRecibido;
    public double campoCambio;

    

   

    public String username;
    public String password;
    public String errorUsuario;

    
    

    public List<SelectItem> getTerminales() {
        return terminales;
    }

    public void setTerminales(List<SelectItem> terminales) {
        this.terminales = terminales;
    }

    public List<SelectItem> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<SelectItem> vendedores) {
        this.vendedores = vendedores;
    }

    public List<ClienteDTO> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<ClienteDTO> listaClientes) {
        this.listaClientes = listaClientes;
    }

   

    /**
     * Get the value of campoFiltro
     *
     * @return the value of campoFiltro
     */
    public String getCampoFiltro() {
        return campoFiltro;
    }

    /**
     * Set the value of campoFiltro
     *
     * @param campoFiltro new value of campoFiltro
     */
    public void setCampoFiltro(String campoFiltro) {
        this.campoFiltro = campoFiltro;
    }

    public String getCampoBusqueda2() {
        return campoBusqueda2;
    }

    public void setCampoBusqueda2(String campoBusqueda2) {
        this.campoBusqueda2 = campoBusqueda2;
    }

    public ClienteDTO getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(ClienteDTO selectedClient) {
        this.selectedClient = selectedClient;
    }

    public String getBusquedaClientes() {
        return busquedaClientes;
    }

    public void setBusquedaClientes(String busquedaClientes) {
        this.busquedaClientes = busquedaClientes;
    }

  
    /**
     * Get the value of campoCantidad
     *
     * @return the value of campoCantidad
     */
    public int getCampoCantidad() {
        return campoCantidad;
    }

    /**
     * Set the value of campoCantidad
     *
     * @param campoCantidad new value of campoCantidad
     */
    public void setCampoCantidad(int campoCantidad) {
        this.campoCantidad = campoCantidad;
    }

    /**
     * Get the value of campoPreMay2
     *
     * @return the value of campoPreMay2
     */
    public double getCampoPreMay2() {
        return campoPreMay2;
    }

    /**
     * Set the value of campoPreMay2
     *
     * @param campoPreMay2 new value of campoPreMay2
     */
    public void setCampoPreMay2(double campoPreMay2) {
        this.campoPreMay2 = campoPreMay2;
    }

    /**
     * Get the value of campoPreMay1
     *
     * @return the value of campoPreMay1
     */
    public double getCampoPreMay1() {
        return campoPreMay1;
    }

    /**
     * Set the value of campoPreMay1
     *
     * @param campoPreMay1 new value of campoPreMay1
     */
    public void setCampoPreMay1(double campoPreMay1) {
        this.campoPreMay1 = campoPreMay1;
    }

    /**
     * Get the value of campoPreVenta
     *
     * @return the value of campoPreVenta
     */
    public double getCampoPreVenta() {
        return campoPreVenta;
    }

    /**
     * Set the value of campoPreVenta
     *
     * @param campoPreVenta new value of campoPreVenta
     */
    public void setCampoPreVenta(double campoPreVenta) {
        this.campoPreVenta = campoPreVenta;
    }

    /**
     * Get the value of selectedPrecio
     *
     * @return the value of selectedPrecio
     */
    public String getSelectedPrecio() {
        System.out.println("selected precio - get" + selectedPrecio + " -");
        return selectedPrecio;
    }

    /**
     * Set the value of selectedPrecio
     *
     * @param selectedPrecio new value of selectedPrecio
     */
    public void setSelectedPrecio(String selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    /**
     * Get the value of campoCambio
     *
     * @return the value of campoCambio
     */
    public double getCampoCambio() {
        return campoCambio;
    }

    /**
     * Set the value of campoCambio
     *
     * @param campoCambio new value of campoCambio
     */
    public void setCampoCambio(double campoCambio) {
        this.campoCambio = campoCambio;
    }

    /**
     * Get the value of campoPorCobrar
     *
     * @return the value of campoPorCobrar
     */
    public double getCampoRecibido() {
        return campoRecibido;
    }

    /**
     * Set the value of campoPorCobrar
     *
     * @param campoPorCobrar new value of campoPorCobrar
     */
    public void setCampoRecibido(double campoPorCobrar) {
        this.campoRecibido = campoPorCobrar;
    }

    /**
     * Get the value of campoTotal
     *
     * @return the value of campoTotal
     */
    public double getCampoTotal() {
        return campoTotal;
    }

    /**
     * Set the value of campoTotal
     *
     * @param campoTotal new value of campoTotal
     */
    public void setCampoTotal(double campoTotal) {
        this.campoTotal = campoTotal;
    }

 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VentaDTO> getVentasEspera() {
        return ventasEspera;
    }

    public void setVentasEspera(List<VentaDTO> ventasEspera) {
        System.out.println("set espera");
        this.ventasEspera = ventasEspera;
    }

    public VentaDTO getSelectEspera() {
        System.out.println("get espera");
        return selectEspera;
    }

    public void setSelectEspera(VentaDTO selectEspera) {
        this.selectEspera = selectEspera;
    }

    public String getErrorUsuario() {
        return errorUsuario;
    }

    public void setErrorUsuario(String errorUsuario) {
        this.errorUsuario = errorUsuario;
    }

    

  

}
