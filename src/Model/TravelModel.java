package Model;

import View.IObserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TravelModel implements ISubject {

    private ArrayList<IObserver> observers = new java.util.ArrayList<>();
    private static ArrayList<IReadable> iReadables;

    Airlines airlines;
    Airports airports;
    Aeroplanes aeroplanes;
    Flights flights;
    ReportFile reportFile;

    public TravelModel() {
        this.airlines = new Airlines();
        this.airports = new Airports();
        this.aeroplanes = new Aeroplanes();
        this.flights = new Flights(this.aeroplanes, this.airlines, this.airports);
        this.reportFile = new ReportFile();


        //Setup array list of IWriteable so that all the read and writes can be called
        iReadables = new ArrayList<IReadable>();
        iReadables.add(airlines);
        iReadables.add(airports);
        iReadables.add(aeroplanes);
        iReadables.add(flights);
        iReadables.add(reportFile);

        readFromFiles();

    }

    public void addFlight(Flight flight) {
        this.flights.AddFlight(flight);
        notifyObservers();
    }

    public static void readFromFiles() {
        for (IReadable iReadable : iReadables)
            iReadable.ReadFromFile();
    }

    public HashMap<String, Airline> getAirlines() {
        return this.airlines.getAirlines();
    }

    public HashMap<String, Aeroplane> getAeroplanes() {
        return this.aeroplanes.getAeroplanes();
    }

    public HashMap<String, Airport> getAirports() {
        return this.airports.getAirports();
    }

    public HashMap<String, Flight> getFlights() {
        return this.flights.getFlights();
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
        for (IObserver obs : this.observers)
            obs.update();
    }

    public void generateReportData() {
        double distance = 0, emissions = 0, fuelConsumption = 0;

        //Iterate through every airline that exists
        for (Airline airline : getAirlines().values()) {
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
            if (fuelConsumption >= 0)
                airline.setAverageFuelConsumption(fuelConsumption / airline.flights.size());

            //Reset for next airline in set
            distance = 0;
            emissions = 0;
            fuelConsumption = 0;
        }
    }

    public void writeToFile() {
        try {
            generateReportData();

            FileWriter fileWriter = new FileWriter("ReportFile");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Airline airline : getAirlines().values()) {
                bufferedWriter.write(airline.getName() + "\n");
                bufferedWriter.write("Total number of flights: " + airline.flights.size() + "\n");
                bufferedWriter.write("Distance: " + airline.getTotalDistance() + "\n");
                bufferedWriter.write("Emissions: " + airline.getTotalEmissions() + "\n");
                bufferedWriter.write("Fuel Consumption: " + airline.getTotalFuelConsumption() + "\n");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
