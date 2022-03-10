package com.F21AS_CW;

import Model.*;
import View.TravelGUI;

import java.util.ArrayList;

public class TravelManager {

    private static ArrayList<IWriteable> iWriteables;

    public void run() {
        Airlines airlines = new Airlines();
        Airports airports = new Airports();
        Aeroplanes aeroplanes = new Aeroplanes();
        Flights flights = new Flights();
        ReportFile reportFile = new ReportFile();


        //Setup array list of IWriteable so that all the read and writes can be called
        iWriteables = new ArrayList<IWriteable>();
        iWriteables.add(airlines);
        iWriteables.add(airports);
        iWriteables.add(aeroplanes);
        iWriteables.add(flights);
        iWriteables.add(reportFile);

        readFromFiles();
        showTravelGUI();
        reportFile.performCalculations();
    }

    public void showTravelGUI() {
        TravelGUI GUI = new TravelGUI();
        GUI.guiCreate();
    }

    public static void readFromFiles() {
        for (IWriteable iWriteable : iWriteables)
            iWriteable.ReadFromFile();
    }

    public static void writeToFiles() {
        for (IWriteable iWriteable : iWriteables)
            iWriteable.WriteToFile();
    }
}
