/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * FXML Controller class
 *
 * @author yael95
 */
public class ClientesController implements Initializable {


    @FXML
    private MenuItem mnuCines;
    @FXML
    private MenuItem mnuCartelera;
    @FXML
    private MenuItem mnItAcercaDe;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoP;
    @FXML
    private TextField txtApellidoM;
    @FXML
    private TextField txtContrasenia;
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTarjeta;
    @FXML
    private TextField txtNip;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void mnuCinesOnAction(ActionEvent event) {
    }

    @FXML
    private void mnuCarteleraOnAction(ActionEvent event) {
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAceptarOnAction(ActionEvent event) {
        
        
        
    }


}
