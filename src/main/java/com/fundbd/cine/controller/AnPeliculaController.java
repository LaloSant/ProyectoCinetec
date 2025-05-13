/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class AnPeliculaController implements Initializable {

    ArrayList<String> cines = new ArrayList<>();
    ArrayList<String> salas = new ArrayList<>();

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
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtClasificacion;
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
    private TextField txtIdFuncion;
    @FXML
    private ComboBox<String> cbIdSala;
    @FXML
    private ComboBox<String> cbIdCine;
    @FXML
    private DatePicker dpHorarioDia;
    @FXML
    private ComboBox<String> cbHora;
    @FXML
    private ComboBox<String> cbMinuto;
    @FXML
    private Button btnAgregarFuncion;
    @FXML
    private ComboBox<String> cbIdPelicula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> horas = FXCollections.observableArrayList("11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
        ObservableList<String> minutos = FXCollections.observableArrayList("00", "10", "20", "30", "40", "50", "60");
        cbHora.setItems(horas);
        cbMinuto.setItems(minutos);
        leerDatos();
    }

    public void leerDatos() {
        try {
            ResultSet rs = Global.getConSql().consulta(Queries.selectAllSalas());
            while (rs.next()) {
                salas.add(rs.getString(1));
                cines.add(rs.getString(2));
            }
            rs = Global.getConSql().consulta(Queries.selectAllPeliculas());
            ArrayList<String> peliculas = new ArrayList<>();
            while (rs.next()) {
                peliculas.add(rs.getString(1));
            }
            cbIdPelicula.setItems(FXCollections.observableArrayList(peliculas));
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
        cbIdSala.setItems(FXCollections.observableArrayList(salas));
        cbIdCine.setItems(FXCollections.observableArrayList(cines));
    }

    @FXML
    private void btnSelImgOnAction(ActionEvent event
    ) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione imagen");
        fc.setInitialDirectory(new File("/"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.jpg"));
        File selectedDirectory = fc.showOpenDialog(App.stage);
        if (selectedDirectory == null) {
            Global.mostrarAlertaError("En caso de no seleccionar imagen o trailer, se guardara como un BLOB vacio");
        } else {
            txtImagen.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void btnSelTrailerOnAction(ActionEvent event
    ) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione trailer");
        fc.setInitialDirectory(new File("/"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes", "*.mp4"));
        File selectedDirectory = fc.showOpenDialog(App.stage);
        if (selectedDirectory == null) {
            Global.mostrarAlertaError("En caso de no seleccionar imagen o trailer, se guardara como un BLOB vacio");
        } else {
            txtTrailer.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void btnAgregarOnAction(ActionEvent event
    ) {
        try {
            String rutaImagen = txtImagen.getText();
            String rutaVideo = txtTrailer.getText();
            File fileImagen = (rutaImagen.isBlank()) ? new File("src/main/resources/temp/img404.jpg") : new File(rutaImagen);
            File fileVideo = (rutaVideo.isBlank()) ? new File("src/main/resources/temp/video404.mp4") : new File(rutaVideo);
            String idPelicula = txtIdPelicula.getText();
            String nomPelicula = txtNomPelicula.getText();
            String sinopsis = txtSinopsis.getText();
            String duracion = txtDuracion.getText();
            String idioma = txtIdioma.getText();
            String clasificacion = txtClasificacion.getText();
            String genero = txtGenero.getText();
            Global.getConSql().insertarPelicula(idPelicula, nomPelicula, sinopsis, duracion, fileImagen, fileVideo, idioma, clasificacion, genero);
            Global.mostrarInfo("Se inserto pelicula exitosamente!");
        } catch (SQLException | IOException e) {
            Global.mostrarAlertaError(e.getMessage());
        }
    }

    @FXML
    private void cbIdPeliculaOnAction(ActionEvent event) {
        String idPelicula = cbIdPelicula.getSelectionModel().getSelectedItem();
        ResultSet rs = Global.getConSql().consulta(Queries.selectPelicula(idPelicula, false));
        try {
            rs.next();
            txtIdPelicula.setText(rs.getString(1));
            txtNomPelicula.setText(rs.getString(2));
            txtSinopsis.setText(rs.getString(3));
            txtDuracion.setText(rs.getString(4));
            txtIdioma.setText(rs.getString(5));
            txtClasificacion.setText(rs.getString(6));
            txtGenero.setText(rs.getString(7));
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
    }

    @FXML
    private void cbIdSalaOnAction(ActionEvent event) {
        cbIdCine.getSelectionModel().select(cbIdSala.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void btnAgregarFuncionOnAction(ActionEvent event) {
        String idFuncion = txtIdFuncion.getText();
        String idPelicula = cbIdPelicula.getSelectionModel().getSelectedItem();
        String idSala = cbIdSala.getSelectionModel().getSelectedItem();
        String idCine = cbIdCine.getSelectionModel().getSelectedItem();
        String dia = dpHorarioDia.getValue().toString();
        String hora = cbHora.getSelectionModel().getSelectedItem();
        String minuto = cbMinuto.getSelectionModel().getSelectedItem();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date horaFormateada = dateFormat.parse(String.format("%s %s:%s", dia, hora, minuto));
            Timestamp ts = new Timestamp(horaFormateada.getTime());
            Global.getConSql().insertarFuncion(idFuncion, idPelicula, idSala, idCine, ts);
            Global.mostrarInfo("Se inserto funcion exitosamente!");
        } catch (ParseException | SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
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
        App.cambiarVista("cliente");
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
