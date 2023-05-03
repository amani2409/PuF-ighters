

package com.example.pufighters.Controllers;

        import com.example.pufighters.Model.SwitchScene;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.AnchorPane;

        import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane loginAchorpane;

    @FXML
    private Button login_button;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {

    }

    @FXML
    void onSwitchToHomepage(ActionEvent event) throws IOException {
        new SwitchScene(loginAchorpane, "Fxml/homepage.fxml");
    }

}