package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayerSearchController {

    @FXML
    private AnchorPane searchplayerAnchorpane;

    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        new SwitchScene(searchplayerAnchorpane, "Fxml/fight.fxml");
    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException{
        new SwitchScene(searchplayerAnchorpane, "Fxml/homepage.fxml");
    }

}
