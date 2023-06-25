package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import static com.example.pufighters.Model.Animation.*;

public class FightController implements Initializable {

    @FXML
    private Label hp1_bar;
    @FXML
    private Label hp2_bar;
    @FXML
    private  Button a_button;
    @FXML
    private  Button s_button;
    @FXML
    private  Button d_button;
    @FXML
    private  Button w_button;
    @FXML
    private  Button i_button;
    @FXML
    private  Button j_button;
    @FXML
    private  Button k_button;
    @FXML
    private  Button l_button;
    @FXML
    private ImageView boom_effect;
    @FXML
    private Label mana_f1;
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
    @FXML
    private AnchorPane fightAnchorpane;

    String fileName = "/Sounds/battle-march-action-loop-6935.mp3";

    MediaPlayer mediaPlayer;

    private int hp1 = 100;
    private int hp2 = 100;




    int timeLeft = 0;

    public void fadeFromePage() {
        FadeTransition fade = new FadeTransition();
        fade.setNode(fightAnchorpane);
        fade.setDuration(Duration.seconds(5));
        fade.setFromValue(1);
        fade.setToValue(1);
        fade.setAutoReverse(true);
        // Eventlistener als Patterns
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("angekommen im Fade aus der Seite Event");
                try {
                    musicThread.interrupt();
                    mediaPlayer.stop();
                    new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fade.play();
    }


    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
    }


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
                System.out.println("Won't play");
                e.printStackTrace();
            }
            System.out.println("Fight Thread: " + Thread.currentThread().getName());
        }
    };



    int i = 3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
        System.out.println("Fight Thread: " + Thread.currentThread().getName());



//        fadeFromePage();


//        timer_fight.setText(String.valueOf(i));
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
//            timer_fight.setText(String.valueOf(i));
            if (i == 3) {
                timer_fight.setText("Ready");
            }
            if (i == 2) {
                timer_fight.setText("Set");
            }
            if (i == 1) {
                timer_fight.setText("FIGHT!");
            }
            if (i == 0) {
                timer_fight.setText("");

            } else {
            }
            i--;
        }));

        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();


        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

        playername1.setText(p1.getPlayername());
        playername2.setText(p2.getPlayername());


        try {
            fighter1.setImage(new Image(f1.getImg().getBinaryStream()));
            fighter2.setImage(new Image(f2.getImg().getBinaryStream()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        ArrayList<Character> attack = new ArrayList<>();
        fightAnchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
//                 waterp1 = .(KeyCode.A);
//                 firep1 = .(KeyCode.W);
//                 plantp1 = .(KeyCode.S);
//                 soilp1 = .(KeyCode.D);
//
//                 waterp2 = .(KeyCode.J);
//                 firep2 = .(KeyCode.I);
//                 plantp2 = .(KeyCode.K);
//                 soilp2 = .(KeyCode.L);

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

                if (hp1 != 0 && hp2 != 0) {
                    if (attack.contains('A') && attack.contains('I')) {
                        hp2 -= 20;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                        System.out.println(attack);
                    }

                    if (attack.contains('A') && attack.contains('K')) {
                        hp1 -= 10;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                        System.out.println(attack);


                    }
                    if (attack.contains('A') && attack.contains('L')) {
                        hp2 -= 10;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                        System.out.println(attack);
                    }

                    // Player 1 = Fire
                    if (attack.contains('W') && attack.contains('J')) {
                        hp1 -= 20;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('W') && attack.contains('I')) {
                        hp1 -= 10;
                        hp2 -= 10;
                        hp1_bar.setMaxWidth(hp1*3);
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('W') && attack.contains('K')) {
                        hp2 -= 10;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('W') && attack.contains('L')) {
                        hp1 -= 10;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }

                    // Player 1 = Plant
                    if (attack.contains('S') && attack.contains('I')) {
                        hp2 -= 10;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('S') && attack.contains('J')) {
                        hp1 -= 20;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('S') && attack.contains('L')) {
                        hp2 -= 10;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }

                    // Player 1 = Earth
                    if (attack.contains('D') && attack.contains('I')) {
                        hp1 -= 10;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('D') && attack.contains('J')) {
                        hp2 -= 10;
                        hp2_bar.setMaxWidth(hp2*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                    if (attack.contains('D') && attack.contains('K')) {
                        hp1 -= 10;
                        hp1_bar.setMaxWidth(hp1*3);
                        System.out.println("HP of Player 1 is: " + hp1);
                        System.out.println("HP of Player 2 is: " + hp2);
                        System.out.println("Before clear: " + attack);
                        attack.clear();
                    }
                } else {
                    try {
                        if (hp1 == 0){
                            System.out.println("Player 2 wins");
                            musicThread.interrupt();
                            mediaPlayer.stop();
                            new SwitchScene(fightAnchorpane, "Fxml/result.fxml");
                            hp1 = 100;
                            hp2 = 100;
                            Player p1 = StateManager.getPlayer(1);
                            Player p2 = StateManager.getPlayer(2);

//                            playername1.setText(p1.getPlayername());
                            playername1.setText(p1.getPlayername());
                            int p1_highscore = p1.getHighscore();
//                            System.out.println("highscore: " + p1_highscore);
                            playername2.setText(p2.getPlayername());
                            int p2_highscore = p2.getHighscore() + 1;
                            p2.setHighscore(p2_highscore);

//                            System.out.println("highscore: " + p2_highscore);
//                            highscore_player1.setText(String.valueOf(p1.getHighscore()));
//                            highscore_player2.setText(String.valueOf(p2.getHighscore()));
//                            JdbcDB.updateDB(playername1.toString(), p1_highscore);
//                            JdbcDB.updateDB(playername2.toString(), p2.getHighscore());
                            System.out.println("highscore: " + p1.getHighscore());
                            System.out.println("highscore: " + p2.getHighscore());
                        }else if(hp2 == 0){
                            System.out.println("Player 1 wins");
                            musicThread.interrupt();
                            mediaPlayer.stop();
                            new SwitchScene(fightAnchorpane, "Fxml/result.fxml");
                            hp1 = 100;
                            hp2 = 100;
                            int p1_highscore = p1.getHighscore() + 1;
                            p1.setHighscore(p1_highscore);
//                            System.out.println("highscore: " + p1_highscore);
                            playername2.setText(p2.getPlayername());
                            int p2_highscore = p2.getHighscore();
//                            System.out.println("highscore: " + p2_highscore);

                            p1.setHighscore(p1_highscore);


//                            highscore_player1.setText(String.valueOf(p1.getHighscore()));
//                            highscore_player2.setText(String.valueOf(p2.getHighscore()));
//                            JdbcDB.updateDB(playername1.toString(), p1.getHighscore());
                            System.out.println("highscore: " + p1.getHighscore());
                            System.out.println("highscore: " + p2.getHighscore());

//                            JdbcDB.updateDB(playername2.toString(), p2_highscore);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}