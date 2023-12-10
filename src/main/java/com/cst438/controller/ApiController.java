package com.cst438.controller;

import com.cst438.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ApiController {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SegmentRepository segmentRepository;

    @GetMapping("/apiflights/{ogCode}/{destCode}/{date}/{adults}/{max}")
    public SegmentDTO[] flights(@PathVariable String ogCode, @PathVariable String destCode, @PathVariable String date,
                                @PathVariable String adults, @PathVariable String max) {
        String endPointString = "/v2/shopping/flight-offers?originLocationCode=" + ogCode +
                "&destinationLocationCode=" + destCode + "&departureDate=" + date +
                "&adults=" + adults + "&max=" + max;
        String response = AmadeusAPIClient.flightOffers(endPointString);
        System.out.println();
        System.out.println("Searching for flights from " + ogCode + " to " + destCode);

        // Display the response received from the API
        if (response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(response);
                JsonNode dataArray = root.get("data");
                if (dataArray != null && dataArray.isArray()) {
                    for (JsonNode node : dataArray) {
                        Flight newFlight = new Flight();
                        JsonNode idNode = node.get("id");
                        // SETTING Flight id
                        String flightId = idNode.asText();
                        System.out.println("Flight ID: " + flightId);
                        newFlight.setId(Integer.parseInt(flightId));

                        // SETTING TOTAL PRICE
                        JsonNode price = node.get("price");
                        JsonNode total = price.get("total");
                        System.out.println("Total Price: $" + total.asText());
                        newFlight.setPrice(Double.parseDouble(total.asText())); //SETTING Price

                        boolean isFirstFlightNumberSet = false;
                        //GETTING DEPARTURE DATE/TIME && ARRIVAL DATE/TIME
                        JsonNode itineraries = node.get("itineraries");
                        if (itineraries != null && itineraries.isArray()) {
                            for (JsonNode itinerary : itineraries) {

                                JsonNode segments = itinerary.get("segments");
                                if (segments != null && segments.isArray()) {
                                    for (JsonNode segment : segments) { //getting all segments / connecting flights
                                        Segment newSegment = new Segment();
                                        JsonNode flightCode = segment.get("number");
                                        // SETTING FLIGHT NUMBER ????
                                        System.out.println("Flight Number: " + flightCode.asText());
                                        newSegment.setFlight_number(Integer.parseInt(flightCode.asText()));

                                        if (!isFirstFlightNumberSet) {
                                            newFlight.setFlight_no(Integer.parseInt(flightCode.asText())); // maybe not needed
                                            isFirstFlightNumberSet = true;
                                        }

                                        // SETTING CARRIER INFO
                                        JsonNode carrierCode = segment.get("carrierCode");
                                        newFlight.setCarrier_name(carrierCode.asText());

                                        // SETTING DEPARTURE INFO
                                        JsonNode departure = segment.get("departure");
                                        if (departure != null) {
                                            JsonNode departureAt = departure.get("at");
                                            JsonNode departureCarrier = departure.get("iataCode");
                                            String departureTime = departureAt.asText();
                                            String[] parts = departureTime.split("T");
                                            String departureDate = parts[0]; // Contains "2023-12-01"
                                            String deptTime = parts[1]; // Contains "15:45:00"
                                            System.out.println("Departing from: " + departureCarrier +
                                                    " On: " + departureDate + ", at time: " + deptTime);
                                            newSegment.setDeparture_date(departureDate);
                                            newSegment.setDeparture_time(deptTime);
                                            newSegment.setDeparture_airport_code(departureCarrier.asText());
                                        }
                                        //GETTING ARRIVAL INFORMATION
                                        JsonNode arrival = segment.get("arrival");
                                        if (arrival != null) {
                                            JsonNode arrivalAt = arrival.get("at");
                                            JsonNode arrivalCarrier = arrival.get("iataCode");
                                            String arrivalTime = arrivalAt.asText();
                                            String[] parts = arrivalTime.split("T");
                                            String arrivalDate = parts[0];
                                            String arrTime = parts[1];
                                            System.out.println("Arriving at: " + arrivalCarrier.asText() + " On: " + arrivalDate + ", at time: " + arrTime);
                                            System.out.println();
                                            newSegment.setArrival_date(arrivalDate);
                                            newSegment.setArrival_time(arrTime);
                                            newSegment.setArrival_airport_code(arrivalCarrier.asText());
                                        }
                                        newSegment.setFlight_id(newFlight.getId());
                                        segmentRepository.save(newSegment);
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
            }
//            return "API Response: " + response;
            Iterable<Segment> segmentIterable = segmentRepository.findAll();
            List<Segment> segmentList = new ArrayList<>();

            for(Segment s : segmentIterable) {
                segmentList.add(s);
            }
            System.out.println("Segment List: " + segmentList);
            if(segmentList.isEmpty()) {
                return new SegmentDTO[0];
            } else {
                SegmentDTO[] allSegments = createSegmentsDTO(segmentList);
                return allSegments;
            }
        }
        return new SegmentDTO[0];
    }

    public SegmentDTO[] createSegmentsDTO(List<Segment> segmentList) {
        SegmentDTO[] result = new SegmentDTO[segmentList.size()];
        for(int i = 0; i < segmentList.size(); i++) {
            SegmentDTO dto = createSegment(segmentList.get(i));
            result[i] = dto;
        }
        return result;
    }

    public SegmentDTO createSegment(Segment s) {
        SegmentDTO dto = new SegmentDTO(s.getId(), s.getFlight_id(), s.getFlight_number(), s.getDeparture_airport_code(), s.getArrival_airport_code(),
        s.getDeparture_date(), s.getArrival_date(), s.getDeparture_time(), s.getArrival_time());
        return dto;
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