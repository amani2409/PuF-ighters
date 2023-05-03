package com.example.pufighters.Model;

import com.example.pufighters.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {

    public SwitchScene(AnchorPane currentAncorPane, String fxml) throws IOException {
        AnchorPane nextAncorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        currentAncorPane.getChildren().removeAll();
        currentAncorPane.getChildren().setAll(nextAncorPane);
    }
}
