/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine;

import com.fundbd.cine.controller.ConexionSQL;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author eduar
 */
public class Global {

    private static ConexionSQL conSql = new ConexionSQL();
    private static ArrayList<String> cinesRegistrados;
    private static int cineActual = -1;

    public Global() {

    }

    /**
     * @return the conSql
     */
    public static ConexionSQL getConSql() {
        return conSql;
    }

    /**
     * @param aConSql the conSql to set
     */
    public static void setConSql(ConexionSQL aConSql) {
        conSql = aConSql;
    }

    /**
     * @return the cinesRegistrados
     */
    public static ArrayList<String> getCinesRegistrados() {
        return cinesRegistrados;
    }

    /**
     * @param aCinesRegistrados the cinesRegistrados to set
     */
    public static void setCinesRegistrados(ArrayList<String> aCinesRegistrados) {
        cinesRegistrados = aCinesRegistrados;
    }

    /**
     * @return the cineActual
     */
    public static int getCineActual() {
        return cineActual;
    }

    /**
     * @param aCineActual the cineActual to set
     */
    public static void setCineActual(int aCineActual) {
        cineActual = aCineActual;
    }

    public static void mostrarMenuCreditos() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                "Creado por: "
                + "\nAlan Daniel Farfan Gomez"
                + "\nMiguel Angel Torres Diaz"
                + "\nErik Carbajal Sanchez"
                + "\nEduardo Jair Bautista Santiesteban"
                + "\nYael Sampayo Marin",
                ButtonType.CLOSE);
        alerta.show();
    }

    public static void mostrarAlertaError(String msg) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, msg, ButtonType.CLOSE);
        alerta.show();
    }

}
