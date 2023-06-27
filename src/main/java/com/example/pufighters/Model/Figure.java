package com.example.pufighters.Model;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Figure {
    String figurename;
    String figureimg;

    public Figure(String figurename) {
        this.figurename = figurename;
    }

    public String getFigureimg() {
        return figureimg;
    }

    public Image getImg() {
        return new Image(new ByteArrayInputStream(Base64.getDecoder().decode(this.getFigureimg())));
    }
}