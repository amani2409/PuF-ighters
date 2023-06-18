package com.example.pufighters.Controllers;

import com.example.pufighters.Helper.StateManager;
import com.example.pufighters.Model.*;
import com.google.gson.Gson;
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
    private Button p1_f1;
    @FXML
    private Button p2_f1;
    @FXML
    private Button p1_f4;
    @FXML
    private Button p2_f4;
    @FXML
    private Button p1_f7;
    @FXML
    private Button p2_f7;
    @FXML
    private Button p1_f2;
    @FXML
    private Button p2_f2;
    @FXML
    private Button p1_f8;
    @FXML
    private Button p2_f8;
    @FXML
    private Button p1_f3;
    @FXML
    private Button p2_f3;
    @FXML
    private Button p1_f6;
    @FXML
    private Button p2_f6;
    @FXML
    private Button p1_f9;
    @FXML
    private Button p2_f9;
    @FXML
    private Button p2_f5;
    @FXML
    private Button p1_f5;
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

    String fig_path = "/Images/playfiguren-homescreen/";



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

    public void figChoice(Button pl_fig1, Button pl_fig2, ImageView img_player1, ImageView img_player2, String fig_name1, String fig_name2) {

        Gson g = new Gson();
            System.out.println("button 1 is pressed");
            pl_fig1.setOnAction(actionEvent -> {
                try {
                    Figure f1 = g.fromJson(JdbcDB.getDatabyColumn("figures", "figurename", fig_name1), Figure[].class)[0];
                    System.out.println(f1.getFigurename());
                    img_player1.setImage(new Image(fig_path+f1.getFigurename()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("button 2 is pressed");
            pl_fig2.setOnAction(actionEvent -> {
                try {
                    Figure f2 = g.fromJson(JdbcDB.getDatabyColumn("figures", "figurename", fig_name2), Figure[].class)[0];
                    System.out.println(f2.getFigurename());
                    img_player2.setImage(new Image(fig_path+f2.getFigurename()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

    }

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

            try {
                JdbcDB.readFigureFromDB().equals("playfig-1.png");
                System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            event.consume();
        });

        Animation.raincomet(12, homepageAchorpane);

        Player p1 = StateManager.getPlayerList().get(0);
        Player p2 = StateManager.getPlayerList().get(1);

        player_name1.setText(p1.getPlayername());
        player_name2.setText(p2.getPlayername());
        highscore_player1.setText(String.valueOf(p1.getHighscore()));
        highscore_player2.setText(String.valueOf(p2.getHighscore()));



        figChoice(p1_f1, p2_f1, img_player1, img_player2, "playfig-1.png","playfig-1.png");
        figChoice(p1_f2, p2_f2, img_player1, img_player2, "playfig-2.png","playfig-2.png");
        figChoice(p1_f3, p2_f3, img_player1, img_player2, "playfig-3.png","playfig-3.png");
        figChoice(p1_f4, p2_f4, img_player1, img_player2, "playfig-4.png","playfig-4.png");
        figChoice(p1_f5, p2_f5, img_player1, img_player2, "playfig-5.png","playfig-5.png");
        figChoice(p1_f6, p2_f6, img_player1, img_player2, "playfig-6.png","playfig-6.png");
        figChoice(p1_f7, p2_f7, img_player1, img_player2, "playfig-7.png","playfig-7.png");
        figChoice(p1_f8, p2_f8, img_player1, img_player2, "playfig-8.png","playfig-8.png");
        figChoice(p1_f9, p2_f9, img_player1, img_player2, "playfig-9.png","playfig-9.png");

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
