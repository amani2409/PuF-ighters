package com.example.pufighters.Controllers;

import com.example.pufighters.Model.Animation;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private AnchorPane homepageAchorpane;



    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/login.fxml");
    }

    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/fight.fxml");
    }

        //
    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation.raincomet(12, homepageAchorpane);
    }
}
