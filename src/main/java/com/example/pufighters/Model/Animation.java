package com.example.pufighters.Model;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;

public class Animation{
    public ImageView fightFigure;

    int leftToRight = 450;
    int rightToLeft = -450;

    int moveUp = 200;
    int moveDown = -200;

    public Animation(ImageView fightFigure) {
        this.fightFigure = fightFigure;
    }

//    ------------------------- BASIS -------------------------------     //

    public static TranslateTransition tranlate(ImageView figure, int directionX, int directionY) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(figure);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(2);
        translate.setByY(directionY);
        translate.setByX(directionX);
        translate.setAutoReverse(true);
        translate.play();

        return translate;
    }

    public static RotateTransition rotate(ImageView figure) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(figure);
        rotate.setDuration(Duration.millis(1000));
        rotate.setCycleCount(2);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(1440);
        rotate.play();

        return rotate;
    }

    public static FadeTransition fade(ImageView figure, double opacityFrom, double opacityTo) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(figure);
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(2);
        fade.setFromValue(opacityFrom);
        fade.setToValue(opacityTo);
        fade.play();

        return fade;
    }

    public static ScaleTransition scale(ImageView figure, double with, double height) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(figure);
        scale.setDuration(Duration.millis(1000));
        scale.setCycleCount(2);
        scale.setByX(with);
        scale.setByY(height);
        scale.setAutoReverse(true);
        scale.play();

        return scale;
    }

    public static PathTransition arc(ImageView figure) {
        Path arc = new Path();

        arc.getElements().add(new MoveTo(10, 20));
        arc.getElements().add(new QuadCurveTo(440, 40, 250, 340));

        PathTransition arcPath = new PathTransition();
        arcPath.setNode(figure);
        arcPath.setDuration(Duration.millis(1000));
        arcPath.setCycleCount(2);
        arcPath.setPath(arc);
        arcPath.setAutoReverse(true);
        arcPath.play();

        return arcPath;
    }

    //    ------------------------- !!! BASIS - Angirffe!!! -------------------------------     //

//    TAKLE

    public static void takleOrRetrete(ImageView fightFigure, int direction) {
        tranlate(fightFigure, direction, 0);
    }

    public static void jumpOrDig(ImageView fightFigure, int direction) {
        tranlate(fightFigure, 0, direction);
    }

    public static void walzer(ImageView fig, int directX) {
        paraTrans2(tranlate( fig, directX,0 ), rotate(fig));
    }

    public static void balloon(ImageView fig, double with, double height) {
        scale(fig, with, height);
    }
    public static void ghostBalloon(ImageView fig, double with, double height, double opacityFrom, double opacityTo) {
        paraTrans2(scale( fig, with, height), fade(fig, opacityFrom, opacityTo));
    }

    public static void paraTrans2(Transition t_one, Transition t_two) {
        ParallelTransition paraTrans = new ParallelTransition();
        paraTrans.getChildren().addAll(t_one, t_two);
    }

    public static void paraTrans3(ImageView effect, Label mana, Transition t_one,Transition t_two, Transition t_three ) {
        ParallelTransition paraTrans = new ParallelTransition();
        paraTrans.getChildren().addAll(t_one, t_two, t_three, fade(effect, 1, .4 ));
        mana.setPrefWidth(100);
    }
    public static void paraTrans4(ImageView effect, Label mana, Transition t_one,Transition t_two, Transition t_three , Transition t_four) {
        ParallelTransition paraTrans = new ParallelTransition();
        paraTrans.getChildren().addAll(t_one, t_two, t_three, t_four, fade(effect, 1, .4 ));
        mana.setPrefWidth(50);
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
