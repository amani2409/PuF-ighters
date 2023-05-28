

package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.DbHandler;
import com.example.pufighters.Main;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends Main {

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


    static private DbHandler dbHandler;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void loginButtonAction(ActionEvent event) throws SQLException, IOException {
        if (username1Text.getText().isEmpty() || username2Text.getText().isEmpty()){
            showAlert(AlertType.ERROR, "Form Error!", "Please enter your username");
            return;
        }
        boolean flag = validatePlayer(username1Text.getText());
        boolean flag2 = validatePlayer(username2Text.getText());

        if (!flag) {
            writeToDB(username1Text.getText());
        } else if (!flag2) {
            writeToDB(username2Text.getText());
        } else {
            infoText("Login Successful! Let's go!", null, "Login " + username1Text);
            new SwitchScene(loginAchorpane, "Fxml/homepage.fxml");
        }

    }

    public static void infoText(String message, String headerText, String title){
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