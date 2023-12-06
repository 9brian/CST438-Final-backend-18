package com.cst438.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmadeusAPIClient {

    public static String flightOffers(String endPointString) {
        String apiKey = "65XodYiYnUCeFupAVAJgt2OAzHjxCM40";
        String secret = "GBOoGue2gPa1sJbg";
        String token = "DmLG0JdvZkBAJ6lKuATbivB1JUCn"; //GET TOKEN FROM COMMENTED CODE BELOW!!!!!!!!
        String baseUrl = "https://test.api.amadeus.com"; // base URL of the API

        //String testEndpoint = "/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2023-12-01&adults=1&max=2";

        // Create the HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Prepare the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endPointString))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        // Send the request and handle the response
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            //System.out.println("Status code: " + statusCode);
            //System.out.println("Response body: " + responseBody);
            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// USE THIS TO GET TOKEN!!!!!!!!!!!!!!!!!!!!!!!!
//package com.cst438.controller;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AmadeusAPIClient {
//
//    public static void main(String[] args) {
//        String baseUrl = "https://test.api.amadeus.com"; // Base URL of the API
//        String clientId = "65XodYiYnUCeFupAVAJgt2OAzHjxCM40"; // Replace with your API Key
//        String clientSecret = "GBOoGue2gPa1sJbg"; // Replace with your API Secret
//
//        // Prepare the request body
//        Map<Object, Object> data = new HashMap<>();
//        data.put("grant_type", "client_credentials");
//        data.put("client_id", clientId);
//        data.put("client_secret", clientSecret);
//        String requestBody = data.entrySet().stream()
//                .map(entry -> entry.getKey() + "=" + entry.getValue())
//                .reduce((param1, param2) -> param1 + "&" + param2)
//                .orElse("");
//
//        // Create the HttpClient
//        HttpClient client = HttpClient.newHttpClient();
//
//        // Prepare the request for obtaining the access token
//        HttpRequest tokenRequest = HttpRequest.newBuilder()
//                .uri(URI.create(baseUrl + "/v1/security/oauth2/token"))
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        try {
//            // Send the request to obtain the access token
//            HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
//            String token = tokenResponse.body(); // Extract the access token from the response
//
//            // Prepare and send the request to get flight destinations with the obtained token
//            HttpRequest destinationsRequest = HttpRequest.newBuilder()
//                    .uri(URI.create(baseUrl + "/v1/shopping/flight-destinations?origin=PAR&maxPrice=200"))
//                    .header("Authorization", "Bearer " + token)
//                    .GET()
//                    .build();
//
//            // Send the request to get flight destinations
//            HttpResponse<String> destinationsResponse = client.send(destinationsRequest, HttpResponse.BodyHandlers.ofString());
//            System.out.println("Response body: " + destinationsResponse.body());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}