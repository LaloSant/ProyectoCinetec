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
    private MenuItem mnuCines;
    @FXML
    private MenuItem mnuCartelera;
    @FXML
    private MenuItem mnItAcercaDe;
    @FXML
    private ComboBox<?> cbBoxCines;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnComprar;
    @FXML
    private TextArea txtMostrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    

    @FXML
    private void mnuCinesOnAction(ActionEvent event)
    {
    }

    @FXML
    private void mnuCarteleraOnAction(ActionEvent event)
    {
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event)
    {
    }
    
    @FXML
    private void cbBoxCinesOnAction(ActionEvent event)
    {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    @FXML
    private void cbColumnas(ActionEvent event)
    {
    }

    @FXML
    private void cbFilas(ActionEvent event)
    {
    }
    
}
