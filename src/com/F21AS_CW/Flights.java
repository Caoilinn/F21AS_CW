package com.F21AS_CW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Flights implements IWriteable {

    private HashMap<String, Flight> flights;

    public Flights(HashMap<String, Flight> flights) {
        this.flights = flights;
    }

    public Flights() {

       /* try {
            this.flights = new HashMap<>();
            File flightsFile = new File("Flights");
            Scanner reader = new Scanner(flightsFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(";");

                //If the line doesn't contain the required fields then skip over the line
                if (fields.length < 1)
                    continue;

                String flightCode = fields[0];
                String planeCode = fields[1];
                String departure = fields[2];
                String destination = fields[3];
                String date = fields[4];
                String time = fields[5];
                String flightPlan = fields[6];

                //Flight flight = new Flight(flightCode,planeCode,departure,destination,date,time,flightPlan);
               // this.flights.put(flightCode,planeCode,departure,destination,date,time,flightPlan);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/

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
