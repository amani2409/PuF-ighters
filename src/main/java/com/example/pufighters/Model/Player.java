package com.example.pufighters.Model;

public class Player {
    String playername;
    int highscore = 0;

    public String getPlayername() {
        return playername;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}