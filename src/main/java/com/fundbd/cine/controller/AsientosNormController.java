/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author eriks
 */
public class AsientosNormController implements Initializable {

    @FXML
    private ComboBox<?> cbBoxCines;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnComprar;
    @FXML
    private TextArea txtMostrar;
    @FXML
    private MenuItem mnuSelCine;
    @FXML
    private MenuItem mnuVerCartelera;
    @FXML
    private MenuItem mnuAgregarCliente;
    @FXML
    private MenuItem mnuAgregarPelicula;
    @FXML
    private MenuItem mnItAcercaDe1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cbColumnas(ActionEvent event) {
    }

    @FXML
    private void cbFilas(ActionEvent event) {
    }

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuSelCineOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void mnuVerCarteleraOnAction(ActionEvent event) {
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuAgregarClienteOnAction(ActionEvent event) {
        App.cambiarVista("clientes");
    }

    @FXML
    private void mnuAgregarPeliculaOnAction(ActionEvent event) {
        App.cambiarVista("anPelicula");
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
        Global.mostrarMenuCreditos();
    }
}
