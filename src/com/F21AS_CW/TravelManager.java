package com.F21AS_CW;

public class TravelManager
{

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

    public void run()
    {
        // Call read methods for all relevant classes
            // airlinesList.readFromFile("AirlinesInput.csv");
            // flightsList.readFromFile("FlightsInput.csv");
            // airportsList.readFromFile("AirportsInput.csv");
            // aeroplanesList.readFromFile("AeroplaneInput.csv");
        // Call a single write method
            // objectName.writeToFile("output.txt", objectName.exitReport());
        showTravelGUI();

    }

    public void showTravelGUI()
    {
        // Create a new TravelGUI object and pass on the created lists

        // JFrame frame = new JFrame("FrameDemo");

        TravelGUI GUI = new TravelGUI();
        GUI.guiCreate();
    }
}
