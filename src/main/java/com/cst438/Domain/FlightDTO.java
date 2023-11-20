package com.cst438.Domain;
import java.time.LocalDateTime;


public record FlightDTO(int id, int flight_no, String carrier_name, Date departure_date, Time departure_time,
                        Date arrival_date, Time arrival_time, String location, String destination,
                        double price)  {

}