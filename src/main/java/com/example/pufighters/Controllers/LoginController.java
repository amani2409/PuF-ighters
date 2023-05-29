package com.example.pufighters.Controllers;

import com.example.pufighters.Model.JdbcDB;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

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

        JdbcDB jdcbc = new JdbcDB();
        boolean userexists1 = jdcbc.validatePlayer(username1Text.getText());
        boolean userexists2 = jdcbc.validatePlayer(username2Text.getText());

        if (!userexists1) {
            jdcbc.writeToDB(username1Text.getText());
        } else if (!userexists2) {
            jdcbc.writeToDB(username2Text.getText());
        } else {
            infoText("Login Successful! Let's go!", null, "Login " + username1Text);
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

}