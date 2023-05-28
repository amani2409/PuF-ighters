package com.example.pufighters.Model;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Animation {
    public ImageView fightFigure;

    public Animation(ImageView fightFigure) {
        this.fightFigure = fightFigure;
    }

    public static void tranlate(ImageView figure) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(figure);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(2);
        translate.setByX(250);
        translate.setAutoReverse(true);
        translate.play();
    }

    public static void rotate(ImageView figure) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(figure);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(2);
        rotate.setInterpolator(Interpolator.LINEAR);
        //-------------------normalangriff
        rotate.setByAngle(350);
        //-------------------Norm + Spezialangriff1
        //rotate.setAxis(Rotate.X_AXIS);
        //-------------------translate + vom Bildschirm rollen
        //rotate.setAxis(Rotate.Z_AXIS);
        //rotate.setAutoReverse(true);
        rotate.play();
    }

    public static void fade(ImageView figure) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(figure);
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(2);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public static void scale(ImageView figure) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(figure);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(2);
        scale.setByX(2.0);
        scale.setAutoReverse(true);
        scale.play();
    }

    public static void raincomet(int cometenZahel, AnchorPane aPane) {
        for (int i = 0; i < cometenZahel; i++) {
            ImageView v = new ImageView();
//            fitHeight="163.0" fitWidth="200.0" layoutX="461.0" layoutY="64.0" pickOnBounds="true" preserveRatio="true" rotate="-85.2"
            double sc = Math.random() * 0.1 + 1;
            v.setFitHeight(163.0*sc);
            v.setFitWidth(200.0*sc);
            v.setLayoutX(-100.0*sc);
            v.setLayoutY(-180*sc);
            v.setPickOnBounds(true);
            v.setPreserveRatio(true);
            v.setRotate(-85.2);
            v.setOpacity(0.5);
            v.setDisable(true);
            v.toBack();
            v.setImage(new Image(Animation.class.getResourceAsStream("/Images/meteorite2.png")));
            aPane.getChildren().add(v);
            double x = Math.random() * 950 + 1;
            double y = Math.random() * 100 + 1;
            double dur = Math.random() * 2500 + 700;
            rain(v,y, x, dur);
        }
    }
    public static void rain(ImageView figure, double y, double x, double duration) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(figure);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        figure.setX(x);
        figure.setY(0);
        translate.setByY(800);
        translate.setByX(100);
        translate.play();
    }
}
