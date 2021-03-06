package com.tenmax.exam.common;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

public class HttpUtil {

    public String get(String targetUrl) {
        HttpURLConnection connection = null;
        StringBuffer response = new StringBuffer("");

        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"UTF-8"));

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
            }



        } catch (MalformedURLException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return response.toString();
        }
    }
}
