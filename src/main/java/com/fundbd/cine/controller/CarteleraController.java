/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CarteleraController {

    private final ArrayList<AnchorPane> peliculas = new ArrayList();

    private final ArrayList<Date> horarios = new ArrayList<>();

    private int pagina = 0;

    @FXML
    private MenuItem mnuCines;

    @FXML
    private MenuItem mnItAcercaDe;

    @FXML
    private MenuItem mnuCartelera;

    @FXML
    private ComboBox<String> cbBoxCines;

    @FXML
    private GridPane gridPaneCentro;

    @FXML
    private TextArea txtArHorarios;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnAnterior;

    @FXML
    private TextField lblPagina;

    public void initialize() {
        Object idCineObj = Global.getCines().keySet().toArray()[Global.getCineActual()];
        leerDatos(idCineObj.toString());
        ponerPeliculas();
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
    }

    private void leerDatos(String idCine) {
        ResultSet rs = Global.getConSql().consulta(Queries.selectPeliculas(idCine));
        try {
            while (rs.next()) {
                String idPelicula = rs.getString(1);
                String titulo = rs.getString(2);
                String sinopsis = rs.getString(3);
                InputStream imagen = rs.getBinaryStream(5);
                Date horario = rs.getDate(10);
                AnchorPane ap = crearAnchorPanePelicula(titulo, imagen, sinopsis, idPelicula);
                GridPane.setMargin(ap, new Insets(15));
                peliculas.add(ap);
                horarios.add(horario);
            }
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(CarteleraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ponerPeliculas() {
        StringBuilder horariosTxt = new StringBuilder();
        gridPaneCentro.getChildren().clear();
        int contador = 0;
        while (contador < 4 && (peliculas.size() - pagina * 4) > contador) {
            AnchorPane ap = peliculas.get(contador + (pagina * 4));
            gridPaneCentro.add(ap, contador % 2, contador / 2);
            Label lbl = (Label) ap.getChildren().get(0);
            horariosTxt
                    .append(lbl.getText()).append(":\n")
                    .append(horarios.get(contador + (pagina * 4)).toString())
                    .append("\n\n");
            contador++;
        }
        txtArHorarios.setText(horariosTxt.toString());
        lblPagina.setText(String.valueOf(pagina + 1));
    }

    @FXML
    public void mnuCinesOnAction(ActionEvent event) {
        App.cambiarAHome();
    }

    @FXML
    private void btnSiguienteOnAction(ActionEvent event) {
        if (((pagina + 1) * 4) < peliculas.size()) {
            lblPagina.setText(String.valueOf((++pagina)) + 1);
            ponerPeliculas();
        }
    }

    @FXML
    private void btnAnteriorOnAction(ActionEvent event) {
        if (pagina > 0) {
            lblPagina.setText(String.valueOf(pagina--));
            ponerPeliculas();
        }
    }

    @FXML
    public void mnuCarteleraOnAction(ActionEvent event) {

    }

    @FXML
    public void mnuItemAcercaDeOnAction(ActionEvent event) {
        Global.mostrarMenuCreditos();
    }

    @FXML
    public void cbBoxCinesOnAction(ActionEvent event) {
        Global.setCineActual(cbBoxCines.getSelectionModel().getSelectedIndex());
        App.cambiarVista("cartelera");
    }

    private AnchorPane crearAnchorPanePelicula(String titulo, InputStream imagen, String sinapsis, String idPelicula) throws IOException {
        AnchorPane panel = new AnchorPane();
        panel.setPrefSize(200, 200);
        panel.setStyle("-fx-background-color: #1a2b3f");

        Label lblTitulo = new Label(titulo);
        lblTitulo.setLayoutX(20);
        lblTitulo.setLayoutY(18);
        lblTitulo.setMaxWidth(300);
        lblTitulo.setTextFill(Color.WHITE);
        lblTitulo.setStyle("-fx-font-size: 36; -fx-font-weight: bold;");

        ImageView imageView = new ImageView(new Image(imagen));
        if (imageView.getImage().getHeight() == 0 || imageView.getImage().getWidth() == 0) {
            FileInputStream fis = new FileInputStream("src/main/resources/temp/img404.jpg");
            imageView = new ImageView(new Image(fis));
        }

        imageView.setFitHeight(158);
        imageView.setFitWidth(111);
        imageView.setLayoutX(326);
        imageView.setLayoutY(24);
        imageView.setPreserveRatio(true);

        Button btnVer = new Button("Ver");
        btnVer.setLayoutX(242);
        btnVer.setLayoutY(149);
        btnVer.setStyle("-fx-font-size: 15;");
        btnVer.setOnAction(event -> botonPresionado(idPelicula));

        TextArea textArea = new TextArea(sinapsis);
        textArea.setLayoutX(20);
        textArea.setLayoutY(66);
        textArea.setPrefSize(194, 110);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 15;");
        panel.getChildren().addAll(lblTitulo, imageView, btnVer, textArea);
        return panel;
    }

    private void botonPresionado(String idPelicula) {
        Global.setIdPeliculaActual(idPelicula);
        App.cambiarVista("pelicula");
    }

}
