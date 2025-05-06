/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Cine {
    private String idCine;
    private String nombre;
    private String localidad;

    public Cine() {
    }

    public Cine(String idCine, String nombre, String localidad) {
        this.idCine = idCine;
        this.nombre = nombre;
        this.localidad = localidad;
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
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cine{");
        sb.append("\nidCine=").append(idCine);
        sb.append(", \nnombre=").append(nombre);
        sb.append(", \nlocalidad=").append(localidad);
        sb.append('}');
        return sb.toString();
    }
    
    
}
