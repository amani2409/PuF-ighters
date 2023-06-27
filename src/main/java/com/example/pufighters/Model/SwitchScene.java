package com.example.pufighters.Model;

import com.example.pufighters.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Objects;

public class SwitchScene {

    /**
     * Erzeugt eine neue Instanz der SwitchScene-Klasse.
     *
     * @param currentAnchorPane Der aktuelle AnchorPane, von der aus die Szene gewechselt wird.
     * @param fxml              Der Pfad zur FXML-Datei der nächsten Szene.
     * @throws IOException      Falls eine I/O-Exception beim Laden der FXML-Datei auftritt.
     */
    public SwitchScene(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAncorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAncorPane);
    }
}
