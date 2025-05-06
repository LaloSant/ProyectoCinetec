/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.model;

/**
 *
 * @author eduar
 */
public class Queries {

    private Queries() {
    }

    public static String selectCines(String idCine) {
        return String.format("SELECT * FROM CINES WHERE id_cine = %s", idCine);
    }
    
    //...
}
