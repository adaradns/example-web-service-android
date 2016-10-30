package com.service.web.example.adara.examplwebservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adara on 10/30/2016.
 */
public class HTTPManager {

    public static String getData(String uri){
        BufferedReader br = null;
        //Peticion HTTP
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //Nos permite ir leyendo lo que se trae desde la peticion
            String line;

            while ((line = br.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (java.io.IOException e) {
            e.printStackTrace();

            return null;
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();

                    return null;
                }
            }
        }
    }
}
