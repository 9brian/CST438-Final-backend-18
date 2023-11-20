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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FlightId getFlight() {
        return flight;
    }

    public void setFlight(FlightId flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledFlight that = (ScheduledFlight) o;
        return booking_number == that.booking_number && Objects.equals(user, that.user) && Objects.equals(flight, that.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking_number, user, flight);
    }
}
