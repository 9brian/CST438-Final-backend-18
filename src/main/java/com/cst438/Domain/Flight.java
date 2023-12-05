package com.cst438.Domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="flights_table")
//@IdClass(FlightId.class) // Indicate composite primary key usage
public class Flight {
    @Id
    int id;
    int flight_no;
    String carrier_name;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Segment> segments;
    double price;

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Flight() {
//        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(int flight_no) {
        this.flight_no = flight_no;
    }

    public String getCarrier_name() {
        return carrier_name;
    }

    public void setCarrier_name(String carrier_name) {
        this.carrier_name = carrier_name;
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
                "id=" + id +
                ", flight_no=" + flight_no +
                ", carrier_name='" + carrier_name + '\'' +
                ", price=" + price +
                '}';
    }
}
