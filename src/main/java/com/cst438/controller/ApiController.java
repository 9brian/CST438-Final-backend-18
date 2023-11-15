package com.cst438.controller;

import com.amadeus.Amadeus;
import com.amadeus.resources.FlightOrder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.google.gson.JsonObject;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value="/api")
public class ApiController {
    @GetMapping("/locations")
    public Location[] locations(@RequestParam(required=true) String keyword) throws Exception {
        return AmadeusConnect.INSANCE.location(keyword);
    }
}
