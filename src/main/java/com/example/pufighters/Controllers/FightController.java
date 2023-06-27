package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

public class FightController implements Initializable {
    @FXML
    private AnchorPane fightAnchorpane;
    @FXML
    private Button a_button;
    @FXML
    private Button s_button;
    @FXML
    private Button d_button;
    @FXML
    private Button w_button;
    @FXML
    private Button i_button;
    @FXML
    private Button j_button;
    @FXML
    private Button k_button;
    @FXML
    private Button l_button;
    @FXML
    private Label hp1_bar;
    @FXML
    private Label hp2_bar;
    @FXML
    private Label timer_fight;
    @FXML
    private Label playername2;
    @FXML
    private Label playername1;
    @FXML
    private ImageView fighter1;
    @FXML
    private ImageView fighter2;

    String fileName = "/Sounds/battle-march-action-loop-6935.mp3";
    MediaPlayer mediaPlayer;
    private int hp1 = 100;
    private int hp2 = 100;
    int countDown = 3;
    ArrayList<Character> attack = new ArrayList<>();
    int hpDamage = 8;
    private boolean enableKeyInput = true;

    private void setEnableKeyInput() {
        enableKeyInput = true;
    }

    private void setDisableKeyInput() {
        enableKeyInput = false;
    }

    /**
     * Wird in der fight.xml aufgerufen, beim Betätigen des Aufgeben-Buttons wird zur Homepage weitergeleitet
     */
    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
    }

    /**
     * Erzeugt einen neuen Thread für die Musikwiedergabe während des Kampfes.
     * Der Thread spielt die Musikdatei ab und setzt verschiedene Eigenschaften des MediaPlayers.
     */
    Thread musicThread = new Thread("Music Thread in Fight") {
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

        // Erzeugt eine Timeline-Animation mit einem KeyFrame, das alle 800 Millisekunden ausgeführt wird
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800), e -> {
            timer_fight.setStyle("-fx-font-size: 30");
            if (countDown == 3) {
                timer_fight.setText("Ready");
                setDisableKeyInput();
            }
            if (countDown == 2) {
                timer_fight.setText("Set");
                setDisableKeyInput();
            }
            if (countDown == 1) {
                timer_fight.setText("FIGHT!");
                setDisableKeyInput();
            }
            if (countDown == 0) {
                timer_fight.setText("");
                setEnableKeyInput();
            }
            countDown--;
        }));

        // Setzt die Wiederholung der Timeline-Animation auf unbestimmte Zeit und startet die Animation.
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

        playername1.setText(p1.getPlayername());
        playername2.setText(p2.getPlayername());

        try {
            fighter1.setImage(f1.getImg());
            fighter2.setImage(f2.getImg());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /**
         * Event-Handler für Tastatureingaben auf dem fightAnchorpane.
         * Reagiert auf Tastendrücke und führt entsprechende Aktionen aus.
         */
        fightAnchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!enableKeyInput) {
                    keyEvent.consume();
                } else {
                    // KeyInput für Player 1 ist WASD mit zugehöriger Animation
                    // KeyInput für Player 2 ist IJKL mit zugehöriger Animation
                    switch (keyEvent.getCode()) {
                        case A:
                            attack.add('A');
                            Animation.water_a(fighter1, a_button);
                            break;
                        case S:
                            attack.add('S');
                            Animation.plant_s(fighter1, s_button);
                            break;
                        case W:
                            attack.add('W');
                            Animation.feuer_w(fighter1, w_button);
                            break;
                        case D:
                            attack.add('D');
                            Animation.solip_d(fighter1, d_button);
                            break;
                        case I:
                            attack.add('I');
                            Animation.feuer_i(fighter2, i_button);
                            break;
                        case J:
                            attack.add('J');
                            Animation.water_j(fighter2, j_button);
                            break;
                        case K:
                            attack.add('K');
                            Animation.plant_k(fighter2, k_button);
                            break;
                        case L:
                            attack.add('L');
                            Animation.solip_l(fighter2, l_button);
                            break;
                        default:
                            break;
                    }
                    // Kombinationen von Tasteneingabe überprüfen und HP-Werte entsprechend anpassen
                    if (hp1 != 0 && hp2 != 0) {
                        // Player 1 = Water
                        if (attack.contains('A') && attack.contains('I')) {
                            hp2 -= 20 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }
                        if (attack.contains('A') && attack.contains('K')) {
                            hp1 -= 10 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }
                        if (attack.contains('A') && attack.contains('L')) {
                            hp2 -= 10 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }

                        // Player 1 = Fire
                        if (attack.contains('W') && attack.contains('J')) {
                            hp1 -= 20 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }
                        if (attack.contains('W') && attack.contains('I')) {
                            hp1 -= 10 * hpDamage;
                            hp2 -= 10 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }
                        if (attack.contains('W') && attack.contains('K')) {
                            hp2 -= 10 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }
                        if (attack.contains('W') && attack.contains('L')) {
                            hp1 -= 10 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }

                        // Player 1 = Plant
                        if (attack.contains('S') && attack.contains('I')) {
                            hp2 -= 10 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }
                        if (attack.contains('S') && attack.contains('J')) {
                            hp1 -= 20 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }
                        if (attack.contains('S') && attack.contains('L')) {
                            hp2 -= 10 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }

                        // Player 1 = Earth
                        if (attack.contains('D') && attack.contains('I')) {
                            hp1 -= 10 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }
                        if (attack.contains('D') && attack.contains('J')) {
                            hp2 -= 10 * hpDamage;
                            hp2_bar.setMaxWidth(hp2 * 3);
                            attack.clear();
                        }
                        if (attack.contains('D') && attack.contains('K')) {
                            hp1 -= 10 * hpDamage;
                            hp1_bar.setMaxWidth(hp1 * 3);
                            attack.clear();
                        }
                        // Auswertung des Kampfes, wenn ein Player 0 HP hat und Weiterleitung zur Result-Page
                    } else {
                        try {
                            // Player 2 gewinnt
                            if (hp1 == 0) {
                                musicThread.interrupt();
                                mediaPlayer.stop();
                                StateManager.setWinner(2);
                                p2.setHighscore(p2.getHighscore() + 1);
                                new SwitchScene(fightAnchorpane, "Fxml/result.fxml");
                                hp1 = 100;
                                hp2 = 100;
                                HttpRequestHelper.updateHighscore(p2.getPlayername(), p2.getHighscore());
                                Long t = System.currentTimeMillis();
                                HttpRequestHelper.updateHistory(new Playerhistory(p2.getPlayername(), p2.getHighscore(), t, "winner"));
                                HttpRequestHelper.updateHistory(new Playerhistory(p1.getPlayername(), p1.getHighscore(), t, "loser"));

                            // Player 1 gewinnt
                            } else if (hp2 == 0) {
                                musicThread.interrupt();
                                mediaPlayer.stop();
                                StateManager.setWinner(1);
                                p1.setHighscore(p1.getHighscore() + 1);
                                new SwitchScene(fightAnchorpane, "Fxml/result.fxml");
                                hp1 = 100;
                                hp2 = 100;
                                HttpRequestHelper.updateHighscore(p1.getPlayername(), p1.getHighscore());
                                Long t = System.currentTimeMillis();
                                HttpRequestHelper.updateHistory(new Playerhistory(p1.getPlayername(), p1.getHighscore(), t, "winner"));
                                HttpRequestHelper.updateHistory(new Playerhistory(p2.getPlayername(), p2.getHighscore(), t, "loser"));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}