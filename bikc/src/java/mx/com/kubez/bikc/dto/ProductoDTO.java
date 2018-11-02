/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.dto;

import java.util.List;

/**
 *
 * @author Usuario1
 */
public class ProductoDTO {

    private int idProducto;
    private String barcode;
    private int inventario;
    private int cantidad;
    private String marca;
    private String modelo;
    private String variante;
    private String corrida;
    private String descripcion;
    
    private double precioUno;
    private double precioDos;
    private double precioTres;
    
    private double iva;
    
    private String categoria;
    private String departamento;
    private String unidadMedida;
    private String corte;
    private String forro;
    private String suela;
    private String dimAlto;
    private String dimAncho;
    private String dimLargo;
    private int stockMinimo;
    private int stockMaximo;
    public double costo;
    private String codigoProveedor;
    
    private int idPromocion;
    private int idProductoComplemento;
    private int aplicaPromocionCantidad;
    private double descuentoPromocion;
    private int estatus;    
    
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVariante() {
        return variante;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public String getCorrida() {
        return corrida;
    }

    public void setCorrida(String corrida) {
        this.corrida = corrida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUno() {
        return precioUno;
    }

    public void setPrecioUno(double precioUno) {
        this.precioUno = precioUno;
    }

    public double getPrecioDos() {
        return precioDos;
    }

    public void setPrecioDos(double precioDos) {
        this.precioDos = precioDos;
    }

    public double getPrecioTres() {
        return precioTres;
    }

    public void setPrecioTres(double precioTres) {
        this.precioTres = precioTres;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getForro() {
        return forro;
    }

    public void setForro(String forro) {
        this.forro = forro;
    }

    public String getSuela() {
        return suela;
    }

    public void setSuela(String suela) {
        this.suela = suela;
    }

    public String getDimAlto() {
        return dimAlto;
    }

    public void setDimAlto(String dimAlto) {
        this.dimAlto = dimAlto;
    }

    public String getDimAncho() {
        return dimAncho;
    }

    public void setDimAncho(String dimAncho) {
        this.dimAncho = dimAncho;
    }

    public String getDimLargo() {
        return dimLargo;
    }

    public void setDimLargo(String dimLargo) {
        this.dimLargo = dimLargo;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getIdProductoComplemento() {
        return idProductoComplemento;
    }

    public void setIdProductoComplemento(int idProductoComplemento) {
        this.idProductoComplemento = idProductoComplemento;
    }

    public int getAplicaPromocionCantidad() {
        return aplicaPromocionCantidad;
    }

    public void setAplicaPromocionCantidad(int aplicaPromocionCantidad) {
        this.aplicaPromocionCantidad = aplicaPromocionCantidad;
    }

    public double getDescuentoPromocion() {
        return descuentoPromocion;
    }

    public void setDescuentoPromocion(double descuentoPromocion) {
        this.descuentoPromocion = descuentoPromocion;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }


    
    
}
