/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author eduar
 */
public class AnPeliculaController implements Initializable {


    @FXML
    private MenuItem mnuCines;
    @FXML
    private MenuItem mnuCartelera;
    @FXML
    private MenuItem mnItAcercaDe;
    @FXML
    private ComboBox<String> cbBoxCines;
    @FXML
    private TextField txtIdPelicula;
    @FXML
    private TextField txtNomPelicula;
    @FXML
    private TextField txtDuracion;
    @FXML
    private TextField txtImagen;
    @FXML
    private TextField txtTrailer;
    @FXML
    private TextField txtIdioma;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextArea txtSinopsis;
    @FXML
    private Button btnSelImg;
    @FXML
    private Button btnSelTrailer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
    }    
    
    @FXML
    private void mnuCinesOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
        Global.mostrarMenuCreditos();
    }

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    @FXML
    private void btnSelImgOnAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione imagen");
        fc.setInitialDirectory(new File("/"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.jpg"));
        File selectedDirectory = fc.showOpenDialog(App.stage);
        if (selectedDirectory != null) {
            System.out.println("Selected directory: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No directory selected.");
        }
        txtImagen.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    private void btnSelTrailerOnAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione imagen");
        fc.setInitialDirectory(new File("/"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.jpg"));
        File selectedDirectory = fc.showOpenDialog(App.stage);
        if (selectedDirectory != null) {
            System.out.println("Selected directory: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No directory selected.");
        }
        txtImagen.setText(selectedDirectory.getAbsolutePath());
    }

}
