package com.F21AS_CW;

import java.util.HashMap;

public class Flights implements IWriteable {

    private HashMap<String, Flight> flights;

    public Flights(HashMap<String, Flight> flights) {
        this.flights = flights;
    }

    public Flights() {

    }

    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    public void AddFlight(Flight flight) {
        if (flight == null)
            throw new IllegalArgumentException("This is not a valid flight");
        else {
            //If flight code is null then there isn't a valid key
            if(flight.getFlightCode() != null)
                this.flights.put(flight.getFlightCode(), flight);
            else
                throw new IllegalArgumentException("This flight doesn't have a valid Flight Code");
        }

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
