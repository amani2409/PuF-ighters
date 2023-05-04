package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;



import java.io.IOException;


public class SettingsController {

    @FXML
    private AnchorPane settingAchorpane;

    @FXML
    void muteToggle(ActionEvent event) {

    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(settingAchorpane, "Fxml/homepage.fxml");
    }
}
