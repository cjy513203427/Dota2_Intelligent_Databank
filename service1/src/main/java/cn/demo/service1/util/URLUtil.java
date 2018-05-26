package cn.demo.service1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hasee on 2017/12/1.
 */
public class URLUtil {

    public static String getUrlForHero(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result = new String(buffer.substring(20,buffer.length()-27));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForItem(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result = new String(buffer.substring(19,buffer.length()-15));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForMatchHistory(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
         httpConn.disconnect();

        String result = new String(buffer.substring(94,buffer.length()-2));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForMatchDetail(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result = new String(buffer.substring(10,buffer.length()-1));
        result = "["+result+"]";
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForMatchDetailPlayers(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result = new String(buffer.substring(21,buffer.lastIndexOf("radiant_win")-2));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForSteamAccount(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result = new String(buffer.substring(33,buffer.length()-3));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        return result;
    }

    public static String getUrlForWeather(String strURL) throws IOException {
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)
                url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        String result1 = new String(buffer.substring(buffer.indexOf("now")+5,buffer.lastIndexOf("last_update")-2));
        //JSONArray jsonArray = JSONArray.fromObject(result);
        String result = "["+result1+"]";
        return result;
    }
}
