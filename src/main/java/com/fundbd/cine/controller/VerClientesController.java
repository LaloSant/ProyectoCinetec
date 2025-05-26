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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (TableColumn<? extends Object, ?> column : tablaClientes.getColumns()) {
            column.setReorderable(false);
        }
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
                byte nip = rs.getByte(10);
                System.out.println("Aqui no llego");
                LocalDate ld = LocalDate.parse(fechaNac);
                Cliente cli = new Cliente(idCliente, nombre, apPat, apMat, correo, telefono, ld, contrasenia, numTarjeta, nip);
                clientes.add(cli);
            }
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
    }
    
    private void crearColumnas(){
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
