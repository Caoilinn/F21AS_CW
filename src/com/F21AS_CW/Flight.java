package com.F21AS_CW;

import java.sql.Time;
import java.sql.Date;

public class Flight {

    private String flightCode;
    private Aeroplane plane;
    private Airport departure;
    private Airport destination;
    private Date date;
    private Time departureTime;
    private FlightPlan flightPlan;

    public Flight(String flightCode, Aeroplane plane, Airport departure, Airport destination, Date date, Time departureTime, FlightPlan flightPlan) {
        this.flightCode = flightCode;
        this.plane = plane;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.departureTime= departureTime;
        this.flightPlan = flightPlan;

        if (!Airports.CheckIfValExists(departure) || !Airports.CheckIfValExists(destination)) {
            throw new InvalidFlightException("This is a null airport");
        }
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Aeroplane getPlane() {
        return plane;
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

    public Time getDepartureTime() {
        return departureTime;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public int getCo2Emissions() {
        //using the numbers shown in coursework spec example of gui, i figured that for every 1 litre of fuel consumed 0.82kg of CO2 is emitted,
        // this isn't exact but it'll do for our purposes
        float emission = this.getFuelConsumption()*0.82f;
        //returns integer for ease of use with Gui
        return (int) emission;
    }

    public int getDistance() {
        return flightPlan.getFlightPlanTotalDistance();
    }

    public int getFuelConsumption() {

       float consumption = plane.getFuelConsumption()*(flightPlan.getFlightPlanTotalDistance()/100);
       //returns integer for ease of use with GUI
       return (int) consumption;
    }

    public float getDurationOfFlight() {
        return flightPlan.getFlightPlanTotalDistance()/plane.getCruiseSpeed();
    }
}
