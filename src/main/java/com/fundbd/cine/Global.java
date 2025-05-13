/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine;

import com.fundbd.cine.controller.ConexionSQL;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author eduar
 */
public class Global {

    private static ConexionSQL conSql = new ConexionSQL();
    private static HashMap<String, String> cines;
    private static int cineActual = -1;
    private static String idFuncionActual;

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
     * @return the cines
     */
    public static HashMap<String, String> getCines() {
        return cines;
    }

    /**
     * @param aCines the cines to set
     */
    public static void setCines(HashMap<String, String> aCines) {
        cines = aCines;
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

    /**
     * @return the idFuncionActual
     */
    public static String getIdFuncionActual() {
        return idFuncionActual;
    }

    /**
     * @param aIdFuncionActual the aIdFuncionActual to set
     */
    public static void setIdFuncionActual(String aIdFuncionActual) {
        idFuncionActual = aIdFuncionActual;
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
    
    public static void mostrarInfo(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.CLOSE);
        alerta.show();
    }

}
