package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    public Label los_highscore;
    public Label win_higscore;
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
        Figure loserFig = StateManager.getFightFigure(StateManager.getWinningOrder()[1]);
        Figure winnerFig = StateManager.getFightFigure(StateManager.getWinningOrder()[0]);
        Player loser = StateManager.getLoser();
        Player winner = StateManager.getWinner();
        musicThread.start();
//        mediaPlayer.getVolume();
        System.out.println("Result Thread: " + Thread.currentThread().getName() + " " + lo_name + " "+ loser.getPlayername());

        lo_name.setText(loser.getPlayername() + " has lost");
        los_highscore.setText(loser.getHighscore()+"");
        win_name.setText(winner.getPlayername() + " has won");
        win_higscore.setText(winner.getHighscore()+"");


        try {
            lost_figter.setImage(loserFig.getImg());
            winning_figter.setImage(winnerFig.getImg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
