package com.cst438.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ScheduledFlight {

    @Id
    @GeneratedValue
    private int booking_number;



}
