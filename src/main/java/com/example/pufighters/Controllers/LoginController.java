package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.JdbcDB;
//import com.example.pufighters.Model.Music;
import com.example.pufighters.Model.Music;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
//import com.example.pufighters.MultiThread;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginAchorpane;

    @FXML
    private Button login_button;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private TextField username1Text;

    @FXML
    private TextField username2Text;

    String fileName = "/Sounds/epic-orchestra-6960.mp3";

    MediaPlayer mediaPlayer;



    public void loginButtonAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (username1Text.getText().isEmpty() || username2Text.getText().isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter your username");
            return;
        }


        JdbcDB jdcbc = new JdbcDB();
        String userexists1 = jdcbc.validatePlayer(username1Text.getText());
        String userexists2 = jdcbc.validatePlayer(username2Text.getText());
        Player player1 = new Player(username1Text.getText());
        Player player2 = new Player(username2Text.getText());
        Gson g = new Gson();
        if (userexists1 != null) {
            player1 = g.fromJson(userexists1, Player.class);
        }
        if (userexists2 != null) {
            player2 = g.fromJson(userexists2, Player.class);
        }
        {
            infoText("Login Successful! Let's go!", null, "Login " + username1Text);
            StateManager.setPlayer(1, player1);
            StateManager.setPlayer(2, player2);

            musicThread.interrupt();
            mediaPlayer.stop();
            new SwitchScene(loginAchorpane, "Fxml/homepage.fxml");
        }

    }

    public static void infoText(String message, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    Thread musicThread = new Thread("Music Thread in Login") {
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
            System.out.println("Login Thread: " + Thread.currentThread().getName());
        }
    };


    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(loginAchorpane, "Fxml/homepage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
        System.out.println("LoginThread: " + Thread.currentThread().getName());
    }
}



