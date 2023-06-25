package com.example.pufighters.Helper;

import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        JsonObject jo = new JsonObject();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            jo.addProperty(entry.getKey(), entry.getValue());
//            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//            result.append("&");
        }


//        String resultString = result.toString();
        String rs = jo.toString();
        System.out.println("-----------------------------"+rs);
        return rs;
    }
}