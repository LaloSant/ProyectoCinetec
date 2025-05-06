/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

import java.time.LocalDate;

/**
 *
 * @author eduar
 */
public class Funcion {

    private String idFuncion;
    private String idPelicula;
    private String idSala;
    private String idCine;
    private LocalDate horario;
//    String fecha; Hacerlo como getter
//    String hora;

    public Funcion() {
    }

    public Funcion(String idFuncion, String idPelicula, String idSala, String idCine, LocalDate horario) {
        this.idFuncion = idFuncion;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idCine = idCine;
        this.horario = horario;
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
     * @return the idPelicula
     */
    public String getIdPelicula() {
        return idPelicula;
    }

    /**
     * @param idPelicula the idPelicula to set
     */
    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
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
     * @return the horario
     */
    public LocalDate getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(LocalDate horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcion{");
        sb.append("\nidFuncion=").append(idFuncion);
        sb.append(", \nidPelicula=").append(idPelicula);
        sb.append(", \nidSala=").append(idSala);
        sb.append(", \nidCine=").append(idCine);
        sb.append(", \nhorario=").append(horario);
        sb.append('}');
        return sb.toString();
    }

}
