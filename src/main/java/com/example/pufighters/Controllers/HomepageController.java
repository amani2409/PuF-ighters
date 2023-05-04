package com.example.pufighters.Controllers;

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
    void onSwitchToPlayersearch(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/playerSearch.fxml");
    }

        //
    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }

    @FXML
    void onClickTerminate(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
