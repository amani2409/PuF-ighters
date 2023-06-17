package com.example.pufighters.Helper;


import com.example.pufighters.Model.Player;

import java.util.ArrayList;

public class StateManager {
    private static ArrayList<Player> playerList = new ArrayList();

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static boolean addPlayerList(Player player) {
        if(!playerList.contains(player)) {
            StateManager.playerList.add(player);

            return true;
        }
        return false;
    }
}
