package com.cst438.Domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends CrudRepository <Flight, Integer> {
    Flight findById(int id);

    // Custom query to find flights within a price range
    @Query("SELECT f FROM Flight f WHERE f.price BETWEEN :minPrice AND :maxPrice")
    List<Flight> findFlightsInPriceRange(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice);
}