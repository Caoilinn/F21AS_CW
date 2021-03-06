package Model;

import View.IObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Flights implements IReadable, ISubject {

    private HashMap<String, Flight> flights;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private Aeroplanes aeroplanes;
    private Airlines airlines;
    private Airports airports;

    public Flights(HashMap<String, Flight> flights) {
        this.flights = flights;
    }

    public Flights(Aeroplanes aeroplanes, Airlines airlines, Airports airports) {
        this.aeroplanes = aeroplanes;
        this.airlines = airlines;
        this.airports = airports;
    }

    public HashMap<String, Flight> getFlights() {
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
    public boolean ReadFromFile() {
        try {
            this.flights = new HashMap<>();
            InputStream data = getClass().getResourceAsStream("/files/Flights.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(data));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                //If the line doesn't contain the required fields then skip over the line
                if (fields.length < 1)
                    continue;

                String flightCode = fields[0];
                Airline airline = this.airlines.getAirlines().get(flightCode.substring(0, 2));

                //this need to be used to get an Aeroplane object from the Aeroplanes collection
                String planeCode = fields[1];

                Aeroplane plane = this.aeroplanes.getAeroplanes().get(planeCode.trim());
                String dep = fields[2];
                Airport departure = this.airports.getAirports().get(dep.replaceAll("\\s", ""));
                String dest = fields[3];
                Airport destination = this.airports.getAirports().get(dest.replaceAll("\\s", ""));
                String date = fields[4];
                String time = fields[5];

                LinkedList<Airport> plan = new LinkedList<Airport>();
                for (int x = 6; x < fields.length; x++) {
                    Airport temp = this.airports.getAirports().get(fields[x].trim());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void registerObserver(IObserver obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : observers)
            obs.update();
    }
}
