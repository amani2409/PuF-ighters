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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
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

    public void figChoice(Button pl_fig1, Button pl_fig2, ImageView img_player1, ImageView img_player2, String fig_name) {

            pl_fig1.setOnAction(actionEvent -> {
                try {
                    Figure f1 = JdbcDB.getFig(fig_name);
                    System.out.println(f1.getFigurename());
                    InputStream in = f1.getImg().getBinaryStream();
                    img_player1.setImage(new Image(in));
                    StateManager.setFightFigure(1, f1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            pl_fig2.setOnAction(actionEvent -> {
                try {
//                    Figure f2 = g.fromJson(JdbcDB.getDatabyColumn("figures", "figurename", fig_name2), Figure[].class)[0];
                    Figure f2 = JdbcDB.getFig(fig_name);
                    System.out.println(f2.getFigurename());
                    InputStream in = f2.getImg().getBinaryStream();
                    img_player2.setImage(new Image(in));
                    StateManager.setFightFigure(2, f2);
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

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            event.consume();
        });

        Animation.raincomet(12, homepageAchorpane);

        Player p1 = StateManager.getPlayer(1);
        Player p2 = StateManager.getPlayer(2);

        player_name1.setText(p1.getPlayername());
        player_name2.setText(p2.getPlayername());
        highscore_player1.setText(String.valueOf(p1.getHighscore()));
        highscore_player2.setText(String.valueOf(p2.getHighscore()));

        Figure f1 = StateManager.getFightFigure(1);
        Figure f2 = StateManager.getFightFigure(2);

        try {
            pink_music.setImage(new Image(JdbcDB.getFig("playfig-1.png").getImg().getBinaryStream()));
            apple.setImage(new Image(JdbcDB.getFig("playfig-2.png").getImg().getBinaryStream()));
            cherry.setImage(new Image(JdbcDB.getFig("playfig-3.png").getImg().getBinaryStream()));
            bread.setImage(new Image(JdbcDB.getFig("playfig-4.png").getImg().getBinaryStream()));
            red_devil.setImage(new Image(JdbcDB.getFig("playfig-5.png").getImg().getBinaryStream()));
            pumpcin.setImage(new Image(JdbcDB.getFig("playfig-6.png").getImg().getBinaryStream()));
            ghost.setImage(new Image(JdbcDB.getFig("playfig-7.png").getImg().getBinaryStream()));
            pig.setImage(new Image(JdbcDB.getFig("playfig-8.png").getImg().getBinaryStream()));
            wolf.setImage(new Image(JdbcDB.getFig("playfig-9.png").getImg().getBinaryStream()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(f1 != null) {
                img_player1.setImage(new Image( f1.getImg().getBinaryStream()));
            }
            if(f2 != null) {
                img_player2.setImage(new Image( f2.getImg().getBinaryStream()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        figChoice(p1_f1, p2_f1, img_player1, img_player2, "playfig-1.png");
        figChoice(p1_f2, p2_f2, img_player1, img_player2, "playfig-2.png");
        figChoice(p1_f3, p2_f3, img_player1, img_player2, "playfig-3.png");
        figChoice(p1_f4, p2_f4, img_player1, img_player2, "playfig-4.png");
        figChoice(p1_f5, p2_f5, img_player1, img_player2, "playfig-5.png");
        figChoice(p1_f6, p2_f6, img_player1, img_player2, "playfig-6.png");
        figChoice(p1_f7, p2_f7, img_player1, img_player2, "playfig-7.png");
        figChoice(p1_f8, p2_f8, img_player1, img_player2, "playfig-8.png");
        figChoice(p1_f9, p2_f9, img_player1, img_player2, "playfig-9.png");

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
