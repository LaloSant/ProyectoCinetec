/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField txtId;

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
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellidoP = txtApellidoP.getText().trim();
            String apellidoM = txtApellidoM.getText().trim();
            String contrasenia = txtContrasenia.getText().trim();
            Date fechaNac = (dtpFecha.getValue() != null) ? java.sql.Date.valueOf(dtpFecha.getValue()) : null;
            Long numTel = (!txtTelefono.getText().isBlank())? Long.parseLong(txtTelefono.getText().trim()): null;
            String correo = txtCorreo.getText().trim();
            Long numTarjeta = (!txtTarjeta.getText().isBlank())? Long.parseLong(txtTarjeta.getText().trim()):null;
            Integer nip = (!txtNip.getText().isBlank()) ? Integer.parseInt(txtNip.getText().trim()):null;
            Global.getConSql().insertarCliente(id, nombre, apellidoP, apellidoM, contrasenia, fechaNac, numTel, correo, numTarjeta, nip);
            Global.mostrarInfo("Se inserto al cliente");
        } catch (IOException | NumberFormatException | SQLException e) {
            Global.mostrarAlertaError(e.getMessage());
        }

    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {

        App.cambiarAHome();
    }

}
