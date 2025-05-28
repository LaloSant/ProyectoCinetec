/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.fundbd.cine.controller;

import com.fundbd.cine.App;
import com.fundbd.cine.Global;
import com.fundbd.cine.Queries;
import com.fundbd.cine.model.Asiento;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author eriks
 */
public class AsientosNormController implements Initializable {

    private static final int MAXBOLETOS = 10;
    private static final ArrayList<Asiento> seleccionados = new ArrayList<>();
    private static final ArrayList<ArrayList<Asiento>> ASIENTOS = new ArrayList<>();
    private static final ArrayList<String> idsClientes = new ArrayList<>();
    private static final int PNORMAL = 70;
    private static final int PVIP = 170;
    private static final float DESCVIP = 0.824f;
    private static final float DESCNOMR = 0.735f;
    private static int total = 0;
    private static boolean descMiercoles = false;
    private static boolean descLunes = false;
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
    private ComboBox<String> cbBoxClientes;
    @FXML
    private CheckBox chkNinioAdulto;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtIdCompra;
    @FXML
    private Button btnCartelera;
    @FXML
    private Label lblDescuento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBoxCines.setItems(FXCollections.observableArrayList(Global.getCines().values()));
        cbBoxCines.getSelectionModel().select(Global.getCineActual());
        try {
            cargarClientes();
            cargarAsientos();
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
        String tipoSala = Global.getTipoSalaActual();
        if (tipoSala.equalsIgnoreCase("VIP")) {
            cbFilas.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E"));
            cbColumnas.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6"));
        } else {
            cbFilas.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
            cbColumnas.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
        }
        cbFilas.setDisable(true);
        cbColumnas.setDisable(true);
        btnAgregar.setDisable(true);
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            descMiercoles = true;

        }
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) {
            LocalTime horaActual = LocalTime.now();
            if (horaActual.isAfter(LocalTime.of(11, 00)) && horaActual.isBefore(LocalTime.of(13, 00))) {
                descLunes = true;
            }
        }
        if (descLunes) {
            lblDescuento.setText("30% DESCUENTO (Lunes temprano)");
            txtMostrar.appendText(String.format("Lunes de 30 por ciento de descuento - Horario de 11:00 a 13:00 %n"));
            txtMostrar.appendText(String.format("----------------------------------------------------" + "%n"));
        }
        if (descMiercoles) {
            lblDescuento.setText("MIERCOLES 2 X 1 (NO SUMAN PROMOCIONES)");
            txtMostrar.appendText(String.format("Miercoles de 2 x 1 - N/A niños y adultos mayores %n"));
            txtMostrar.appendText(String.format("------------------------------------------------" + "%n"));
            chkNinioAdulto.setDisable(descMiercoles);
        }
    }

    private void cargarClientes() throws SQLException {
        ResultSet rs = Global.getConSql().consulta(Queries.selectAllClientes());
        while (rs.next()) {
            idsClientes.add(rs.getString("id_cliente"));
            cbBoxClientes.getItems().add(rs.getString("id_cliente") + ".- " + rs.getString("nombre"));
        }
    }

    private void cargarAsientos() throws SQLException {
        boolean hayDisponibles = false;
        ASIENTOS.clear();
        ResultSet rs = Global.getConSql().consulta(Queries.selectAsientos(Global.getIdFuncionActual()));
        char anterior = 'A';
        ArrayList<Asiento> temp = new ArrayList<>();
        while (rs.next()) {
            String idAsientos = rs.getString(1);
            String idFuncion = rs.getString(2);
            boolean disponible = rs.getString(3).trim().equalsIgnoreCase("true");
            if (disponible) {
                hayDisponibles = true;
            }
            char fila = rs.getString(4).charAt(0);
            String columna = rs.getString(5);
            if (anterior != fila) {
                ASIENTOS.add(temp);
                temp = new ArrayList<>();
                anterior = fila;
            }
            Asiento a = new Asiento(idAsientos, idFuncion, disponible, fila, columna);
            temp.add(a);
        }
        ASIENTOS.add(temp);
        if (!hayDisponibles) {
            Global.mostrarAlertaError("No se pueden comprar mas boletos... la sala esta llena");
        }
        App.cambiarVista("pelicula");
    }

    @FXML
    private void cbColumnas(ActionEvent event) {

    }

    @FXML
    private void cbFilas(ActionEvent event) {

    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        if (seleccionados.size() >= MAXBOLETOS) {
            Global.mostrarAlertaError("Solo puedes agregar 10 boletos.");
            return;
        }
        if (cbFilas.getValue() == null && cbColumnas.getValue() == null) {
            Global.mostrarInfo("SELECCIONE UN ASIENTO");
            return;
        }
        Asiento temp = ASIENTOS.get(cbFilas.getSelectionModel().getSelectedIndex()).get(cbColumnas.getSelectionModel().getSelectedIndex());
        if (!temp.isDisponible()) {
            Global.mostrarAlertaError("El asiento ya esta ocupado.");
            return;
        }
        String tipoSala = Global.getTipoSalaActual();

        temp.setDisponible(false);
        String txtnino = "";
        String txtmiercoles = "";

        if (chkNinioAdulto.isSelected()) {
            txtnino = "(Con descuento)";
            if (tipoSala.equalsIgnoreCase("VIP")) {
                if (descLunes) {
                    total += ((PVIP - (PVIP * 0.3)) * DESCVIP);
                } else {
                    total += (PVIP * DESCVIP);
                }
            } else {
                if (descLunes) {
                    total += ((PNORMAL - (PNORMAL * 0.3)) * DESCNOMR);
                } else {
                    total += (PNORMAL * DESCNOMR);
                }
            }

        } else {
            if (tipoSala.equalsIgnoreCase("VIP")) {
                if (descMiercoles) {
                    if (seleccionados.isEmpty()) {
                        total += PVIP;
                    } else {
                        if (seleccionados.size() % 2 == 0) {
                            total += PVIP;
                        } else {
                            txtnino = "(Segundo boleto gratis)";
                        }
                    }
                }

                if (descLunes) {
                    total += (PVIP - (PVIP * 0.3));
                }
                if (!descMiercoles && !descLunes) {
                    total += PVIP;
                }
            } else {
                if (descMiercoles) {
                    if (seleccionados.isEmpty()) {
                        total += PNORMAL;
                    } else {
                        if (seleccionados.size() % 2 == 0) {
                            total += PNORMAL;
                        } else {
                            txtnino = "(Segundo boleto gratis)";
                        }
                    }
                }
                if (descLunes) {
                    total += (PNORMAL - (PNORMAL * 0.3));
                }
                if (!descLunes && !descMiercoles) {
                    total += PNORMAL;
                }
            }
        }
        lblTotal.setText(total + "");
        txtMostrar.appendText(String.format("Fila: %s -- Columna: %s %s %n", temp.getFila(), temp.getColumna(), txtnino));
        seleccionados.add(temp);
        txtIdCompra.setDisable(false);
    }

    @FXML
    private void cbBoxClientesOnAction(ActionEvent event) {
        cbFilas.setDisable(false);
        cbColumnas.setDisable(false);
        btnAgregar.setDisable(false);
        txtMostrar.clear();
        for (Asiento seleccionado : seleccionados) {
            seleccionado.setDisponible(true);
        }
        seleccionados.clear();
        total = 0;
        lblTotal.setText("0");
    }

    @FXML
    private void txtIdCompra(ActionEvent event) {
        btnComprar.setDisable(false);
    }

    @FXML
    private void btnComprarOnAction(ActionEvent event) {
        String idCompra = txtIdCompra.getText();
        String idCliente = idsClientes.get(cbBoxClientes.getSelectionModel().getSelectedIndex());
        try {
            Global.getConSql().insertarCompra(idCompra, idCliente, total);
            Global.mostrarInfo("Se guardo la compra");
            int contador = numBoletos() + 1;
            for (Asiento seleccionado : seleccionados) {
                String idBoleto = String.format("BO%04d", contador++);
                Global.getConSql().insertarBoleto(idBoleto, seleccionado.getIdAsiento(), idCompra);
                Global.getConSql().updateAsientoDisponible(seleccionado.getIdAsiento());
            }
            App.cambiarVista("pelicula");
        } catch (SQLException | IOException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
    }

    private int numBoletos() {
        ResultSet rs = Global.getConSql().consulta(Queries.contarBoletos());
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            Global.mostrarAlertaError(ex.getMessage());
        }
        return 0;
    }

    @FXML
    private void cbBoxCinesOnAction(ActionEvent event) {
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
    private void mnuItemAcercaDeOnAction(ActionEvent event) {
        Global.mostrarMenuCreditos();
    }

    @FXML
    private void btnCartelera(ActionEvent event) {
        App.cambiarVista("cartelera");
    }
}
