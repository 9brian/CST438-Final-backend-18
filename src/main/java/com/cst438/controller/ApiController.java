package com.cst438.controller;

import com.amadeus.Amadeus;
import com.amadeus.resources.FlightOrder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.google.gson.JsonObject;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @GetMapping("/apiflights/{ogCode}/{destCode}")
    public String flights(@PathVariable String ogCode, @PathVariable String destCode) {
        String endPointString = "/v2/shopping/flight-offers?originLocationCode=" + ogCode +
                "&destinationLocationCode=" + destCode + "&departureDate=2023-12-01&adults=1&max=2";
        String response = AmadeusAPIClient.flightOffers(endPointString);

        // Display the response received from the API
        if (response != null) {
            return "API Response: " + response;
        } else {
            return "API Request failed!!!";
        }
    }
}