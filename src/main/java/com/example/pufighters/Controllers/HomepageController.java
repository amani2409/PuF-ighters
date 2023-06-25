package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.*;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {


    @FXML
    private Button p1_f1;
    @FXML
    private Button p2_f1;
    @FXML
    private Button p1_f4;
    @FXML
    private Button p2_f4;
    @FXML
    private Button p1_f7;
    @FXML
    private Button p2_f7;
    @FXML
    private Button p1_f2;
    @FXML
    private Button p2_f2;
    @FXML
    private Button p1_f8;
    @FXML
    private Button p2_f8;
    @FXML
    private Button p1_f3;
    @FXML
    private Button p2_f3;
    @FXML
    private Button p1_f6;
    @FXML
    private Button p2_f6;
    @FXML
    private Button p1_f9;
    @FXML
    private Button p2_f9;
    @FXML
    private Button p2_f5;
    @FXML
    private Button p1_f5;
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
    private Label highscore_player2;
    @FXML
    private Label player_name2;
    @FXML
    private Label highscore_player1;
    @FXML
    private Button button_logout;

    @FXML
    private AnchorPane homepageAchorpane;

    String fileName = "/Sounds/epic-logo-6906.mp3";

    MediaPlayer mediaPlayer;

    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/login.fxml");
    }

    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/fight.fxml");
    }

    //
    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        musicThread.interrupt();
        mediaPlayer.stop();
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }

    public void figChoice(Button pl_fig1, Button pl_fig2, ImageView img_player1, ImageView img_player2, String fig_name) {

        pl_fig1.setOnAction(actionEvent -> {
            try {
                Figure f1 = HttpRequestHelper.getFigure(fig_name);
                System.out.println(f1.getFigurename());
                img_player1.setImage(f1.getImg());
                StateManager.setFightFigure(1, f1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pl_fig2.setOnAction(actionEvent -> {
            try {
//                    Figure f2 = g.fromJson(JdbcDB.getDatabyColumn("figures", "figurename", fig_name2), Figure[].class)[0];
                Figure f2 = HttpRequestHelper.getFigure(fig_name);
                System.out.println(f2.getFigurename());
                img_player2.setImage(f2.getImg());
                StateManager.setFightFigure(2, f2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

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
                System.out.println("Won't play");
                e.printStackTrace();
            }
            System.out.println("Homepage Thread: " + Thread.currentThread().getName());
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
        System.out.println("Homepage Thread: " + Thread.currentThread().getName());

        Animation.raincomet(12, homepageAchorpane);

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        player_name1.setText(p1.getPlayername());
        player_name2.setText(p2.getPlayername());
        highscore_player1.setText(String.valueOf(p1.getHighscore()));
        highscore_player2.setText(String.valueOf(p2.getHighscore()));

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

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
