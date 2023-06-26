package com.example.pufighters.Model;

import java.util.Date;

public class Playerhistory {
    String playername;
    Integer highscore;
    Long date;


    String result;

    public Playerhistory(String playername,
                         Integer highscore,
                         Long date,
                         String result) {
        this.playername = playername;
        this.highscore = highscore;
        this.date = date;
        this.result = result;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return playername +" | "+highscore+" | "+result+" | "+new Date(date);
    }
}
