package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomepageController {

    @FXML
    private Button button_logout;

    @FXML
    private AnchorPane homepageAchorpane;

    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/login.fxml");
    }


    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }


}
