package com.example.pufighters.Model;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;

public class Animation {
    public ImageView fightFigure;

    public Animation(ImageView fightFigure) {
        this.fightFigure = fightFigure;
    }

//    ------------------------- BASIS Animationen  -------------------------------     //

    public static TranslateTransition tranlate(ImageView figure, int directionX, int directionY, double duration, boolean aure) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(figure);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(2);
        translate.setByY(directionY);
        translate.setByX(directionX);
        translate.setAutoReverse(aure);
        translate.play();
        translate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(figure, .3, 1);
            }
        });
        return translate;
    }

    public static RotateTransition rotate(ImageView figure, Button x) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(figure);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(2);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(1440);
        rotate.play();
        rotate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(figure, .3, 1);
                x.setStyle("-fx-background-color: #000000");
                x.setStyle("-fx-text-fill: #ffffff");
                x.setStyle("-fx-opacity: 70%");
                x.setStyle("-fx-border-color: #e5dada");
                x.setStyle("-fx-border-radius: 15%");
            }
        });
        return rotate;
    }

    public static FadeTransition fade(ImageView figure, double opacityFrom, double opacityTo) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(figure);
        fade.setDuration(Duration.millis(400));
        fade.setCycleCount(3);
        fade.setFromValue(opacityFrom);
        fade.setToValue(opacityTo);
        fade.play();

        return fade;
    }

    public static FadeTransition fade_rain(ImageView figure, Button x) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(figure);
        fade.setDuration(Duration.millis(400));
        fade.setCycleCount(3);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(figure, .3, 1);
                x.setStyle("-fx-background-color: #000000");
                x.setStyle("-fx-text-fill: #ffffff");
                x.setStyle("-fx-opacity: 70%");
                x.setStyle("-fx-border-color: #e5dada");
                x.setStyle("-fx-border-radius: 15%");
            }
        });
        return fade;
    }

    public static ScaleTransition scale(ImageView figure, double with, double height, Button x, Boolean sprung, int cycount) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(figure);
        scale.setDuration(Duration.millis(500));
        scale.setCycleCount(cycount);
        scale.setByX(with);
        scale.setByY(height);
        scale.setAutoReverse(sprung);
        scale.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fade(figure, .3, 1);
                x.setStyle("-fx-background-color: #000000");
                x.setStyle("-fx-text-fill: #ffffff");
                x.setStyle("-fx-opacity: 70%");
                x.setStyle("-fx-border-color: #e5dada");
                x.setStyle("-fx-border-radius: 15%");
            }
        });
        scale.play();
        return scale;
    }

    public static PathTransition arc(ImageView figure, Button x, double l_r) {
        Path arc = new Path();
        arc.getElements().add(new MoveTo(10, 20));
        arc.getElements().add(new QuadCurveTo(440, 40, l_r, -340));

        PathTransition arcPath = new PathTransition();
        arcPath.setNode(figure);
        arcPath.setDuration(Duration.millis(700));
        arcPath.setCycleCount(2);
        arcPath.setPath(arc);
        arcPath.setAutoReverse(true);
        arcPath.play();
        arcPath.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tranlate(figure, 0, -400, 500, true);
            }
        });
        return arcPath;
    }

    public static void paraTrans2(Transition t_one, Transition t_two) {
        ParallelTransition paraTrans = new ParallelTransition();
        paraTrans.getChildren().addAll(t_one, t_two);
    }

    //    ------------------------- !!! BASIS - Angirffe!!! -------------------------------     //

    //    Figure 1 feuer
    public static void feuer_w(ImageView fightFigure1, Button w) {
        paraTrans2(tranlate(fightFigure1, 500, -150, 600, true), rotate(fightFigure1, w));
        w.setStyle("-fx-background-color: #da4533");
    }

    //    Figure 2 feuer
    public static void feuer_i(ImageView fightFigure2, Button i) {
        paraTrans2(tranlate(fightFigure2, -500, -150, 600, true), rotate(fightFigure2, i));
        i.setStyle("-fx-background-color: #da4533");
    }

    //    Figure 1 solip
    public static void solip_d(ImageView fightFigure1, Button d) {
        paraTrans2(tranlate(fightFigure1, 200, -100, 1000, true), scale(fightFigure1, 4, 2, d, true, 4));
        d.setStyle("-fx-background-color: #d77e0a");
    }

    //    Figure 2 solip
    public static void solip_l(ImageView fightFigure2, Button l) {
        paraTrans2(tranlate(fightFigure2, -200, -100, 1000, true), scale(fightFigure2, 4, 2, l, true, 4));
        l.setStyle("-fx-background-color: #d77e0a");
    }

    //    Figure 1 plant
    public static void plant_s(ImageView fightFigure1, Button s) {
        paraTrans2(arc(fightFigure1, s, 250), scale(fightFigure1, -4, -4, s, true, 2));
        s.setStyle("-fx-background-color: #11d70a");
    }

    //    Figure 2 plant
    public static void plant_k(ImageView fightFigure2, Button k) {
        paraTrans2(arc(fightFigure2, k, -250), scale(fightFigure2, -4, -4, k, true, 2));
        k.setStyle("-fx-background-color: #11D70AFF");
    }

    //    Figure 1 water
    public static void water_a(ImageView fightFigure1, Button a) {
        paraTrans2(tranlate(fightFigure1, -100, -500, 500, true), scale(fightFigure1, 0, 5, a, true, 4));
        a.setStyle("-fx-background-color: #0a58ee");
    }

    //    Figure 2 water
    public static void water_j(ImageView fightFigure2, Button j) {
        paraTrans2(tranlate(fightFigure2, 100, -500, 500, true), scale(fightFigure2, 0, 5, j, true, 4));
        j.setStyle("-fx-background-color: #0A58EEFF");
    }

//    ---------------------- Hintergrund Animation ---------------------------- //

    public static void raincomet(int cometenZahel, AnchorPane aPane) {
        for (int i = 0; i < cometenZahel; i++) {
            ImageView v = new ImageView();
//            fitHeight="163.0" fitWidth="200.0" layoutX="461.0" layoutY="64.0" pickOnBounds="true" preserveRatio="true" rotate="-85.2"
            double sc = Math.random() * 0.1 + 1;
            v.setFitHeight(163.0 * sc);
            v.setFitWidth(200.0 * sc);
            v.setLayoutX(-100.0 * sc);
            v.setLayoutY(-180 * sc);
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
            rain(v, y, x, dur);
        }
    }

    public static Transition rain(ImageView figure, double y, double x, double duration) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(figure);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        figure.setX(x);
        figure.setY(0);
        translate.setByY(800);
        translate.setByX(100);
        translate.play();
        return translate;
    }


}
