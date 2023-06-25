package com.example.pufighters.Model;

public class Music {

    private static double musicVolume = 100;

    public static void setMusicVolume(double musicVolume){
        Music.musicVolume = musicVolume;
    }

    public static double getMusicVolume(){
        return musicVolume;
    }

}
