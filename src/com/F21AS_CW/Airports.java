package com.F21AS_CW;

import java.util.HashMap;

public class Airports {

    private HashMap<String, Airport> airports;

    public Airports(HashMap<String, Airport> airports) {
        this.airports = airports;
    }

    //Empty constructor so an empty object can be created
    public Airports() {

    }

    public HashMap<String, Airport> getAirports() {
        return airports;
    }

    public boolean CheckIfValExists(Airport airport) {
        if (this.airports.containsValue(airport))
            return true;
        else
            return false;
    }

    public boolean CheckIfKeyExists(String airportCode) {
        if (this.airports.containsKey(airportCode))
            return true;
        else
            return false;
    }
}
