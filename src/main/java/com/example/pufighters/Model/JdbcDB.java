package com.example.pufighters.Model;

import com.example.pufighters.Helper.DbHandler;
import com.mysql.cj.MysqlType;
//import com.mysql.cj.xdevapi.*;
import com.example.pufighters.Model.Player;
import com.google.gson.*;

import java.io.IOException;
import java.sql.*;
import java.util.Base64;

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

    public static String getDatabyColumn(String table, String column, String value) throws SQLException {
        String query = "SELECT * FROM "+table+" where "+column+" = ? ";
        Gson g = new Gson();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
//            JsonObject jo = new JsonObject();
            JsonArray ja = new JsonArray();
            while (resultSet.next()) {
                JsonObject j = new JsonObject();
                int count = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String s = resultSet.getMetaData().getColumnName(i);
                    int type = resultSet.getMetaData().getColumnType(i);
                    System.out.println("+++++++++++++++++++++++++++++++"+s+"|"+type);
                    switch (type) {
                        case 4:
                            j.addProperty(s, resultSet.getInt(s));
                            break;
                        case -4:
                            String blob = new String(resultSet.getBlob(s).getBinaryStream().readAllBytes());
                            j.addProperty(s, blob);
                            break;
                        default:
                            j.addProperty(s, resultSet.getString(s));
                    }
                }
                System.out.println(j);
                ja.add(j);
            }
//            jo.add("dataList", ja);
            System.out.println("---------------------"+ja);
            return new Gson().toJson(ja);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static void addRightType(JsonObject ja, ResultSet rs, int index) throws SQLException {

    }

    public static Figure getFig(String figurename) throws SQLException {
        String query = "SELECT * FROM figures where figurename = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, figurename);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Figure f = new Figure(figurename);
                f.setImg(resultSet.getBlob("figureimg"));

                return f;
            }
            writeToDB(figurename);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
