package com.F21AS_CW;

import java.util.ArrayList;
import java.util.HashSet;

public class ReportFile implements IWriteable {

    Airlines airlines;
    Flights flights;

    public ReportFile(Airlines airlines, Flights flights) {
        this.airlines = airlines;
        this.flights = flights;
    }

    public void performCalculations() {

        ArrayList<Airline> airlines = new ArrayList<Airline>(this.airlines.getAirlines().values());
        double distance = 0, emissions = 0, fuelConsumption = 0;

        //Iterate through every airline that exists
        for (Airline airline : airlines) {
            //For every airline loop through its flights
            for (Flight flight : airline.flights) {

                //Get the flight's individual values for distance, emissions and fuel consumption then total these
                distance += flight.getDistance();
                emissions += flight.getCo2Emissions();
                fuelConsumption += flight.getFuelConsumption();
            }

            //Set the airline's total distance travelled, emissions and average fuel consumption
            airline.setTotalDistance(distance);
            airline.setTotalEmissions(emissions);
            airline.setAverageFuelConsumption(fuelConsumption / airline.flights.size());

            //Reset for next airline in set
            distance = 0;
            emissions = 0;
            fuelConsumption = 0;
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
