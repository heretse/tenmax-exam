package com.tenmax.service.common;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

public class HttpUtil {

    public String get(String targetUrl) {
        //return this.httpRequest("GET", targetUrl, "");

        HttpURLConnection connection = null;
        StringBuffer response = new StringBuffer("");

        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"UTF-8"));

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            return response.toString();
        }
    }
}
