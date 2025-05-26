/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import com.fundbd.cine.model.Cliente;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class VerClientesController implements Initializable {

    ArrayList<Cliente> clientes = new ArrayList<>();

    @FXML
    private MenuItem mnuSelCine;
    @FXML
    private MenuItem mnuVerCartelera;
    @FXML
    private MenuItem mnuAgregarCliente;
    @FXML
    private MenuItem mnuAgregarPelicula;
    @FXML
    private MenuItem mnItAcercaDe;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> idClienteColumna;
    @FXML
    private TableColumn<Cliente, String> nombresColumna;
    @FXML
    private TableColumn<Cliente, String> apPColumna;
    @FXML
    private TableColumn<Cliente, String> ApMColumna;
    @FXML
    private TableColumn<Cliente, String> correoColumna;
    @FXML
    private TableColumn<Cliente, String> telefonoColumna;
    @FXML
    private TableColumn<Cliente, String> fechaColumna;
    @FXML
    private TableColumn<Cliente, String> contraColumna;
    @FXML
    private TableColumn<Cliente, String> tarjetaColumna;
    @FXML
    private TableColumn<Cliente, String> nipColumna;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (TableColumn<Cliente, ?> column : tablaClientes.getColumns()) {
            column.setReorderable(false);
        }
        tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        leerDatos();
        crearColumnas();
    }

    private void leerDatos() {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllClientes());
        try {
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
            }
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
    }

    private void crearColumnas() {
        idClienteColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCliente()));
        nombresColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apPColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidoPaterno()));
        ApMColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidoMaterno()));
        correoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        telefonoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        fechaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaNacimiento().toString()));
        contraColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContrasenia()));
        tarjetaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumeroTarjeta())));
        nipColumna.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNip())));
        tablaClientes.setItems(FXCollections.observableArrayList(clientes));
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

}
