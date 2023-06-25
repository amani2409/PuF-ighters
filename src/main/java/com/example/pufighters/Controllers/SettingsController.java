package com.example.pufighters.Controllers;

import com.example.pufighters.Model.Music;
import com.example.pufighters.Model.SwitchScene;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {


    @FXML
    private Slider musicSlider;
    @FXML
    private ToggleButton muteButton;

    @FXML
    private AnchorPane settingsAchorpane;

    @FXML
    private ImageView soundIcon;

    String fileName = "/Sounds/epic-logo-6906.mp3";

    MediaPlayer mediaPlayer;
    int musicVolume;


    @FXML
    void muteToggle(ActionEvent event) throws Exception {
        if(muteButton.isSelected()){
            mediaPlayer.setVolume(0);
            Music.setMusicVolume(0);

        } else{
            mediaPlayer.setVolume(100);
            Music.setMusicVolume(100);
        }

    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(settingsAchorpane, "Fxml/homepage.fxml");
    }

    Thread musicThread = new Thread("Music Thread in Settings") {
        public void run() {
            try {
                Media media = new Media(getClass().getResource(fileName).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(Music.getMusicVolume());
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Won't play");
                e.printStackTrace();
            }
            System.out.println("Settings Thread: " + Thread.currentThread().getName());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
        System.out.println("Settings Thread: " + Thread.currentThread().getName());

        musicSlider.setValue(0.7 * 100);

        musicSlider.valueProperty().addListener((Observable observable) -> {
            mediaPlayer.setVolume(musicSlider.getValue()/100);
            Music.setMusicVolume(musicSlider.getValue()/100);
        });


    }
}
