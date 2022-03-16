package Model;

import View.IObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Airports implements IReadable, ISubject {

    private HashMap<String, Airport> airports;
    private ArrayList<IObserver> observers = new ArrayList<>();

    // public Airports(HashMap<String, Airport> airports) {this.airports = airports;}


    public Airports() {
    }

    public HashMap<String, Airport> getAirports() {
        return airports;
    }

    public boolean CheckIfValExists(Airport airport) {
        if (airports.containsValue(airport))
            return true;
        else
            return false;
    }

    public boolean CheckIfKeyExists(String airportCode) {
        if (airports.containsKey(airportCode))
            return true;
        else
            return false;
    }

    @Override
    public boolean ReadFromFile() {

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
                String latitude = fields[2];
                String l0ngitude = fields[3];


                String[] split1 = latitude.split("°");

                Double degrees1 = Double.parseDouble(split1[0]);

                String[] split2 = split1[1].split("'");
                Double minutes1 = (Double.parseDouble(split2[0])) / 60;

                String[] split3 = split2[1].split("\"");
                Double seconds1 = (Double.parseDouble(split3[0])) / 3600;
                Boolean north = split3[1].equals("N");


                String[] split4 = l0ngitude.split("°");
                Double degrees2 = Double.parseDouble(split4[0]);

                String[] split5 = split4[1].split("'");
                Double minutes2 = (Double.parseDouble(split5[0])) / 60;

                String[] split6 = split5[1].split("\"");
                Double seconds2 = (Double.parseDouble(split6[0])) / 3600;
                Boolean west = split6[1].equals("W");


                double DD_latitude = degrees1 + minutes1 + seconds1;
                double DD_longitude = degrees2 + minutes2 + seconds2;

                //for testing purposes we keep our formatting in line with https://www.movable-type.co.uk/scripts/latlong.html

                //check if latitude is north or south, invert the number if south
                if (!north) {
                    DD_latitude *= -1;

                }
                //check if longitude is east or west, invert the number if west
                if (west) {
                    DD_longitude *= -1;
                }


                GPSCoordinates gps = new GPSCoordinates(DD_latitude, DD_longitude);
                ControlTower ct = new ControlTower(gps, airportCode);

                Airport airport = new Airport(airportCode, airportName, ct);

                this.airports.put(airportCode, airport);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public void startThreads() {
        for (Airport airport : this.airports.values()) {
            Thread thread = new Thread(airport.getControlTower());
            thread.start();
        }
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