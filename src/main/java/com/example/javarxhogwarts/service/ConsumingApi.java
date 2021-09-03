package com.example.javarxhogwarts.service;

import com.example.javarxhogwarts.entity.Student;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumingApi {

    private static final String webService = "https://api-harrypotter.herokuapp.com/sortinghat";

    public static Student sortingHat() {
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(webService, String.class);
        var gson = new Gson();
        return gson.fromJson(response.getBody(), Student.class);
    }


//    public String setIdHouse(){
//        try {
//            var url = new URL(webService +"/sortinghat");
//            var connect = (HttpURLConnection) url.openConnection();
//
//            if (connect.getResponseCode() != 200) {
//                throw new RuntimeException("algo deu errado :( " + connect.getResponseCode());
//            } else {
//                var response = new BufferedReader(new InputStreamReader(connect.getInputStream()));
//                var convertJson = convertJson(response);
//                var gson = new Gson();
//                return gson.fromJson(convertJson, String.class);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static String convertJson(BufferedReader bufferedReader) {
//        String response, json = "";
//        try {
//            while ((response = bufferedReader.readLine()) != null) {
//                json += response;
//            }
//            return json;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


}
