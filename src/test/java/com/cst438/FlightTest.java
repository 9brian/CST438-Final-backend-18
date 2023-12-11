package com.cst438;

import com.cst438.Domain.Flight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {
    @Test
    public void TestFLightGetAndSet(){
        Flight flight = new Flight();

        flight.setId(1);
        flight.setFlight_no(123);
        flight.setCarrier_name("testcarrier");
        flight.setPrice(100.00);

        assertEquals(1,flight.getId());
        assertEquals(123,flight.getFlight_no());
        assertEquals("testcarrier", flight.getCarrier_name());
        assertEquals(100.00, flight.getPrice(), 0.001);
    }

    @Test
    public void testFlightToString(){
        Flight flight = new Flight();
        flight.setId(1);
        flight.setFlight_no(123);
        flight.setCarrier_name("testcarrier");
        flight.setPrice(100.00);

        // Check the string representation
        String expectedToString = "Flight{id=1, flight_no=123, carrier_name='testcarrier', price=100.0}";
        assertEquals(expectedToString, flight.toString());
    }
    }
