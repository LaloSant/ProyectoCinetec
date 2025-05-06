/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Sala {
    private String idSala;
    private String idCine;
    private String nombre;
    private String tipo;
    private int precio;
    private int filas;
    private int columnas;

    public Sala() {
    }

    public Sala(String idSala, String idCine, String nombre, String tipo, int precio, int filas, int columnas) {
        this.idSala = idSala;
        this.idCine = idCine;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.filas = filas;
        this.columnas = columnas;
    }

    /**
     * @return the idSala
     */
    public String getIdSala() {
        return idSala;
    }

    /**
     * @param idSala the idSala to set
     */
    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    /**
     * @return the idCine
     */
    public String getIdCine() {
        return idCine;
    }

    /**
     * @param idCine the idCine to set
     */
    public void setIdCine(String idCine) {
        this.idCine = idCine;
    }

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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(int filas) {
        this.filas = filas;
    }

    /**
     * @return the columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sala{");
        sb.append("\nidSala=").append(idSala);
        sb.append(", \nidCine=").append(idCine);
        sb.append(", \nnombre=").append(nombre);
        sb.append(", \ntipo=").append(tipo);
        sb.append(", \nprecio=").append(precio);
        sb.append(", \nfilas=").append(filas);
        sb.append(", \ncolumnas=").append(columnas);
        sb.append('}');
        return sb.toString();
    }

    
    
}
