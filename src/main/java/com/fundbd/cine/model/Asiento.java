/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Asiento {

    private String idAsiento;
    private String idFuncion;
    private boolean disponible;
    private char fila;
    private String columna;

    public Asiento() {
    }

    public Asiento(String idAsiento, String idFuncion, boolean disponible, char fila, String columna) {
        this.idAsiento = idAsiento;
        this.idFuncion = idFuncion;
        this.disponible = disponible;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * @return the idAsiento
     */
    public String getIdAsiento() {
        return idAsiento;
    }

    /**
     * @param idAsiento the idAsiento to set
     */
    public void setIdAsiento(String idAsiento) {
        this.idAsiento = idAsiento;
    }

    /**
     * @return the idFuncion
     */
    public String getIdFuncion() {
        return idFuncion;
    }

    /**
     * @param idFuncion the idFuncion to set
     */
    public void setIdFuncion(String idFuncion) {
        this.idFuncion = idFuncion;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the fila
     */
    public char getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(char fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public String getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(String columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asiento{");
        sb.append("\nidAsiento=").append(idAsiento);
        sb.append(", \nidFuncion=").append(idFuncion);
        sb.append(", \ndisponible=").append(disponible);
        sb.append(", \nfila=").append(fila);
        sb.append(", \ncolumna=").append(columna);
        sb.append('}');
        return sb.toString();
    }

}
