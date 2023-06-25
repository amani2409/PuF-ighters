package com.example.pufighters.Model;

import com.example.pufighters.Helper.HttpRequestHelper;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

public class Figure {
    String figurename;
    String figureimg;
    Blob img;

    public String getFigurename() {
        return figurename;
    }

    public void setFigurename(String figurename) {
        this.figurename = figurename;
    }

    public Figure(String figurename) {
        this.figurename = figurename;
    }

    public String getFigureimg() {
        return figureimg;
    }

    public void setFigureimg(String figureimg) {
        this.figureimg = figureimg;
    }

    public Image getImg() {
        return new Image(new ByteArrayInputStream(Base64.getDecoder().decode(this.getFigureimg())));
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}