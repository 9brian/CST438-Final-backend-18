package com.cst438.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends CrudRepository <Flight, FlightId> {
    Flight findById(int flight_id, String carrier_name);

    List<Flight> findByLocation(String location);

    List<Flight> findByDestination(String destination);

    // Custom query to find flights within a price range
    @Query("SELECT f FROM Flight f WHERE f.price BETWEEN :minPrice AND :maxPrice")
    List<Flight> findFlightsInPriceRange(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice);
}