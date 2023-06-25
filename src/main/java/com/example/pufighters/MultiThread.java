/*
package com.example.pufighters;

import com.example.pufighters.Model.Music;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class MultiThread extends Thread{

    @Override
    public void run(){
        System.out.println("This is a Thread");
        Media media = null;
        try {
            media = new Media(Music.class.getResource(fileName).toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer = new MediaPlayer(media);

        if(play.equals("play")){
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } else {
            mediaPlayer.getOnPaused();
            mediaPlayer.stop();
        }


    }

}
*/
