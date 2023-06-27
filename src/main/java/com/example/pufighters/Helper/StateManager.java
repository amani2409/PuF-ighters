package com.example.pufighters.Helper;

import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Die Klasse StateManager ist verantwortlich für die Verwaltung des Zustands des Spiels.
 * Sie speichert Informationen über die Spieler, ihre Kampffiguren und die Reihenfolge der Gewinner.
 */
public class StateManager {
    private static Map<Integer, Player> playerList = new HashMap<>();
    private static Map<Integer, Figure> fightFigures = new HashMap<>();
    private static int[] winningOrder = new int[2];

    public static void setFightFigure(Integer num, Figure fightFigure) {
        StateManager.fightFigures.put(num, fightFigure);
    }

    public static void setPlayer(int num, Player player) throws SQLException {
        StateManager.playerList.put(num, player);
        if (!fightFigures.containsKey(num)) {
            fightFigures.put(num, HttpRequestHelper.getFigure("playfig-4.png"));
        }
    }

    public static Figure getFightFigure(int num) {
        return StateManager.fightFigures.get(num);
    }

    public static Player getPlayer(int num) {
        return StateManager.playerList.get(num);
    }

    public static int[] getWinningOrder() {
        return winningOrder;
    }

    public static void setWinner(int num) {
        winningOrder[0] = num;
        winningOrder[1] = 3 - num;
    }

    public static Player getWinner() {
        return playerList.get(winningOrder[0]);
    }

    public static Player getLoser() {
        return playerList.get(winningOrder[1]);
    }
}