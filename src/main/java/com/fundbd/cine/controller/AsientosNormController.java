/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class AsientosNormController implements Initializable
{

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
    private TextArea areaTexto;
    @FXML
    private Button btnVerCartelera;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
        for (char letra = 'A'; letra <= 'J'; letra++)
        {
            cbFilas.getItems().add("Fila " + letra);
        }

        for (int i = 1; i <= 20; i++)
        {
            cbColumnas.getItems().add("Columna " + i);
        }
    }

    @FXML
    private void cbColumnas(ActionEvent event)
    {

    }

    @FXML
    private void cbFilas(ActionEvent event)
    {
        
    }

    private void btnAgregar(ActionEvent event)
    {
        String fila = cbFilas.getValue();
        String columna = cbColumnas.getValue();

        if (fila != null && columna != null)
        {
            areaTexto.appendText(fila + " - " + columna);
        } else
        {
            areaTexto.appendText("Selecciona una fila y una columna");
        }
    }


    @FXML
    private void cbBoxCinesOnAction(ActionEvent event)
    {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuSelCineOnAction(ActionEvent event)
    {
        App.cambiarAHome();
    }

    @FXML
    private void mnuVerCarteleraOnAction(ActionEvent event)
    {
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuAgregarClienteOnAction(ActionEvent event)
    {
        App.cambiarVista("clientes");
    }

    @FXML
    private void mnuAgregarPeliculaOnAction(ActionEvent event)
    {
        App.cambiarVista("anPelicula");
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event)
    {
        Global.mostrarMenuCreditos();
    }
}
