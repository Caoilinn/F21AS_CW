package com.F21AS_CW;

import java.sql.Time;
import java.sql.Date;

public class Flight {

    private String flightCode;
    private String planeCode;
    private Airport departure;
    private Airport destination;
    private Date date;
    private Time time;
    private FlightPlan flightPlan;

    public Flight(String flightCode, String planeCode, Airport departure, Airport destination, Date date, Time time, FlightPlan flightPlan) {
        this.flightCode = flightCode;
        this.planeCode = planeCode;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.flightPlan = flightPlan;

        if (!Airports.CheckIfValExists(departure) || !Airports.CheckIfValExists(destination)) {
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

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
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
