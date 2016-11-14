/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anthony
 */
public class ModelProductos {
    private String nombre;
    private String descripcion;
    private int existencias;
    private int compra;
    private int venta;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    /**
     * @return the compra
     */
    public int getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(int compra) {
        this.compra = compra;
    }

    /**
     * @return the venta
     */
    public int getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(int venta) {
        this.venta = venta;
    }
    
    
}
