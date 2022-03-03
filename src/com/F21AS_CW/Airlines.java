package com.F21AS_CW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Airlines implements IWriteable {

    private static HashMap<String, Airline> airlines;

    //  public Airlines(HashSet<String> airlines) {this.airlines = airlines;}

    public Airlines() {
    }

    public static HashMap<String, Airline> getAirlines() {
        return airlines;
    }

    @Override
    public boolean WriteToFile() {
        return false;
    }

    @Override
    public boolean ReadFromFile() {
        try {
            this.airlines = new HashMap<String, Airline>();
            File airlinesFile = new File("Airlines");
            Scanner reader = new Scanner(airlinesFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(";");

                //If the line doesn't contain a name and code then skip over the line
                if (fields.length < 2)
                    continue;

                String airlineCode = fields[0];
                String airlineName = fields[1];

                Airline airline = new Airline(airlineName, airlineCode);
                this.airlines.put(airlineCode, airline);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

}
