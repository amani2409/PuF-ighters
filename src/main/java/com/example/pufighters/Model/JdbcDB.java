package com.example.pufighters.Model;

import com.example.pufighters.Helper.DbHandler;
import com.mysql.cj.MysqlType;
//import com.mysql.cj.xdevapi.*;
import com.example.pufighters.Model.Player;
import com.google.gson.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDB {
    static private DbHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;

    public JdbcDB() throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();
        connection = dbHandler.getDbConnection();
    }


    public static void writeToDB(String playername) throws SQLException {
        String insert = "INSERT INTO player(playername, highscore)" + "VALUES(?,?)";

        preparedStatement = connection.prepareStatement(insert);
//        preparedStatement.setInt(1,1);
        preparedStatement.setString(1, playername);
        preparedStatement.setInt(2, 0);
        preparedStatement.executeUpdate();
    }

    public static String readFromDB() throws SQLException {

        String query = "SELECT * from player";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement1.executeQuery();
        JsonObject jsonObject = new JsonObject();
        JsonArray playerInfoWieDuWillst = new JsonArray();
        while (resultSet.next()) {
            JsonObject db = new JsonObject();
            db.addProperty("playername", resultSet.getString("playername"));
            db.addProperty("highscore", resultSet.getInt("highscore"));
            playerInfoWieDuWillst.add(db);
            System.out.println("Names: " + resultSet.getString("playername") + " Highscore: " + resultSet.getInt("highscore"));
        }

        jsonObject.add("playerList", playerInfoWieDuWillst);
        return jsonObject.toString();
    }

    public static String readFigureFromDB() throws SQLException {

        String query = "SELECT * from figures";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement1.executeQuery();
        JsonObject jsonObject = new JsonObject();
        JsonArray figureInfo = new JsonArray();
        while (resultSet.next()) {
            JsonObject db = new JsonObject();
            db.addProperty("figurename", resultSet.getString("figurename"));
            figureInfo.add(db);
            System.out.println("Figures: " + resultSet.getString("figurename"));
        }

        jsonObject.add("playerList", figureInfo);
        return jsonObject.toString();
    }

    public static void updateDB(Integer playerid, String playername, Integer highscore) throws SQLException {
        String query = "UPDATE player SET playername = ?, highscore = ? where playerid = ?";

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, playername);
            preparedStatement1.setInt(2, highscore);
            preparedStatement1.setInt(3, playerid);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletePlayer(Integer playerid) {
        String query = "DELETE FROM player where playerid = ?";

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setInt(1, playerid);
            preparedStatement1.execute();
            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String validatePlayer(String playername) throws SQLException {
        String query = "SELECT * FROM player where playername = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playername);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                JsonObject j = new JsonObject();
                j.addProperty("playername", resultSet.getString("playername"));
                j.addProperty("highscore", resultSet.getInt("highscore"));
//                j.addProperty("img_path", resultSet.getString("img_path"));
                System.out.println(j);
                return j.toString();
            }

            writeToDB(playername);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
