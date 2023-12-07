package com.cst438.Domain;

public record SegmentDTO(int id,
                         int flightId,
                         int flightNumber,
                         String departureAirportCode,
                         String arrivalAirportCode,
                         String departureDate,
                         String arrivalDate,
                         String departureTime,
                         String arrivalTime) {
}
