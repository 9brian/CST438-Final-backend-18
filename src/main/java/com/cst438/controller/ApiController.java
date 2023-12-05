package com.cst438.controller;

import com.amadeus.Amadeus;
import com.amadeus.resources.FlightOrder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.cst438.Domain.Flight;
import com.cst438.Domain.FlightDTO;
import com.cst438.Domain.FlightRepository;
import com.cst438.Domain.Segment;
import com.google.gson.JsonObject;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
public class ApiController {
    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/apiflights/{ogCode}/{destCode}/{date}/{adults}/{max}")
    public String flights(@PathVariable String ogCode, @PathVariable String destCode, @PathVariable String date,
                          @PathVariable String adults, @PathVariable String max) {
        String endPointString = "/v2/shopping/flight-offers?originLocationCode=" + ogCode +
                "&destinationLocationCode=" + destCode + "&departureDate=" + date +
                "&adults=" + adults + "&max=" + max;
        String response = AmadeusAPIClient.flightOffers(endPointString);
        System.out.println("Searching for flights from " + ogCode + " to " + destCode);

        // Display the response received from the API
        if (response != null) {
            List<FlightDTO> flights = new ArrayList<>();
            Flight newFlight = new Flight();
            List<Segment> segmentsList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(response);
                //GET CARRIER INFO
                JsonNode carriers = root.get("dictionaries").get("carriers");
                if (carriers != null) {
                    // Iterate through all key-value pairs in the "carriers" object
                    Iterator<Map.Entry<String, JsonNode>> fieldsIterator = carriers.fields();
                    while (fieldsIterator.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fieldsIterator.next();
                        String carrierCode = entry.getKey(); // 3-letter code is not really needed
                        String carrierName = entry.getValue().asText(); //full name
                        System.out.println("Carrier '" + carrierCode + "': " + carrierName);
                        newFlight.setCarrier_name(carrierCode + "-" + carrierName); //setting carrier name
                    }
                } else {
                    System.out.println("'carriers' object not found");
                }

                // Accessing the 'data' array
                JsonNode dataArray = root.get("data");
                if (dataArray != null && dataArray.isArray()) {
                    for (JsonNode node : dataArray) {
                        // GETTING FLIGHT ID
                        JsonNode idNode = node.get("id");
                        if (idNode != null) {
                            String flightId = idNode.asText();
                            System.out.println("Flight ID: " + flightId);
                            newFlight.setId(Integer.parseInt(flightId)); //SETTING ID

                        }
                        // GETTING TOTAL PRICE
                        JsonNode price = node.get("price");
                        JsonNode total = price.get("total");
                        System.out.println("Total Price: $" + total.asText());
                        newFlight.setPrice(Double.parseDouble(total.asText())); //SETTING Price
                        //GETTING DEPARTURE DATE/TIME && ARRIVAL DATE/TIME
                        JsonNode itineraries = node.get("itineraries");
                        if (itineraries != null && itineraries.isArray()) {
                            for (JsonNode itinerary : itineraries) {
                                JsonNode segments = itinerary.get("segments");
                                if (segments != null && segments.isArray()) {
                                    for (JsonNode segment : segments) { //getting all segments / connecting flights
                                        Segment newSegment = new Segment();
                                        //GETTING DEPARTURE INFORMATION
                                        JsonNode flightCode = segment.get("number");
                                        if(flightCode != null) {
                                            //INSERT FLIGHT NUMBER
                                            System.out.println("Flight Number: " + flightCode.asText());
                                            newFlight.setFlight_no(Integer.parseInt(flightCode.asText())); //setting flight #
                                            newSegment.setFlight_number(Integer.parseInt(flightCode.asText()));
                                        }
                                        JsonNode departure = segment.get("departure");
                                        if (departure != null) {
                                            JsonNode departureAt = departure.get("at");
                                            JsonNode departureCarrier = departure.get("iataCode");
                                            if (departureAt != null) {
                                                String departureTime = departureAt.asText();
                                                String[] parts = departureTime.split("T");
                                                String departureDate = parts[0]; // Contains "2023-12-01"
                                                String deptTime = parts[1]; // Contains "15:45:00"
                                                System.out.println("Departing from: " + departureCarrier +
                                                        " On: " + departureDate + ", at time: " + deptTime);
                                                //setting departure information
                                                newSegment.setDeparture_date(departureDate);
                                                newSegment.setDeparture_time(deptTime);
                                                newSegment.setDeparture_airport_code(departureCarrier.asText());
                                            }
                                        }
                                        //GETTING ARRIVAL INFORMATION
                                        JsonNode arrival = segment.get("arrival");
                                        if(arrival != null) {
                                            JsonNode arrivalAt = arrival.get("at");
                                            JsonNode arrivalCarrier = arrival.get("iataCode");
                                            if (arrivalAt != null) {
                                                String arrivalTime = arrivalAt.asText();
                                                String[] parts = arrivalTime.split("T");
                                                String arrivalDate = parts[0];
                                                String arrTime = parts[1];
                                                System.out.println("Arriving at: " + arrivalCarrier.asText() + " On: " + arrivalDate + ", at time: " + arrTime);
                                                System.out.println();
                                                //setting arrival information
                                                newSegment.setArrival_date(arrivalDate);
                                                newSegment.setArrival_time(arrTime);
                                                newSegment.setArrival_airport_code(arrivalCarrier.asText());
//                                                newFlight.setArrival_date(arrivalDate);
//                                                newFlight.setDeparture_time(arrivalTime);
//                                                newFlight.setDestination(arrivalCarrier.asText());
                                            }
                                        }
                                        //newSegment.setFlightId(newFlight.getId());
                                        segmentsList.add(newSegment);
//                                        newFlight.setSegments(segmentsList);
//                                        flightRepository.save(newFlight);

                                        for (Segment s : segmentsList) {
                                            s.setFlight(newFlight);
                                        }

                                        newFlight.setSegments(segmentsList);
                                        flightRepository.save(newFlight);
                                    }
                                }
                            }
                            System.out.println();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exception (e.g., log error, return default value, etc.)
            }
            return "API Response: " + response;
        } else {
            return "API Request failed!!!";
        }
    }
}

//TO INITIALIZE DB
//@RestController
//public class ApiController {
//    @GetMapping("/")
//    public String flights() {
//        String ogCode = "SYD";
//        String destCode = "BKK";
//        String adults = "1";
//        String date = "2023-12-01";
//        String max = "2";
//
//        String endPointString = "/v2/shopping/flight-offers?originLocationCode=" + ogCode +
//                "&destinationLocationCode=" + destCode + "&departureDate=" + date +
//                "&adults=" + adults + "&max=" + max;
//        String response = AmadeusAPIClient.flightOffers(endPointString);
//
//        // Display the response received from the API
//        if (response != null) {
////            System.out.print(response[0]);
//            return "API Response: " + response;
//        } else {
//            return "API Request failed!!!";
//        }
//    }
//}