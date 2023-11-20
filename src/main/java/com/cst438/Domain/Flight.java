package com.cst438.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.time.LocalDateTime;

@Entity
@Table(name="flight_table")
@IdClass(FlightId.class) // Indicate composite primary key usage
public class Flight {
    @Id
    int flight_id;
    @Id
    String carrier_name;
    LocalDateTime departure_time;
    LocalDateTime arrival_time;
    String location;
    String destination;
    double price;

    public Flight() {
        super();
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getCarrier_name() {
        return carrier_name;
    }

    public void setCarrier_name(String carrier_name) {
        this.carrier_name = carrier_name;
    }

    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalDateTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalDateTime departure_time) {
        this.departure_time = departure_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight_id=" + flight_id +
                ", carrier_name='" + carrier_name + '\'' +
                ", departure_time=" + departure_time +
                ", arrival_time=" + arrival_time +
                ", location='" + location + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                '}';
    }
}
