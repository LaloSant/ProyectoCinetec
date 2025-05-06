/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Boleto {

    private String idBoleto;
    private String idAsiento;
    private String idCompra;

    public Boleto() {
    }

    public Boleto(String idBoleto, String idAsiento, String idCompra) {
        this.idBoleto = idBoleto;
        this.idAsiento = idAsiento;
        this.idCompra = idCompra;
    }

    /**
     * @return the idBoleto
     */
    public String getIdBoleto() {
        return idBoleto;
    }

    /**
     * @param idBoleto the idBoleto to set
     */
    public void setIdBoleto(String idBoleto) {
        this.idBoleto = idBoleto;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Boleto{");
        sb.append("\nidBoleto=").append(idBoleto);
        sb.append(", \nidAsiento=").append(idAsiento);
        sb.append(", \nidCompra=").append(idCompra);
        sb.append('}');
        return sb.toString();
    }

}
