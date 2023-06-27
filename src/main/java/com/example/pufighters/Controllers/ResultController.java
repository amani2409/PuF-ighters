package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Music;
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
    @FXML
    private AnchorPane resultAncorpane;
    @FXML
    private Label los_highscore;
    @FXML
    private Label win_higscore;
    @FXML
    private Label lo_name;
    @FXML
    private Label win_name;
    @FXML
    private ImageView lost_figter;
    @FXML
    private ImageView winning_figter;


    String fileName = "/Sounds/badass-victory-85546.mp3";
    MediaPlayer mediaPlayer;

    /**
     * Wird in der result.xml aufgerufen, beim Betätigen des Homescreen-Buttons wird zur Homepage weitergeleitet
     */
    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(resultAncorpane, "Fxml/homepage.fxml");
    }

    /**
     * Wird in der result.xml aufgerufen, beim Betätigen des Rematch-Buttons wird zur Fighting-Page weitergeleitet
     */
    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(resultAncorpane, "Fxml/fight.fxml");
    }

    /**
     * Erzeugt einen neuen Thread für die Musikwiedergabe während der Result-Page.
     * Der Thread spielt die Musikdatei ab und setzt verschiedene Eigenschaften des MediaPlayers.
     */
    Thread musicThread = new Thread("Music Thread in Result") {
        public void run() {
            try {
                Media media = new Media(getClass().getResource(fileName).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(Music.getMusicVolume());
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Zuerst werden die Figuren des Verlierers und Gewinners sowie die Spielerobjekte des Verlierers und Gewinners aus dem StateManager abgerufen.
     * Danach werden die Benutzeroberflächenelemente entsprechend aktualisiert, indem die Namen, Highscores und Bilder des Verlierers und Gewinners gesetzt werden.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
        Figure loserFig = StateManager.getFightFigure(StateManager.getWinningOrder()[1]);
        Figure winnerFig = StateManager.getFightFigure(StateManager.getWinningOrder()[0]);
        Player loser = StateManager.getLoser();
        Player winner = StateManager.getWinner();

        lo_name.setText(loser.getPlayername() + " has lost");
        los_highscore.setText("Highscore: " + loser.getHighscore());
        win_name.setText(winner.getPlayername() + " has won");
        win_higscore.setText("Highscore: " + winner.getHighscore());

        try {
            lost_figter.setImage(loserFig.getImg());
            winning_figter.setImage(winnerFig.getImg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
