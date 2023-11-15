package com.cst438.Domain;
import java.time.LocalDateTime;


public record FlightDTO(int flight_id, String carrier_name, LocalDateTime departure_time,
                        LocalDateTime arrival_time, String location, String destination,
                        double price) {

}
//    CREATE TABLE flights_table (
//        flight_id       INT,
//        carrier_name    VARCHAR(100),
//    departure_time  DATETIME NOT NULL,
//    arrival_time    DATETIME NOT NULL,
//    location        VARCHAR(100) NOT NULL,
//    destination     VARCHAR(100) NOT NULL,
//    price           DECIMAL(10,2) NOT NULL,
//    PRIMARY KEY (flight_id, carrier_name)
//);