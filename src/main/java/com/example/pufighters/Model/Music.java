package com.example.pufighters.Model;

import com.example.pufighters.Controllers.HomepageController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;


public class Music {

    private static double musicVolume = 100;

    public static void setMusicVolume(double musicVolume){
        Music.musicVolume = musicVolume;
    }

    public static double getMusicVolume(){
        return musicVolume;
    }



    /*


    public static void autoPlay(String fileName, String play) {

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


    public static void stopMusic(String fileName) {
        Media media = null;
        try {
            media = new Media(Music.class.getResource(fileName).toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.stop();
    }
*/
}
