package com.example.pufighters.Helper;

import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.Playerhistory;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

public class HttpRequestHelper {


    public static String updateHighscore(String name, int highscore) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("playername", name);
        parameters.put("highscore", highscore+"");
        String response = executeRequest("http://localhost:8080/player", "PUT", parameters);
        return response;
    }

    public static String updateHistory(Playerhistory ph) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("playername", ph.getPlayername());
        parameters.put("highscore", ph.getHighscore()+"");
        parameters.put("date", ph.getDate()+"");
        parameters.put("result", ph.getResult());
        String response = executeRequest("http://localhost:8080/history", "PUT", parameters);
        return response;
    }

    public static Player validatePlayer(String playername) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("playername", playername);
            String response = executeRequest("http://localhost:8080/playername", "POST", parameters);
            Player p = new Gson().fromJson(response, Player.class);
        return p;
    }

    public static Figure getFigure(String figurename) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("figurename", figurename);
        String response = executeRequest("http://localhost:8080/figure?figurename="+figurename, "GET", null);
        Figure f = new Gson().fromJson(response, Figure.class);
//        System.out.println("---------------------" + Arrays.toString(Base64.getDecoder().decode(f.getFigureimg())));
        return f;
    }

    public static String deletePlayerHistory(String playername) {
        String response = executeRequest("http://localhost:8080/figure?playername="+playername, "DELETE", null);
        return response;
    }

    public static List<Playerhistory> getPlayerHistory(String playername) {
        String response = executeRequest("http://localhost:8080/history?playername="+playername, "GET", null);
        if(response != null){
            List<Playerhistory> ph = Arrays.asList(new Gson().fromJson(response, Playerhistory[].class));
//            System.out.println("#######################################"+ph);
            return ph;
        }
        return null;
    }

    public static String executeRequest(String link, String requestMethod, Map<String, String> params) {
        HttpURLConnection connection;
        URL url = null;
        try {
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type",
                    "application/json");
//            connection.setUseCaches(false);
            connection.setDoOutput(true);

            if (params != null) {
                //Send request
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(params));
                out.flush();
                out.close();
            }
            //Get Response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
//            System.out.println("+++++++++++++++++++++++++++++++"+content);
            connection.disconnect();
            return content.toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
