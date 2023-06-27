package com.example.pufighters.Helper;

import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ParameterStringBuilder {
    /**
     * Erstellt eine Zeichenkette aus den Parametern für eine HTTP-Anfrage.
     *
     * @param params Die Parameter, die in die Zeichenkette umgewandelt werden sollen.
     * @return Die Zeichenkette der Parameter.
     * @throws UnsupportedEncodingException Wenn das Encoding nicht unterstützt wird.
     */
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        JsonObject jo = new JsonObject();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            jo.addProperty(entry.getKey(), entry.getValue());
        }
        String rs = jo.toString();
        return rs;
    }
}