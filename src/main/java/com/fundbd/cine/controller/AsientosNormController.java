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
    private Button btnVerCartelera;
    private boolean[][] asientosDisponibles;
    private int boletosAgregados = 0;
    private final int maxBoletos = 5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());

        String tipoSala = Global.getTipoSalaActual();
        boolean esSalaVIP = tipoSala.equalsIgnoreCase("VIP");

        int numFilas;
        int numColumnas;

        if (esSalaVIP)
        {
            numFilas = 5;
            numColumnas = 10;
        } else
        {
            numFilas = 10;
            numColumnas = 20;
        }

        asientosDisponibles = new boolean[numFilas][numColumnas];

        cbFilas.getItems().clear();
        cbColumnas.getItems().clear();

        for (int i = 0; i < numFilas; i++)
        {
            cbFilas.getItems().add("Fila " + (char) ('A' + i));
        }

        for (int i = 1; i <= numColumnas; i++)
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

    @FXML
    private void btnAgregar(ActionEvent event)
    {
        if (boletosAgregados >= maxBoletos)
        {
            Global.mostrarAlertaError("Solo puedes agregar 5 boletos.\n");
            return;
        }

        String filaTexto = cbFilas.getValue();
        String columnaTexto = cbColumnas.getValue();

        if (filaTexto != null && columnaTexto != null)
        {
            int fila = filaTexto.charAt(filaTexto.length() - 1) - 'A';
            int columna = Integer.parseInt(columnaTexto.replace("Columna ", "")) - 1;

            if (asientosDisponibles[fila][columna])
            {
                Global.mostrarAlertaError("El asiento " + filaTexto + " " + columnaTexto + " ya esta ocupado.\n");
            } else
            {
                asientosDisponibles[fila][columna] = true;
                boletosAgregados++;

                String cine = cbBoxCines.getValue();
                String tipoSala = Global.getTipoSalaActual();

                txtMostrar.appendText("Boleto generado:\n");
                txtMostrar.appendText("Sala: " + tipoSala + "\n");
                txtMostrar.appendText("Asiento: " + filaTexto + " " + columnaTexto + "\n");
                txtMostrar.appendText("-----------------------------------" + "\n");
            }
        } else
        {
            Global.mostrarAlertaError("Selecciona una fila o una columna.\n");
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
