package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FightController {

    @FXML
    private AnchorPane fightAnchorpane;

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
    }

}