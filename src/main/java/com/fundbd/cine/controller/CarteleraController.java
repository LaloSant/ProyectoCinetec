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
    private Label lblCartelera;

    public void initialize() {
        Object idCineObj = Global.getCines().keySet().toArray()[Global.getCineActual()];
        leerDatos(idCineObj.toString());
        ponerPeliculas();
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
        lblCartelera.setText("Cartelera de: " + cbBoxCines.getSelectionModel().getSelectedItem());
    }

    private void leerDatos(String idCine) {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllFunciones(idCine));
        try {
            while (rs.next()) {
                String idPelicula = rs.getString(1);
                String titulo = rs.getString(2);
                String sinopsis = rs.getString(3);
                InputStream imagen = rs.getBinaryStream(5);
                Date horario = rs.getDate(10);
                String tipoSala = rs.getString(11);
                String idFuncion = rs.getString(12);
                AnchorPane ap = crearAnchorPanePelicula(titulo, imagen, sinopsis, idPelicula, tipoSala, idFuncion);
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
        gridPaneCentro.getChildren().clear(); //execute order 66
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

    private AnchorPane crearAnchorPanePelicula(String titulo, InputStream imagen, String sinapsis, String idPelicula, String tipoSala, String id_Funcion) throws IOException {
        AnchorPane panel = new AnchorPane();
        panel.setPrefSize(200, 200);
        panel.setStyle("-fx-background-color: #1a2b3f");

        Label lblTitulo = new Label(titulo);
        lblTitulo.setLayoutX(20);
        lblTitulo.setLayoutY(10);
        lblTitulo.setMaxWidth(300);
        lblTitulo.setTextFill(Color.WHITE);
        lblTitulo.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

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
        
        Label lblTipoSala = new Label("Tipo sala: " + tipoSala);
        lblTipoSala.setLayoutX(20);
        lblTipoSala.setLayoutY(160);
        lblTipoSala.setTextFill(Color.WHITE);
        lblTipoSala.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Button btnVer = new Button("Ver");
        btnVer.setLayoutX(242);
        btnVer.setLayoutY(160);
        btnVer.setStyle("-fx-font-size: 15;");
        btnVer.setOnAction(event -> botonPresionado(id_Funcion));

        TextArea textArea = new TextArea(sinapsis);
        textArea.setLayoutX(20);
        textArea.setLayoutY(55);
        textArea.setPrefSize(265, 100);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 15;");
        
        panel.getChildren().addAll(lblTitulo, imageView, btnVer, textArea, lblTipoSala);
        return panel;
    }

    private void botonPresionado(String idFuncion) {
        Global.setIdFuncionActual(idFuncion);
        App.cambiarVista("pelicula");
    }

    @FXML
    public void cbBoxCinesOnAction(ActionEvent event) {
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
    public void mnuItemAcercaDeOnAction(ActionEvent event) {
        Global.mostrarMenuCreditos();
    }

}
