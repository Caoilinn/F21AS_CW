package com.F21AS_CW;

import java.util.HashMap;

public class Airports implements IWriteable{

    private static HashMap<String, Airport> airports;

    public Airports(HashMap<String, Airport> airports) {
        this.airports = airports;
    }

    //Empty constructor so an empty object can be created
    public Airports() {

    }

    public HashMap<String, Airport> getAirports() {
        return airports;
    }

    public static boolean CheckIfValExists(Airport airport) {
        if (airports.containsValue(airport))
            return true;
        else
            return false;
    }

    public static boolean CheckIfKeyExists(String airportCode) {
        if (airports.containsKey(airportCode))
            return true;
        else
            return false;
    }

    @Override
    public boolean WriteToFile() {
        return false;
    }

    @Override
    public boolean ReadFromFile() {
        return false;
    }

    @Override
    public void ProcessLine() {

    }
}
