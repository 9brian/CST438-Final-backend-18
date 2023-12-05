package com.cst438.Domain;
import java.sql.Time;
import java.util.Date;
import java.util.List;


public record FlightDTO(int id, int flight_no, String carrier_name, List<Segment> segments, double price) {

}