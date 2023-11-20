package com.cst438.Domain;


import com.amadeus.resources.DatedFlight;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="scheduled_flights_table")
public class ScheduledFlight {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int booking_number;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;


    public int getBooking_number() {
        return booking_number;
    }

    public void setBooking_number(int booking_number) {
        this.booking_number = booking_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
