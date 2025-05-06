/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> datos = FXCollections.observableArrayList("Cine 1", "Cine 2", "Cine 3");
        cbBoxCines.setItems(datos);
    }    

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
        btnVerFunciones.setDisable(false);
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, 
                "Creado por: "
                        + "\nAlan Daniel Farfan Gomez"
                        + "\nMiguel Angel Torres Diaz"
                        + "\nErik Carbajal Sanchez"
                        + "\nEduardo Jair Bautista Santiesteban"
                        + "\nYael Sampayo Marin"
                , ButtonType.CLOSE);
        alerta.show();
        
    }

    @FXML
    private void btnFuncionesOnAction(ActionEvent event) {
        App.cambiarVista("peliculas");
    }
    
}
