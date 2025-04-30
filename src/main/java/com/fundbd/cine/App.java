package com.fundbd.cine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final String RUTARESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("peliculas"));
        stage.setScene(scene);
        stage.show();
//        ConexionSQL conec = new ConexionSQL();
//        ArrayList<String> lista = conec.consulta(querySelect("id_prueba", 8), "id_prueba");
//        for (String testo : lista) {
//            System.out.println(testo);
//        }
//        conec.cerrar();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static String rutaArchivo(String nombre){
        return RUTARESOURCES + nombre;
    }
    
    public static String querySelect(String columna, int id){
        //imagen
        //trailer
        return String.format("SELECT %s FROM prueba WHERE id_prueba = %d", columna, id);
    }
    
    public static String queryInsert(String columna){
        //imagen
        //trailer
        return String.format("INSERT INTO prueba (%s) VALUES (?)", columna);
    }
}