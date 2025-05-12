package com.fundbd.cine;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stageLocal) throws IOException {
        scene = new Scene(loadFXML("Home"));
        stage = stageLocal;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("CINETEC MANAGING SYSTEM S.A. de C.V.");
        stage.show();
//        conSql.subirBlob("src/main/resources/temp/img.jpg", Queries.subirImagenPelicula("PE0001"));
//        conSql.subirBlob("src/main/resources/temp/video.mp4", Queries.subirVideoPelicula("PE0001"));
    }

    public static void cambiarVista(String nombre){
        cambiarVista("vistas/" + nombre, true);
    }
    
    public static void cambiarAHome(){
        cambiarVista("Home", true);
    }
    
    private static void cambiarVista(String nombre, boolean si){
        try {
            scene = new Scene(loadFXML(nombre));
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
