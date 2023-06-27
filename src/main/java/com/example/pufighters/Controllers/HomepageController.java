package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {
    @FXML
    private AnchorPane homepageAchorpane;
    @FXML
    private Button p1_f1;
    @FXML
    private Button p1_f2;
    @FXML
    private Button p1_f3;
    @FXML
    private Button p1_f4;
    @FXML
    private Button p1_f5;
    @FXML
    private Button p1_f6;
    @FXML
    private Button p1_f7;
    @FXML
    private Button p1_f8;
    @FXML
    private Button p1_f9;
    @FXML
    private Button p2_f1;
    @FXML
    private Button p2_f2;
    @FXML
    private Button p2_f3;
    @FXML
    private Button p2_f4;
    @FXML
    private Button p2_f5;
    @FXML
    private Button p2_f6;
    @FXML
    private Button p2_f7;
    @FXML
    private Button p2_f8;
    @FXML
    private Button p2_f9;
    @FXML
    private ImageView pink_music;
    @FXML
    private ImageView bread;
    @FXML
    private ImageView ghost;
    @FXML
    private ImageView apple;
    @FXML
    private ImageView red_devil;
    @FXML
    private ImageView pig;
    @FXML
    private ImageView cherry;
    @FXML
    private ImageView pumpcin;
    @FXML
    private ImageView wolf;
    @FXML
    private ImageView img_player1;
    @FXML
    private ImageView img_player2;
    @FXML
    private Label player_name1;
    @FXML
    private Label player_name2;
    @FXML
    private Label highscore_player1;
    @FXML
    private Label highscore_player2;

    String fileName = "/Sounds/epic-logo-6906.mp3";
    MediaPlayer mediaPlayer;

    /**
     * Wird in der homepage.xml aufgerufen, beim Betätigen des Logout-Buttons wird zur Login-Page weitergeleitet
     */
    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/login.fxml");
    }

    /**
     * Wird in der homepage.xml aufgerufen, beim Betätigen des Start Game Buttons wird zur Fighting-Page weitergeleitet
     */
    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/fight.fxml");
    }

    /**
     * Wird in der homepage.xml aufgerufen, beim Betätigen des Settings-Buttons wird zur Setting-Page weitergeleitet
     */
    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }

    /**
     * Methode zum Setzen der Figurenauswahl für die Spieler.
     * Die Methode reagiert auf Klicks der Buttons pl_fig1 und pl_fig2 und setzt entsprechend die ausgewählte Figur für den jeweiligen Spieler.
     * Die Methode verwendet die übergebenen Parameter pl_fig1, pl_fig2, img_player1, img_player2 und fig_name.
     */
    public void figChoice(Button pl_fig1, Button pl_fig2, ImageView img_player1, ImageView img_player2, String fig_name) {
        pl_fig1.setOnAction(actionEvent -> {
            try {
                Figure f1 = HttpRequestHelper.getFigure(fig_name);
                img_player1.setImage(f1.getImg());
                StateManager.setFightFigure(1, f1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pl_fig2.setOnAction(actionEvent -> {
            try {
                Figure f2 = HttpRequestHelper.getFigure(fig_name);
                img_player2.setImage(f2.getImg());
                StateManager.setFightFigure(2, f2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Erzeugt einen neuen Thread für die Musikwiedergabe während der Homepage.
     * Der Thread spielt die Musikdatei ab und setzt verschiedene Eigenschaften des MediaPlayers.
     */
    Thread musicThread = new Thread("Music Thread in Homepage") {
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
     * Initialisiert die Szene, wenn sie geladen wird.
     *
     * @param url            Die URL der FXML-Datei.
     * @param resourceBundle Das ResourceBundle, das der Szene zugeordnet ist.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();

        Animation.raincomet(12, homepageAchorpane);

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        player_name1.setText(p1.getPlayername());
        player_name2.setText(p2.getPlayername());
        highscore_player1.setText(String.valueOf(p1.getHighscore()));
        highscore_player2.setText(String.valueOf(p2.getHighscore()));

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

        // Figuren werden aus der Datenbank geladen und den ImageViews im Client ausgetauscht
        try {
            pink_music.setImage(HttpRequestHelper.getFigure("playfig-1.png").getImg());
            apple.setImage(HttpRequestHelper.getFigure("playfig-2.png").getImg());
            cherry.setImage(HttpRequestHelper.getFigure("playfig-3.png").getImg());
            bread.setImage(HttpRequestHelper.getFigure("playfig-4.png").getImg());
            pumpcin.setImage(HttpRequestHelper.getFigure("playfig-5.png").getImg());
            red_devil.setImage(HttpRequestHelper.getFigure("playfig-6.png").getImg());
            wolf.setImage(HttpRequestHelper.getFigure("playfig-7.png").getImg());
            pig.setImage(HttpRequestHelper.getFigure("playfig-8.png").getImg());
            ghost.setImage(HttpRequestHelper.getFigure("playfig-9.png").getImg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Images werden den Player zugeordnet
        try {
            if (f1 != null) {
                img_player1.setImage(f1.getImg());
            }
            if (f2 != null) {
                img_player2.setImage(f2.getImg());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Buttons anklicken, um den Playern die jeweilige Fightfigur zuzuordnen
        figChoice(p1_f1, p2_f1, img_player1, img_player2, "playfig-1.png");
        figChoice(p1_f2, p2_f2, img_player1, img_player2, "playfig-2.png");
        figChoice(p1_f3, p2_f3, img_player1, img_player2, "playfig-3.png");
        figChoice(p1_f4, p2_f4, img_player1, img_player2, "playfig-4.png");
        figChoice(p1_f5, p2_f5, img_player1, img_player2, "playfig-5.png");
        figChoice(p1_f6, p2_f6, img_player1, img_player2, "playfig-6.png");
        figChoice(p1_f7, p2_f7, img_player1, img_player2, "playfig-7.png");
        figChoice(p1_f8, p2_f8, img_player1, img_player2, "playfig-8.png");
        figChoice(p1_f9, p2_f9, img_player1, img_player2, "playfig-9.png");
    }
}
