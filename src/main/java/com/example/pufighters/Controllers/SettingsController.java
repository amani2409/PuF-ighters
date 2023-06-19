package com.example.pufighters.Controllers;

import com.example.pufighters.Model.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsController {


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

}
