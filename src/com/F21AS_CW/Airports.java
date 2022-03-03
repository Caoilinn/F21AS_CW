package com.F21AS_CW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Airports implements IWriteable {

    private static HashMap<String, Airport> airports;


    // public Airports(HashMap<String, Airport> airports) {this.airports = airports;}


    public Airports() {
    }

    public static HashMap<String, Airport> getAirports() {
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
        System.out.println("Airports write to file");
        return false;
    }

    @Override
    public boolean ReadFromFile() {

        System.out.println("Airports read from file");
        try {
            this.airports = new HashMap<>();
            File airportsFile = new File("Airports");
            Scanner reader = new Scanner(airportsFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(";");

                //If the line doesn't contain the required fields then skip over the line
                //     if (fields.length < 1)
                //        continue;

                String airportCode = fields[0];
                String airportName = fields[1];
                String l0ngitude = fields[2];
                String latitude = fields[3];

                String[] split1 = l0ngitude.split("°");
                Double degrees1 = Double.parseDouble(split1[0]);

                String[] split2 = split1[1].split("'");
                Double minutes1 = (Double.parseDouble(split2[0])) / 60;

                String[] split3 = split2[1].split("\"");
                Double seconds1 = (Double.parseDouble(split3[0])) / 3600;
                Boolean north = split3[1].equals("N");

                String[] split4 = latitude.split("°");
                Double degrees2 = Double.parseDouble(split4[0]);

                String[] split5 = split4[1].split("'");
                Double minutes2 = (Double.parseDouble(split5[0])) / 60;

                String[] split6 = split5[1].split("\"");
                Double seconds2 = (Double.parseDouble(split6[0])) / 3600;
                Boolean west = split6[1].equals("W");

                double DD_longitude = degrees1 + minutes1 + seconds1;
                double DD_latitude = degrees2 + minutes2 + seconds2;

                if (!north) {
                    DD_longitude *= -1;
                }

                if (!west) {
                    DD_latitude *= -1;
                }


                GPSCoordinates gps = new GPSCoordinates(DD_longitude, DD_latitude);
                ControlTower ct = new ControlTower(gps);

                Airport airport = new Airport(airportCode, airportName, ct);

                // Airport airport = new Airport(airportCode,airportName);
                this.airports.put(airportCode, airport);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void ProcessLine() {

    }
}