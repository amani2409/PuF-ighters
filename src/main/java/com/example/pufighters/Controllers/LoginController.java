package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.JdbcDB;
import com.example.pufighters.Model.Music;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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


    public void loginButtonAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (username1Text.getText().isEmpty() || username2Text.getText().isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter your username");
            return;
        }

        Player player1 = HttpRequestHelper.validatePlayer(username1Text.getText());
        Player player2 = HttpRequestHelper.validatePlayer(username2Text.getText());

        {
            infoText("Login Successful! Let's go!", null, "Login " + username1Text);
            StateManager.setPlayer(1, player1);
            StateManager.setPlayer(2, player2);
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

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {

        new SwitchScene(loginAchorpane, "Fxml/homepage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread music = new Thread();

        music.start();
        while (music.isAlive()) {
            try {
                Music.autoPlay("/Sounds/epic-orchestra-6960.mp3", "play");
            } catch (Exception e) {
                System.out.println("Won't play");
                e.printStackTrace();
            }

        }

        if (login_button.isPressed()){
            music.interrupt();
            Music.autoPlay("/Sounds/epic-orchestra-6960.mp3", "stop");
        }

//        HttpRequestHelper.getFigure("playfig-4.png");
//        HttpRequestHelper.getPlayerHistory("popo");

    }

}
    /*

        () -> {
            try {
                Music.autoPlay("/Sounds/epic-orchestra-6960.mp3");
            } catch (Exception e) {
                System.out.println("Won't play");
                e.printStackTrace();
            }
        }).start();

        Thread.st;
        Music.stopMusic("/Sounds/epic-orchestra-6960.mp3");

        stop*/



