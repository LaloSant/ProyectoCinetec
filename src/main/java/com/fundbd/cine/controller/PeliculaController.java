/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class PeliculaController implements Initializable {

    private static String RUTA_VIDEO = "src/main/resources/temp/video.mp4";
    
    String tipoSala;

    @FXML
    private Label lblTitulo;
    @FXML
    private TextArea txtArSinopsis;
    @FXML
    private Label lblDuracion;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblClasificacion;
    @FXML
    private Label lblIdioma;
    @FXML
    private ImageView iVImagen;
    @FXML
    private MediaView mVTrailer;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPausa;
    @FXML
    private Button btnComprar;
    @FXML
    private ComboBox<String> cbBoxCines;
    @FXML
    private Button btnVerCartelera;
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
    private Label lblHorario;
    @FXML
    private Label lblTipoSala;
    @FXML
    private Label lblIdFuncion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leerDatos(Global.getIdFuncionActual());
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
    }

    private void leerDatos(String idPelicula) {
        ResultSet rs = Global.getConSql().consulta(Queries.selectFuncion(idPelicula));
        if (rs == null) {
            return;
        }
        String nombre = "";
        String sinopsis = "";
        int duracion = 0;
        String idioma = "";
        String clasificacion = "";
        String genero = "";
        String horario = "";
        String nombreSala = "";
        String idFuncion = "";
        try {
            rs.next();
            nombre = rs.getString(2);
            sinopsis = rs.getString(3);
            duracion = rs.getInt(4);
            leerBlob(idPelicula, rs.getBinaryStream(6), RUTA_VIDEO);
            mVTrailer.setMediaPlayer(new MediaPlayer(new Media(new File(RUTA_VIDEO).toURI().toString())));
            mVTrailer.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
            idioma = rs.getString(7);
            clasificacion = rs.getString(8);
            genero = rs.getString(9);
            horario = rs.getString(10);
            nombreSala = rs.getString(11);
            idFuncion = rs.getString(12);
            tipoSala = rs.getString(13);
            iVImagen.setImage(new Image(rs.getBinaryStream(5)));
            if (iVImagen.getImage().getHeight() == 0 || iVImagen.getImage().getWidth() == 0) {
                FileInputStream fis = new FileInputStream("src/main/resources/temp/img404.jpg");
                iVImagen.setImage(new Image(fis));
            }
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblTitulo.setText(nombre);
        txtArSinopsis.setText(sinopsis);
        lblIdioma.setText(idioma);
        lblClasificacion.setText(clasificacion);
        lblGenero.setText(genero);
        lblDuracion.setText(String.valueOf(duracion));
        lblHorario.setText(horario);
        lblTipoSala.setText(nombreSala);
        lblIdFuncion.setText(idFuncion);
    }

    public boolean leerBlob(String idPelicula, InputStream is, String ruta) throws IOException {
        boolean termino = false;
        FileOutputStream fos = new FileOutputStream(ruta);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }
        fos.close();
        termino = true;
        return termino;
    }

    @FXML
    private void onBtnPlayAction(ActionEvent event) {
        if (mVTrailer.getMediaPlayer() == null) {
            Global.mostrarAlertaError("Error cargando el video...");
            return;
        }
        if (mVTrailer.getMediaPlayer().getStatus().equals(MediaPlayer.Status.UNKNOWN)) {
            mVTrailer.setMediaPlayer(new MediaPlayer(new Media(new File(RUTA_VIDEO).toURI().toString())));
            mVTrailer.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        }
        mVTrailer.getMediaPlayer().play();
    }

    @FXML
    private void onBtnPausaAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
    }

    @FXML
    private void onBtnComprarAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        Global.setTipoSalaActual(tipoSala);
        App.cambiarVista("asientosNorm");
    }

    private void mnuCarteleraOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        App.cambiarVista("cartelera");
    }

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    @FXML
    private void btnVerCarteleraOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuSelCineOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void mnuVerCarteleraOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        App.cambiarVista("cartelera");
    }

    @FXML
    private void mnuAgregarClienteOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        App.cambiarVista("clientes");
    }

    @FXML
    private void mnuAgregarPeliculaOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        App.cambiarVista("anPelicula");
    }

    @FXML
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
        mVTrailer.getMediaPlayer().pause();
        Global.mostrarMenuCreditos();
    }
}
