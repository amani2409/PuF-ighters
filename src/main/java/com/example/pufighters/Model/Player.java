package com.example.pufighters.Model;

public class Player {
    String playername;
    int higscore = 0;
    String img_path = "/Images/user.png";

    public Player() {

    }

    public Player(String playername) {
        this.playername = playername;
    }
    public Player(String playername, int higscore, String img_path) {
        this.playername = playername;
        this.higscore = higscore;
        this.img_path = img_path;
    }


    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getHigscore() {
        return higscore;
    }

    public void setHigscore(int higscore) {
        this.higscore = higscore;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
