package com.cst438.controller;

import com.cst438.Domain.Flight;
import com.cst438.Domain.FlightDTO;
import com.cst438.Domain.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class FlightController {
    @Autowired
    FlightRepository flightRepository;

    /* will return a list of ALL flights */
    @GetMapping("/flights")
    public FlightDTO[] getAllFlights() {
        //get ALL flights & create empty List
        Iterable<Flight> flightsIterable = flightRepository.findAll();
        List<Flight> flightList = new ArrayList<>();

        //add ALL flights into the list
        for(Flight f : flightsIterable) {
            flightList.add(f);
        }

        if(flightList.isEmpty()) {
            return new FlightDTO[0];
        } else {
            FlightDTO[] allFlights = createFlightDTO(flightList);
            return allFlights;
        }
    }

    // creates FlightDTO[] using the list, and using a method to insert each flight
    public FlightDTO[] createFlightDTO(List<Flight> flightList) {
        FlightDTO[] result = new FlightDTO[flightList.size()];
        for(int i = 0; i < flightList.size(); i++) {
            FlightDTO dto = createFlightDTO(flightList.get(i));
            result[i] = dto;
        }
        return result;
    }

    // method to insert each flight
    public FlightDTO createFlightDTO(Flight flight) {
        return new FlightDTO(
                flight.getId(),
                flight.getFlight_no(),
                flight.getCarrier_name(),
                flight.getDeparture_date(),
                flight.getDeparture_time(),
                flight.getArrival_date(),
                flight.getArrival_time(),
                flight.getLocation(),
                flight.getDestination(),
                flight.getPrice()
        );
    }
}
