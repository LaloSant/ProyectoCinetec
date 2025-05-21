/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import com.fundbd.cine.model.Asiento;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author eriks
 */
public class AsientosNormController implements Initializable {
    
    
    private static final int MAXBOLETOS = 10;
    private static final ArrayList<Asiento> seleccionados = new ArrayList<>();
    private static final ArrayList<ArrayList<Asiento>> ASIENTOS = new ArrayList<>();

    @FXML
    private ComboBox<String> cbBoxCines;
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
    @FXML
    private ComboBox<String> cbFilas;
    @FXML
    private ComboBox<String> cbColumnas;
    @FXML
    private ComboBox<String> cbBoxClientes;
    @FXML
    private CheckBox chkNinioAdulto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
        try {
            cargarClientes();
            cargarAsientos();
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
        String tipoSala = Global.getTipoSalaActual();
        if (tipoSala.equalsIgnoreCase("VIP")) {
            cbFilas.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E"));
            cbColumnas.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));
        } else {
            cbFilas.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
            cbColumnas.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
        }
        
        cbFilas.setDisable(true);
        cbColumnas.setDisable(true);
        btnAgregar.setDisable(true);
    }

    private void cargarClientes() throws SQLException {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllClientes());
        while (rs.next()) {
            cbBoxClientes.getItems().add(rs.getString("id_cliente") + ".- " + rs.getString("nombre"));
        }
    }

    private void cargarAsientos() throws SQLException {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAsientos(Global.getIdFuncionActual()));
        char anterior = 'A';
        ArrayList<Asiento> temp = new ArrayList<>();
        while (rs.next()) {
            String idAsientos = rs.getString(1);
            String idFuncion = rs.getString(2);
            boolean disponible = rs.getString(3).trim().equalsIgnoreCase("true");
            char fila = rs.getString(4).charAt(0);
            char columna = rs.getString(5).charAt(0);
            if (anterior != fila) {
                ASIENTOS.add(temp);
                temp = new ArrayList<>();
                anterior = fila;
            }
            temp.add(new Asiento(idAsientos, idFuncion, disponible, fila, columna));
        }
    }

    @FXML
    private void cbColumnas(ActionEvent event) {

    }

    @FXML
    private void cbFilas(ActionEvent event) {

    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        if (seleccionados.size() >= MAXBOLETOS) {
            Global.mostrarAlertaError("Solo puedes agregar 10 boletos.");
            return;
        }
        if (cbFilas.getValue() == null && cbColumnas.getValue() == null) {
            Global.mostrarInfo("SELECCIONE UN ASIENTO");
            return;
        }
        Asiento temp = ASIENTOS.get(cbFilas.getSelectionModel().getSelectedIndex()).get(cbColumnas.getSelectionModel().getSelectedIndex());
        if (!temp.isDisponible()) {
            Global.mostrarAlertaError("El asiento ya esta ocupado.");
            return;
        }
        temp.setDisponible(false);
        txtMostrar.appendText(String.format("Fila: %s -- Columna: %s %n", temp.getFila(), temp.getColumna()));
        seleccionados.add(temp);
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

    @FXML
    private void cbBoxClientesOnAction(ActionEvent event) {
        cbFilas.setDisable(false);
        cbColumnas.setDisable(false);
        btnAgregar.setDisable(false);
    }

    @FXML
    private void btnComprarOnAction(ActionEvent event) {
    }
}
