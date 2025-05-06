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
public class Cliente {

    private String idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private long telefono;
    private LocalDate fechaNacimiento; //Representa anio, mes, dia
    private String contrasenia;
    private long numeroTarjeta;
    private byte nip;

    public Cliente() {
    }

    public Cliente(String idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, long telefono, LocalDate fechaNacimiento, String contrasenia, long numeroTarjeta, byte nip) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasenia = contrasenia;
        this.numeroTarjeta = numeroTarjeta;
        this.nip = nip;
    }

    /**
     * @return the id_cliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the numeroTarjeta
     */
    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the nip
     */
    public byte getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(byte nip) {
        this.nip = nip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("\nidCliente=").append(idCliente);
        sb.append(",\nnombre=").append(nombre);
        sb.append(", \napellidoPaterno=").append(apellidoPaterno);
        sb.append(", \napellidoMaterno=").append(apellidoMaterno);
        sb.append(", \ncorreo=").append(correo);
        sb.append(", \ntelefono=").append(telefono);
        sb.append(", \nfechaNacimiento=").append(fechaNacimiento);
        sb.append(", \ncontrasenia=").append(contrasenia);
        sb.append(", \nnumeroTarjeta=").append(numeroTarjeta);
        sb.append(", \nnip=").append(nip);
        sb.append('}');
        return sb.toString();
    }

}
