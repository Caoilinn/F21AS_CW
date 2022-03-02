package com.F21AS_CW;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Airports implements IWriteable{

    private static HashMap<String, Airport> airports;

   // public Airports(HashMap<String, Airport> airports) {this.airports = airports;}

    public Airports() {

        try {
            this.airports = new HashMap<>();
            File airportsFile = new File("Airports");
            Scanner reader = new Scanner(airportsFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(";");

                //If the line doesn't contain the required fields then skip over the line
                if (fields.length < 1)
                    continue;

                String airportCode = fields[0];
                String airportName = fields[1];

                Airport airport = new Airport(airportCode,airportName);
                this.airports.put(airportCode,airport);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public HashMap<String, Airport> getAirports() {
        return airports;
    }

    public static boolean CheckIfValExists(Airport airport) {
        if (airports.containsValue(airport))
            return true;
        else
            return false;
    }

    public static boolean CheckIfKeyExists(String airportCode) {
        if (airports.containsKey(airportCode))
            return true;
        else
            return false;
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
