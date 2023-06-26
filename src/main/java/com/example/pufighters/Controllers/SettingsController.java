package com.example.pufighters.Controllers;

import com.example.pufighters.Model.Music;
import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.Playerhistory;
import com.example.pufighters.Model.SwitchScene;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class SettingsController implements Initializable {


    public Button reset_his_pl1;
    public Button reset_his_pl2;
    public ListView listview_history;
    public ListView listview_history2;
    @FXML
    private ImageView music_icon;

    @FXML
    private Slider musicSlider;
    @FXML
    private ToggleButton muteButton;
    @FXML
    private ToggleButton muteButton1;

    @FXML
    private AnchorPane settingsAchorpane;

    @FXML
    private ImageView soundIcon;

    String fileName = "/Sounds/epic-logo-6906.mp3";

    MediaPlayer mediaPlayer;

    String fileMusicOn = "/Images/music_on.png";
    Image musicOn = new Image(getClass().getResourceAsStream(fileMusicOn));

    String fileMusicOff = "/Images/music_off.png";
    Image musicOff = new Image(getClass().getResourceAsStream(fileMusicOff));


    @FXML
    void muteToggle(ActionEvent event) throws Exception {
        if(muteButton.isSelected()){
            mediaPlayer.setVolume(0);
            Music.setMusicVolume(0);
            music_icon.setImage(musicOff);
        } else{
            mediaPlayer.setVolume(100);
            Music.setMusicVolume(100);
            music_icon.setImage(musicOn);
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
        if (musicSlider.getValue() > 0) {
            music_icon.setImage(musicOn);
        }

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);
        String name1 = p1.getPlayername();
        String name2 = p2.getPlayername();
        reset_his_pl1.setOnAction(event -> {
            HttpRequestHelper.updateHighscore(name1, 0);
            HttpRequestHelper.deletePlayerHistory(name1);
            p1.setHighscore(0);
            setList(listview_history, name1);
        });

        reset_his_pl2.setOnAction(event -> {
            HttpRequestHelper.updateHighscore(name2, 0);
            HttpRequestHelper.deletePlayerHistory(name2);
            p2.setHighscore(0);
            setList(listview_history2, name2);

        });

        List<Playerhistory> h2 = HttpRequestHelper.getPlayerHistory(name2);
        setList(listview_history, name1);
        setList(listview_history2, name2);
    }

    public static void setList(ListView l, String name) {
        List<Playerhistory> h1 = HttpRequestHelper.getPlayerHistory(name);
        l.getItems().clear();
        for (Playerhistory ph : h1) {
            l.getItems().add(ph.toString());
        }
    }
}
