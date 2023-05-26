package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pufighters.Model.Animation;

import static com.example.pufighters.Model.Animation.*;

public class FightController implements Initializable {

    @FXML
    private ImageView reddevil;
    @FXML
    private ImageView pinkzyklon;
    @FXML
    private Button fig_translate;
    @FXML
    private Button fig_rotate;
    @FXML
    private Button fig_fade;
    @FXML
    private Button fig_scale;
    @FXML
    private AnchorPane fightAnchorpane;

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(fightAnchorpane, "Fxml/homepage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fig_translate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tranlate(reddevil);
            }
        });

        fig_rotate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rotate(reddevil);
            }
        });

        fig_fade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(pinkzyklon);
            }
        });

        fig_scale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scale(pinkzyklon);
            }
        });

    }
}