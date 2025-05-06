/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Compra {

    private String idCompra;
    private String idCliente;
    private double total;

    public Compra() {
    }

    public Compra(String idCompra, String idCliente, double total) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.total = total;
    }

    /**
     * @return the idCompra
     */
    public String getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra{");
        sb.append("\nidCompra=").append(idCompra);
        sb.append(", \nidCliente=").append(idCliente);
        sb.append(", \ntotal=").append(total);
        sb.append('}');
        return sb.toString();
    }

}
