package com.cst438.Domain;
import java.time.LocalDateTime;


public record FlightDTO(int flight_id, String carrier_name, LocalDateTime departure_time,
                        LocalDateTime arrival_time, String location, String destination,
                        double price)  {

}