package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Music;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginAchorpane;
    @FXML
    private TextField username1Text;
    @FXML
    private TextField username2Text;

    String fileName = "/Sounds/epic-orchestra-6960.mp3";
    MediaPlayer mediaPlayer;

    /**
     * Die Methode überprüft, ob die Benutzernamenfelder leer sind. Wenn ja, wird eine Fehlermeldung angezeigt und die Methode wird beendet.
     * Anschließend werden die eingegebenen Benutzernamen über die HttpRequestHelper-Klasse validiert und die entsprechenden Spielerobjekte erhalten.
     * Danach wird eine Erfolgsmeldung angezeigt, der Player-Status im StateManager aktualisiert, der Musik-Thread unterbrochen und der MediaPlayer gestoppt.
     * Schließlich wird zur Homepage-Szene gewechselt.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loginButtonAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (username1Text.getText().isEmpty() || username2Text.getText().isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter your username");
            return;
        }
        Player player1 = HttpRequestHelper.validatePlayer(username1Text.getText());
        Player player2 = HttpRequestHelper.validatePlayer(username2Text.getText());
        {
            infoText("Login Successful! Let's go!", "Welcome in PuFighter", "Login " + username1Text.getText() + " " + username2Text.getText());
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

    /**
     * Erzeugt einen neuen Thread für die Musikwiedergabe während der Login-Page.
     * Der Thread spielt die Musikdatei ab und setzt verschiedene Eigenschaften des MediaPlayers.
     */
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
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicThread.start();
    }
}



