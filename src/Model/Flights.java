package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Flights implements IWriteable {

    private static HashMap<String, Flight> flights;

    public Flights(HashMap<String, Flight> flights) {
        this.flights = flights;
    }

    public Flights() {
    }

    public static HashMap<String, Flight> getFlights() {
        return flights;
    }

    public Flight getFlight(String flightCode) {
        if (this.flights.containsKey(flightCode)) {
            return flights.get(flightCode);
        } else return null;
    }

    public void AddFlight(Flight flight) {
        if (flight == null)
            throw new IllegalArgumentException("This is not a valid flight");
        else {
            //If flight code is null then there isn't a valid key
            if (flight.getFlightCode() != null) {
                this.flights.put(flight.getFlightCode(), flight);
            } else
                throw new IllegalArgumentException("This flight doesn't have a valid Flight Code");
        }

    }

    @Override
    public boolean WriteToFile() {
        return false;
    }

    @Override
    public boolean ReadFromFile() {
        try {
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
                Airline airline = Airlines.getAirlines().get(flightCode.substring(0, 2));

                //this need to be used to get an Aeroplane object from the Aeroplanes collection
                String planeCode = fields[1];

                Aeroplane plane = Aeroplanes.getAeroplanes().get(planeCode.trim());
                String dep = fields[2];
                Airport departure = Airports.getAirports().get(dep.replaceAll("\\s", ""));
                String dest = fields[3];
                Airport destination = Airports.getAirports().get(dest.replaceAll("\\s", ""));
                String date = fields[4];
                String time = fields[5];

                LinkedList<Airport> plan = new LinkedList<Airport>();
                for (int x = 6; x < fields.length; x++) {
                    Airport temp = Airports.getAirports().get(fields[x].trim());
                    plan.addLast(temp);
                }

                FlightPlan flightPlan = new FlightPlan(plan);


                Flight flight = new Flight(flightCode, plane, departure, destination, date, time, flightPlan, airline);
                this.flights.put(flightCode, flight);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }
}
