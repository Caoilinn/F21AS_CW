package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportFile implements IReadable {

    private ArrayList<Airline> airlines;

    public ReportFile() {
    }

    public void performCalculations() {

        //airlines = new ArrayList<Airline>(Airlines.getAirlines().values());
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
            if (fuelConsumption >= 0)
                airline.setAverageFuelConsumption(fuelConsumption / airline.flights.size());

            //Reset for next airline in set
            distance = 0;
            emissions = 0;
            fuelConsumption = 0;
        }
    }

    public boolean WriteToFile() {
        try {
            FileWriter fileWriter = new FileWriter("ReportFile");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Airline airline : airlines) {
                bufferedWriter.write(airline.getName() + "\n");
                bufferedWriter.write("Total number of flights: " + airline.flights.size() + "\n");
                bufferedWriter.write("Distance: " + airline.getTotalDistance() + "\n");
                bufferedWriter.write("Emissions: " + airline.getTotalEmissions() + "\n");
                bufferedWriter.write("Fuel Consumption: " + airline.getTotalFuelConsumption() + "\n");
                bufferedWriter.newLine();
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ReadFromFile() {
        return false;
    }

}
