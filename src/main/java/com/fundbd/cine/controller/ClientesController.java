/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import com.fundbd.cine.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yael95
 */
public class ClientesController implements Initializable {

    ArrayList<Cliente> clientes = new ArrayList<>();

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
    @FXML
    private ComboBox<String> cbBoxClientes;
    @FXML
    private Button btnModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarClientes();
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
    }

    private void cargarClientes() throws SQLException {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllClientes());
        while (rs.next()) {
            String idCliente = rs.getString(1);
            String nombre = rs.getString(2);
            String apPat = rs.getString(3);
            String apMat = rs.getString(4);
            String correo = rs.getString(5);
            long telefono = rs.getLong(6);
            String fechaNac = rs.getString(7);
            String contrasenia = rs.getString(8);
            long numTarjeta = rs.getLong(9);
            int nip = rs.getInt(10);
            String dateString = fechaNac.split(" ")[0];
            LocalDate date = LocalDate.parse(dateString);
            Cliente cli = new Cliente(idCliente, nombre, apPat, apMat, correo, telefono, date, contrasenia, numTarjeta, nip);
            clientes.add(cli);
            cbBoxClientes.getItems().add(rs.getString("id_cliente") + ".- " + rs.getString("nombre"));
        }
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
            String numTel = txtTelefono.getText();
            String correo = txtCorreo.getText().trim();
            String numTarjeta = txtTarjeta.getText();
            String nip = txtNip.getText();
            Global.getConSql().insertarCliente(id, nombre, apellidoP, apellidoM, contrasenia, fechaNac, numTel, correo, numTarjeta, nip);
            Global.mostrarInfo("Se inserto al cliente!");
        } catch (IOException | NumberFormatException | SQLException e) {
            Global.mostrarAlertaError(e.getMessage());
        }
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void mnuSelCineOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void mnuVerCarteleraOnAction(ActionEvent event) {
        Global.mostrarAlertaError("Como llegaste aqui?");
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

    @FXML
    private void cbBoxClientesOnAction(ActionEvent event) {
        Cliente temp = clientes.get(cbBoxClientes.getSelectionModel().getSelectedIndex());
        txtId.setText(temp.getIdCliente());
        txtNombre.setText(temp.getNombre());
        txtApellidoP.setText(temp.getApellidoPaterno());
        txtApellidoM.setText(temp.getApellidoMaterno());
        txtCorreo.setText(temp.getCorreo());
        txtTelefono.setText(String.valueOf(temp.getTelefono()));
        txtContrasenia.setText(temp.getContrasenia());
        txtTarjeta.setText(String.valueOf(temp.getNumeroTarjeta()));
        txtNip.setText(String.valueOf(temp.getNip()));
        dtpFecha.setPromptText(temp.getFechaNacimiento().toString() + " (Dato de referencia solamente)");
    }

    @FXML
    private void btnModificarOnAction(ActionEvent event) {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellidoP = txtApellidoP.getText().trim();
            String apellidoM = txtApellidoM.getText().trim();
            String contrasenia = txtContrasenia.getText().trim();
            Date fechaNac = (dtpFecha.getValue() != null) ? java.sql.Date.valueOf(dtpFecha.getValue()) : null;
            String numTel = txtTelefono.getText();
            String correo = txtCorreo.getText().trim();
            String numTarjeta = txtTarjeta.getText();
            String nip = txtNip.getText();
            Global.getConSql().updateCliente(id, nombre, apellidoP, apellidoM, contrasenia, fechaNac, numTel, correo, numTarjeta, nip);
            Global.mostrarInfo("Se modifico al cliente!");
        } catch (IOException | NumberFormatException | SQLException e) {
            Global.mostrarAlertaError(e.getMessage());
        }
    }

}
