package com.F21AS_CW;

import java.util.ArrayList;
import java.util.Objects;

public class Airline {
    private String name;
    private String code;
    public ArrayList<Flight> flights;
    private double totalDistance, totalFuelConsumption, totalEmissions;

    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return Objects.equals(name, airline.name) && Objects.equals(code, airline.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setAverageFuelConsumption(double totalFuelConsumption) {
        this.totalFuelConsumption = totalFuelConsumption;
    }

    public void setTotalEmissions(double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }
}
