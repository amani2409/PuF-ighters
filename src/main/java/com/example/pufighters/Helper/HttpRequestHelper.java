package com.example.pufighters.Helper;

import com.example.pufighters.Model.Figure;
import com.example.pufighters.Model.Player;
import com.example.pufighters.Model.Playerhistory;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.*;
import java.util.*;

public class HttpRequestHelper {
    /**
     * Die Methode updateHighscore sendet einen PUT-HTTP-Request an einen Server, um den Highscore eines Spielers zu aktualisieren.
     *
     * @param name      Der Name des Spielers.
     * @param highscore Der neue Highscore.
     * @return Die Serverantwort auf die Anfrage.
     */
    public static String updateHighscore(String name, int highscore) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("playername", name);
        parameters.put("highscore", highscore + "");
        String response = executeRequest("http://localhost:8080/player", "PUT", parameters);
        return response;
    }

    /**
     * Die Methode updateHistory sendet einen PUT-HTTP-Request an einen Server, um die Spielerhistorie zu aktualisieren.
     *
     * @param ph Die Spielerhistorie.
     * @return Die Serverantwort auf die Anfrage.
     */
    public static String updateHistory(Playerhistory ph) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("playername", ph.getPlayername());
        parameters.put("highscore", ph.getHighscore() + "");
        parameters.put("date", ph.getDate() + "");
        parameters.put("result", ph.getResult());
        String response = executeRequest("http://localhost:8080/history", "PUT", parameters);
        return response;
    }

    /**
     * Die Methode validatePlayer überprüft die Gültigkeit eines Spielers, indem sie einen POST-HTTP-Request an den Server sendet.
     *
     * @param playername Der Spielername, der überprüft werden soll.
     * @return Wenn der Spieler nicht gefunden wurde, dann wird null zurückgegeben, ansonsten wird der Spieler zurückgegeben
     */
    public static Player validatePlayer(String playername) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("playername", playername);
        String response = executeRequest("http://localhost:8080/playername", "POST", parameters);
        Player p = new Gson().fromJson(response, Player.class);
        return p;
    }

    /**
     * Die Methode getFigure ruft eine Figur von einem Server ab, indem sie einen GET-HTTP-Request an die entsprechende URL sendet.
     *
     * @param figurename Der Name der Figur, die abgerufen werden soll.
     * @return Die abgerufene Figur.
     */
    public static Figure getFigure(String figurename) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("figurename", figurename);
        String response = executeRequest("http://localhost:8080/figure?figurename=" + figurename, "GET", null);
        Figure f = new Gson().fromJson(response, Figure.class);
        return f;
    }

    /**
     * Löscht die Spielerhistorie eines Spielers.
     *
     * @param playername Der Name des Spielers, dessen Historie gelöscht werden soll.
     * @return Die Serverantwort auf die Lösch-Anfrage.
     */
    public static String deletePlayerHistory(String playername) {
        String response = executeRequest("http://localhost:8080/history?playername=" + playername, "DELETE", null);
        return response;
    }

    /**
     * Ruft die Spielerhistorie eines Spielers ab.
     *
     * @param playername Der Name des Spielers, dessen Historie abgerufen werden soll.
     * @return Eine Liste mit den Spielerhistorie-Einträgen des Spielers.
     */
    public static List<Playerhistory> getPlayerHistory(String playername) {
        String response = executeRequest("http://localhost:8080/history?playername=" + playername, "GET", null);
        if (response != null) {
            List<Playerhistory> ph = Arrays.asList(new Gson().fromJson(response, Playerhistory[].class));
            return ph;
        }
        return null;
    }

    /**
     * Sendet eine HTTP-Anfrage an den Server und empfängt die Serverantwort.
     *
     * @param link          Der Link, an den die Anfrage gesendet werden soll.
     * @param requestMethod Die HTTP-Anfragemethode (z.B. GET, POST, PUT, DELETE).
     * @param params        Die Parameter, die an die Anfrage angehängt werden sollen (optional).
     * @return Die Serverantwort als Zeichenkette.
     */
    public static String executeRequest(String link, String requestMethod, Map<String, String> params) {
        HttpURLConnection connection;
        URL url = null;
        try {
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type",
                    "application/json");
            connection.setDoOutput(true);

            if (params != null) {
                //Send request
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(params));
                out.flush();
                out.close();
            }
            //Get Response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
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
