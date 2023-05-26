package com.example.pufighters.Model;

import javafx.animation.*;
import javafx.scene.image.ImageView;
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

    public  static  void rain() {

    }
}
