/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.dao;

/**
 *
 * @author Usuario1
 */
public class Terminal {
    
    private int id_terminal;
    private String nombre;
    private String descripcion;
    private int predeterminada;
    private int id_almacen;
    private int id_tienda;
    
    public Terminal(int id_terminal,String nombre,String descripcion,int predeterminada,int id_tienda,int id_almacen){
        this.id_terminal = id_terminal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.predeterminada = predeterminada;
        this.id_almacen = id_almacen;
        this.id_tienda = id_tienda; 
   }
    
    public Terminal(){
        
    }

    public int getId_terminal() {
        return id_terminal;
    }

    public void setId_terminal(int id_terminal) {
        this.id_terminal = id_terminal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPredeterminada() {
        return predeterminada;
    }

    public void setPredeterminada(int predeterminada) {
        this.predeterminada = predeterminada;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }
    
    
    
}
