package com.example.demo.utils;

import com.example.demo.entity.HTTPEntity;
import lombok.AllArgsConstructor;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

@AllArgsConstructor
public class HTTP {
    private String cookie;
    public HTTPEntity post(String url, String body) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
        String merchantUid = "30092479";

//        String json1 = "{\"merchantUid\":\"30082479\",\"sku\":\"510820\",\"model\":\"Внешний аккумулятор CUKTECH PB200P 20000 мАч серый\",\"price\":29990,\"availabilities\":[{\"available\":\"yes\",\"storeId\":\"30082479_PP1\"}],\"cityPrices\":[{\"value\":29990,\"cityId\":\"750000000\"}]}";
//        String json2 = "{\"merchantUid\":\"30082479\",\"sku\":\"133909\",\"model\":\"Цепь MOCHI длина 70 см металл стекло\",\"price\":5991,\"availabilities\":[{\"available\":\"yes\",\"storeId\":\"30082479_PP1\"}],\"cityPrices\":[{\"value\":5991,\"cityId\":\"750000000\"}]}";
//        JsonNode jsonNode = objectMapper.readTree(body);
//        ((ObjectNode)jsonNode).put("price", 29990);
//        ((ObjectNode)jsonNode.get("cityPrices").get(0)).put("value", 29990);

//        System.out.println(String.valueOf(jsonNode).length());

//        String url = "https://mc.shop.kaspi.kz/pricefeed/upload/merchant/process";

        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "kk,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpClient.setRequestProperty("cookie", cookie);
        httpClient.setRequestProperty("Referer", "https://kaspi.kz/");
        httpClient.setRequestProperty("content-type", "application/json; charset=utf8");
        httpClient.setRequestProperty("accept", "application/json, text/plain, */*");
        httpClient.setRequestProperty("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"YaBrowser\";v=\"24.1\", \"Yowser\";v=\"2.5\"");
        httpClient.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpClient.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpClient.setRequestProperty("sec-fetch-dest", "empty");
        httpClient.setRequestProperty("sec-fetch-mode", "cors");
        httpClient.setRequestProperty("sec-fetch-site", "same-site");
        httpClient.setRequestProperty("Referrer-Policy", "strict-origin-when-cross-origin");
        httpClient.setRequestProperty("content-length", String.valueOf(String.valueOf(body).length()));
//        httpClient.setRequestProperty("X-Auth-Token", "MP0ZyfSCdd7OdzqER2+j1s5v98HSDTU0BqW20ZCOZdg=");
        System.out.println("body: " + body);
        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        httpClient.setDoOutput(true);
        try(
                OutputStream os = httpClient.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println(httpClient.getHeaderFields());

        System.out.println(responseCode);
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            return new HTTPEntity(response.toString(), httpClient.getHeaderFields());

        }
    }
    public HTTPEntity get(String url) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
        String merchantUid = "30092479";

//        String json1 = "{\"merchantUid\":\"30082479\",\"sku\":\"510820\",\"model\":\"Внешний аккумулятор CUKTECH PB200P 20000 мАч серый\",\"price\":29990,\"availabilities\":[{\"available\":\"yes\",\"storeId\":\"30082479_PP1\"}],\"cityPrices\":[{\"value\":29990,\"cityId\":\"750000000\"}]}";
//        String json2 = "{\"merchantUid\":\"30082479\",\"sku\":\"133909\",\"model\":\"Цепь MOCHI длина 70 см металл стекло\",\"price\":5991,\"availabilities\":[{\"available\":\"yes\",\"storeId\":\"30082479_PP1\"}],\"cityPrices\":[{\"value\":5991,\"cityId\":\"750000000\"}]}";
//        JsonNode jsonNode = objectMapper.readTree(body);
//        ((ObjectNode)jsonNode).put("price", 29990);
//        ((ObjectNode)jsonNode.get("cityPrices").get(0)).put("value", 29990);

//        System.out.println(String.valueOf(jsonNode).length());

//        String url = "https://mc.shop.kaspi.kz/pricefeed/upload/merchant/process";

        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add reuqest header
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "kk,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpClient.setRequestProperty("cookie", cookie);
        httpClient.setRequestProperty("Referer", "https://kaspi.kz/");
        httpClient.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        httpClient.setRequestProperty("accept", "application/json, text/plain, */*");
        httpClient.setRequestProperty("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"YaBrowser\";v=\"24.1\", \"Yowser\";v=\"2.5\"");
        httpClient.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpClient.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpClient.setRequestProperty("sec-fetch-dest", "empty");
        httpClient.setRequestProperty("sec-fetch-mode", "cors");
        httpClient.setRequestProperty("sec-fetch-site", "same-site");
        httpClient.setRequestProperty("Referrer-Policy", "strict-origin-when-cross-origin");
        httpClient.setRequestProperty("X-Auth-Token", "MP0ZyfSCdd7OdzqER2+j1s5v98HSDTU0BqW20ZCOZdg=");
//        httpClient.setRequestProperty("content-length", String.valueOf(String.valueOf(body).length()));

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        httpClient.setDoOutput(true);
//        try(
//                OutputStream os = httpClient.getOutputStream()) {
//            byte[] input = body.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }

        int responseCode = httpClient.getResponseCode();

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            return new HTTPEntity(response.toString(), httpClient.getHeaderFields());

        }
    }
}
