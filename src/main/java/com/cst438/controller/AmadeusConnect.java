package com.cst438.controller;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;

enum AmadeusConnect {
    INSANCE;
    private Amadeus amadeus;
    private AmadeusConnect(){
        this.amadeus = Amadeus
                .builder("APi key", "Api secret")
                .build();
    }
    public Location[] location(String keyword) throws Exception{
        return amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subtype", Locations.AIRPORT));
    }
}