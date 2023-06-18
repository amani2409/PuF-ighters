package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.pufighters.Model.Animation;

import static com.example.pufighters.Model.Animation.*;

public class FightController implements Initializable {

    @FXML
    private Label playername2;
    @FXML
    private Label playername1;
    @FXML
    private ImageView fighter1;
    @FXML
    private ImageView fighter2;
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

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

        playername1.setText(p1.getPlayername());
        playername2.setText(p2.getPlayername());

        try {
            fighter1.setImage(new Image( f1.getImg().getBinaryStream()));
            fighter2.setImage(new Image( f2.getImg().getBinaryStream()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        fig_translate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tranlate(fighter1);
            }
        });

        fig_rotate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rotate(fighter1);
            }
        });

        fig_fade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(fighter2);
            }
        });

        fig_scale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scale(fighter2);
            }
        });

    }
}