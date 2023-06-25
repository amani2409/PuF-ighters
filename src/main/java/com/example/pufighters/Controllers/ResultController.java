package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML
    private Label lo_name;
    @FXML
    private Label win_name;
    @FXML
    private ImageView lost_figter;
    @FXML
    private ImageView winning_figter;
    @FXML
    private ImageView crown;
    @FXML
    private AnchorPane resultAncorpane;

    String fileName = "/Sounds/badass-victory-85546.mp3";

    MediaPlayer mediaPlayer;



    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(resultAncorpane, "Fxml/homepage.fxml");
    }

    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(resultAncorpane, "Fxml/fight.fxml");
    }


    Thread musicThread = new Thread("Music Thread in Result") {
        public void run() {
            try {
                Media media = new Media(getClass().getResource(fileName).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Won't play");
                e.printStackTrace();
            }
            System.out.println("Result Thread: " + Thread.currentThread().getName());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer.getVolume();
        musicThread.start();
        System.out.println("Result Thread: " + Thread.currentThread().getName());

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);
        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        lo_name.setText(p1.getPlayername() + " has lost");
        win_name.setText(p2.getPlayername() + " has won");

        try {
            lost_figter.setImage(new Image(f1.getImg().getBinaryStream()));
            winning_figter.setImage(new Image(f2.getImg().getBinaryStream()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}