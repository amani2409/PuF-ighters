package com.example.pufighters.Model;

import java.sql.Timestamp;

public class Playerhistory {

    String playername;
    Integer highscore;
    Timestamp date;
    String result;

    public Playerhistory(String playername, Integer highscore, Timestamp date, String result) {
        this.playername = playername;
        this.highscore = highscore;
        this.date = date;
        this.result = result;
    }


    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
