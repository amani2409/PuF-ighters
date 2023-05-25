package com.example.pufighters;

import com.example.pufighters.Helper.DbHandler;
import java.sql.PreparedStatement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    static private DbHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("PuF-ighters!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        dbHandler = new DbHandler();
        connection = dbHandler.getDbConnection();

        //writeToDB();
        //readFromDB();
        //updateDB(3,"gurke",9);
        deletePlayer(3);

        launch();
    }

    public static void writeToDB() throws SQLException {
        String insert = "INSERT INTO player(playerid, playername, highscore)" + "VALUES(?,?,?)";

        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1,4);
        preparedStatement.setString(2,"DeleteMe");
        preparedStatement.setInt(3,90);
        preparedStatement.executeUpdate();
    }

    public static void readFromDB() throws SQLException {

        String query = "SELECT * from player";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement1.executeQuery();

        while (resultSet.next()) {
            System.out.println("Names: " + resultSet.getString("playername") + " Highscore: " + resultSet.getString("highscore"));
        }

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

    public static void  deletePlayer(Integer playerid) {
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
}