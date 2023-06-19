package com.example.pufighters.Helper;


import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.JdbcDB;
import com.example.pufighters.Model.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private static Map<Integer, Player> playerList = new HashMap<>();

    private static Map<Integer, Figure> fightFigures = new HashMap<>();


    public static void setFightFigure(Integer num, Figure fightFigure) {
        StateManager.fightFigures.put(num, fightFigure);
    }

    public static void setPlayer(int num, Player player) throws SQLException {
        StateManager.playerList.put(num, player);
        if(!fightFigures.containsKey(num)) {
            fightFigures.put(num, JdbcDB.getFig("playfig-4.png"));
        }
    }

    public static Figure getFightFigure(int num) {
        return StateManager.fightFigures.get(num);
    }

    public static Player getPlayer(int num) {
        return StateManager.playerList.get(num);
    }
}