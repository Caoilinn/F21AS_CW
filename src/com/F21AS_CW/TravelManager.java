package com.F21AS_CW;

import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class TravelManager {

    // Instance variables
    // private Airlines airlinesList;
    // private Flights flightsList;
    // private Airports airportsList;
    // private Airplane aeroplanesList;

    // Constructor
    //  public TravelManager()
    //  {
    // Initialise all lists
    // airlinesList = new Airlines();
    // flightsList = new Flights();
    // airportsList = new Airports();
    // aeroplanesList = new Aeroplane();
    //  }

    public void run() {
   /*             try {
                    HashSet<String> airlinesList = new HashSet<String>();
                    File airlines = new File("Airlines");
                    Scanner Reader = new Scanner(airlines);
                    while (Reader.hasNextLine()) {
                        String airline = Reader.nextLine();
                        //String line = Reader.nextLine();
                       // String [] fields = line.split(";");
                       // String airlineCode = fields[0];
                        //String airline = fields[1];
                        airlinesList.add(airline);
                        //System.out.println(airline);
                    }
                    System.out.println(airlinesList);
                    Reader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

*/


        // Call read methods for all relevant classes
        // airlinesList.readFromFile("AirlinesInput.csv");
        // flightsList.readFromFile("FlightsInput.csv");
        // airportsList.readFromFile("AirportsInput.csv");
        // aeroplanesList.readFromFile("AeroplaneInput.csv");
        // Call a single write method
        // objectName.writeToFile("output.txt", objectName.exitReport());
        Airlines airlines = new Airlines();
        Airports airports = new Airports();
        Aeroplanes aeroplanes = new Aeroplanes();
        Flights flights = new Flights();
        ReportFile reportFile = new ReportFile();
        showTravelGUI();
        reportFile.performCalculations();

    }

    public void showTravelGUI() {
        TravelGUI GUI = new TravelGUI();
        GUI.guiCreate();

    }
}
