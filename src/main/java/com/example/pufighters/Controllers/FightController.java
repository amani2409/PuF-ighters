package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
import javafx.animation.AnimationTimer;
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
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.example.pufighters.Model.Animation;
import javafx.util.Duration;

import static com.example.pufighters.Model.Animation.*;

public class FightController implements Initializable {

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
    private Button fig_translate;
    @FXML
    private Button fig_rotate;
    @FXML
    private Button fig_fade;
    @FXML
    private Button fig_scale;
    @FXML
    private AnchorPane fightAnchorpane;


    int moveToRight = 450;
    int moveToLeft = -450;

    int moveUp = -200;
    int moveDown = 200;

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
                    new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fade.play();
    }

//    boolean nextPage = false;

//    Timer fightTimer = new Timer();
//    TimerTask fightTimerTask = new TimerTask() {
//        int countdown = 10;
//        @Override
//        public void run() {
//            if(countdown > 0 ) {
//                Animation.raincomet(8, fightAnchorpane);
//                countdown -= 1;
//            } else {
//               fightTimer.cancel();
//            }
//        }
//    };//    public long fiveSeconds = 0;
//    public Label timer_fight;
//    @Override
//    public void handle(long l) {
//        long dt = l - fiveSeconds;
//        if(dt > 5e9) {
//            fiveSeconds = l;
//            System.out.println("hi");
//
//        }
//    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
            new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
    }

    int i = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fadeFromePage();

        timer_fight.setText(String.valueOf(i));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                i++;
                timer_fight.setText(String.valueOf(i));

                if (i == 300) {
                    System.out.println("Ich bin bei 5 angekommen");
                    try {
                        new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
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



        fig_translate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        fig_rotate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rotate(fighter1);
            }
        });

        fig_fade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                long fiveSeconds = 0;
                long l = 0;
                long dt = l - fiveSeconds;
                    while ( dt > 5e9) {
                        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooooo");
                    }
                try {
                    new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fig_scale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scale(fighter2, 3, 3);
            }
        });


        fightAnchorpane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case A:

//                        FightController n = new FightController();
//                        n.start();
                        System.out.println("Pressed A!");
//                        Animation.paraTrans3(boom_effect,mana_f1, tranlate(fighter1, leftToRight, 0), rotate(fighter1), scale(fighter1));
//                        Animation.paraTrans3(boom_effect,mana_f1, tranlate(fighter2, rightToLeft, 0), fade(fighter1), scale(fighter1));
                        Animation.takleOrRetrete(fighter1, moveUp);
                        Animation.takleOrRetrete(fighter2, moveToRight);
                        break;
                    case S:
                        System.out.println("Pressed S!");
                        Animation.walzer(fighter1, moveToRight);
                        Animation.jumpOrDig(fighter2,moveDown);
//                        Animation.paraTrans3(boom_effect,mana_f1, tranlate(fighter1, leftToRight, moveDown), rotate(fighter1), scale(fighter1));
//                        Animation.paraTrans3(boom_effect,mana_f1, tranlate(fighter2, rightToLeft, moveUp), fade(fighter1), scale(fighter1));
                        break;
                    case W:
//                        rotate(fighter1);
                        Animation.arc(fighter1);
                        Animation.balloon(fighter1, 4, 4);
                        Animation.ghostBalloon(fighter2, 2, 2, 0.2, 0.5);
                        System.out.println("Pressed W!");
                        break;
                    case D:
                        tranlate(fighter1 ,0, moveToRight);
                        System.out.println("Pressed D!");
                        break;

                    case I:
                        fade(fighter2, 1, 0);
                        System.out.println("Pressed UP!");
                        break;
                    case J:
                        scale(fighter2, 3.4, 2.2);
                        System.out.println("Pressed RIGHT!");
                        break;
                    case K:
                        rotate(fighter2);
                        System.out.println("Pressed DOWN!");
                        break;
                    case L:
                        tranlate(fighter2, moveToRight, 0);
                        System.out.println("Pressed LEFT!");
                        break;

                    default:
                        break;
                }
            }
        });
    }

}