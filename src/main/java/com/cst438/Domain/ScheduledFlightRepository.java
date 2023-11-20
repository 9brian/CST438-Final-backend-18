package com.cst438.Domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ScheduledFlightRepository extends CrudRepository <ScheduledFlight, Integer>{

    // Find ScheduledFlight entities by email
    List<ScheduledFlight> findByUserEmail(String email);

    // Find ScheduledFlight entities by flight_no
    List<ScheduledFlight> findByFlightFlightNo(int flightNo);

    @Query("SELECT sf FROM ScheduledFlight sf WHERE sf.booking_number = :bookingNumber")
    ScheduledFlight findByBookingNumberCustom(@Param("bookingNumber") int bookingNumber);

}
