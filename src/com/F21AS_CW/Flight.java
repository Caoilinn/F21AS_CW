package com.F21AS_CW;

import java.sql.Time;
import java.sql.Date;

public class Flight {

    private String flightCode;
    private String planeCode;
    private Airport departure;
    private Airport destination;
    private FlightPlan flightPlan;

    public Flight(String flightCode, String planeCode, Airport departure, Airport destination, FlightPlan flightPlan) {
        this.flightCode = flightCode;
        this.planeCode = planeCode;
        this.departure = departure;
        this.destination = destination;
        this.flightPlan = flightPlan;

        if (destination == null || departure == null) {
            //Demo for the exception, would be better to throw if airports are not in the airports set
            throw new InvalidFlightException("Ths is a null airport");
        }
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public float Co2Emissions() {
        return 2.0f;
    }

    public float getDistance() {
        return 2.0f;
    }

    public float getFuelConsumption() {
        return 2.0f;
    }
}
