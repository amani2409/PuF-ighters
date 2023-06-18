package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.Animation;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.SwitchScene;
import com.example.pufighters.Model.JdbcDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    @FXML
    private ImageView pink_music;
    @FXML
    private ImageView bread;
    @FXML
    private ImageView ghost;
    @FXML
    private ImageView apple;
    @FXML
    private ImageView red_devil;
    @FXML
    private ImageView pig;
    @FXML
    private ImageView cherry;
    @FXML
    private ImageView pumpcin;
    @FXML
    private ImageView wolf;
    @FXML
    private ImageView img_player1;
    @FXML
    private ImageView img_player2;
    @FXML
    private Label player_name1;
    @FXML
    private Label highscore_player2;
    @FXML
    private Label player_name2;
    @FXML
    private Label highscore_player1;
    @FXML
    private Button button_logout;

    @FXML
    private AnchorPane homepageAchorpane;
    Popup popup = new Popup();



    @FXML
    void onSwitchToLogin(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/login.fxml");
    }

    @FXML
    void onSwitchToFight(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/fight.fxml");
    }

        //
    @FXML
    void onSwitchToSettings(ActionEvent event) throws IOException {
        new SwitchScene(homepageAchorpane, "Fxml/settings.fxml");
    }

    public static void buttons(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    Popup pop = new Popup();
    Label la = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        la.setText("HIIHIHIH");
        pig.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("pop");
            pop.getContent().add(la);
            pop.centerOnScreen();
            pop.show(homepageAchorpane, 300, 300);
        });

        apple.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            buttons("Alert.AlertType.INFORMATION", "hi", "ho");
            img_player2.setImage(new Image("/Images/playfiguren-homescreen/playfig-6.png"));
            event.consume();
        });

        Animation.raincomet(12, homepageAchorpane);

        Player p1 = StateManager.getPlayerList().get(0);
        Player p2 = StateManager.getPlayerList().get(1);

        player_name1.setText(p1.getPlayername());
        player_name2.setText(p2.getPlayername());
        highscore_player1.setText(String.valueOf(p1.getHighscore()));
        highscore_player2.setText(String.valueOf(p2.getHighscore()));


//        try_db.setOnAction(actionEvent -> {
//            try {
//                JdbcDB.readFigureFromDB();
//                img_player1.setImage(new Image("/Images/playfiguren-homescreen/"+"playfig-1.png"));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });

    }
}
