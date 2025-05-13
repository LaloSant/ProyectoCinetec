/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class HomeController implements Initializable {

    @FXML
    private ImageView imgViewLogo;
    @FXML
    private ComboBox<String> cbBoxCines;
    @FXML
    private MenuItem mnItAcercaDe;
    @FXML
    private Button btnVerFunciones;
    @FXML
    private MenuItem mnuSelCine;
    @FXML
    private MenuItem mnuVerCartelera;
    @FXML
    private MenuItem mnuAgregarCliente;
    @FXML
    private MenuItem mnuAgregarPelicula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllCines());
        HashMap<String, String> cines = new HashMap<>();
        try {
            while (rs.next()) {
                cines.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }

        Global.setCines(cines);
        ObservableList<String> datos = FXCollections.observableArrayList(new ArrayList(cines.values()));
        cbBoxCines.setItems(datos);
    }

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
        btnVerFunciones.setDisable(false);
    }

    @FXML
    private void btnFuncionesOnAction(ActionEvent event) {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
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
