package com.cst438.Domain;

import java.io.Serializable;
import java.util.Objects;

public class FlightId implements Serializable {
    private int flight_id;
    private String carrier_name;

    public FlightId() {
        // Default constructor
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
}
