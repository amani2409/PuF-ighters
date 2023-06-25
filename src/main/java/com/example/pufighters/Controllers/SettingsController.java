package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.HttpRequestHelper;
import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Playerhistory;
import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {


    public Button reset_his_pl1;
    public Button reset_his_pl2;
    public ListView listview_history;
    @FXML
    private ToggleButton muteButton;

    @FXML
    private AnchorPane settingsAchorpane;

    @FXML
    private ImageView soundIcon;


    @FXML
    void muteToggle(ActionEvent event) throws Exception {
/*        Image muteIcon = new Image("Images/user.png");
        ImageView view = new ImageView(muteIcon);
        muteButton.setGraphic(view);*/
//        Image muteIcon = new Image(getClass().getResourceAsStream("Images/mute.png"));
//        muteButton = new ToggleButton("Mute", new ImageView(muteIcon));
        //  soundIcon.setImage(new Image("Images/mute.png"));
/*        final ToggleButton toggle      = new ToggleButton();
        final Image        unselected  = new Image("Images/sound.png");
        final Image        selected    = new Image("Images/mute.png");
        final ImageView    toggleImage = new ImageView();
        toggle.setGraphic(toggleImage);
        toggleImage.imageProperty().bind(Bindings
                .when(toggle.selectedProperty())
                .then(selected)
                .otherwise(unselected)
        );*/
    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(settingsAchorpane, "Fxml/homepage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String name1 = StateManager.getPlayer(1).getPlayername();
        String name2 = StateManager.getPlayer(2).getPlayername();
        reset_his_pl1.setOnAction(event -> {
            HttpRequestHelper.updateHighscore(name1, 0);
            HttpRequestHelper.deletePlayerHistory(name1);
        });

        reset_his_pl2.setOnAction(event -> {
            HttpRequestHelper.updateHighscore(name2, 0);
            HttpRequestHelper.deletePlayerHistory(name2);
        });

        List<Playerhistory> h1 = HttpRequestHelper.getPlayerHistory(name1);
        List<Playerhistory> h2 = HttpRequestHelper.getPlayerHistory(name2);

        for (Playerhistory ph : h1) {
//            Player.to
            listview_history.getItems().add(ph.toString());
        }
//        for (Playerhistory ph : h2) {
//            listview_history.getItems().add(ph.getPlayername()+", "+ph.getHighscore());
//        }
    }
}
