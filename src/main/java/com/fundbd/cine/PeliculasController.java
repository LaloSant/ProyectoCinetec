package com.fundbd.cine;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PeliculasController {

    private static final String RUTA_VIDEO_TEMP = "src/main/resources/temp/video.mp4";

    @FXML
    private Button primaryButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPausa;
    @FXML
    private ImageView imgView;

    @FXML
    private void mvPlay(ActionEvent event) {
        if (mediaView.getMediaPlayer() == null) {
            cargarVideo(8);
            String encoded = RUTA_VIDEO_TEMP.replace(" ", "%20");
            File f = new File(encoded);
            Media video = new Media(f.toURI().toString());
            MediaPlayer mp = new MediaPlayer(video);
            mp.setAutoPlay(true);
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mediaView.setMediaPlayer(mp);
        }
        mediaView.getMediaPlayer().play();
    }

    @FXML
    private void mvPausa(ActionEvent event) {
        mediaView.getMediaPlayer().pause();
    }
    
    private void cargarVideo(int id){
        ConexionSQL conec = new ConexionSQL();
//        conec.subirBlob(RUTA_VIDEO_TEMP, App.queryInsert("trailer"));
        
        conec.leerBlob(RUTA_VIDEO_TEMP, App.querySelect("trailer", id));
        conec.cerrar();
    }
}
