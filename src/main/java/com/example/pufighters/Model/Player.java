package com.example.pufighters.Model;

public class Player {
    String playername;
    int highscore = 0;
    String img_path = "/Images/user.png";

    public Player() {

    }

    public Player(String playername) {
        this.playername = playername;
    }
    public Player(String playername, int highscore, String img_path) {


        this.highscore = highscore;
        this.img_path = img_path;
    }


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