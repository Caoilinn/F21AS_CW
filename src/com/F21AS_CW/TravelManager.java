package com.F21AS_CW;

import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TravelManager {

    private static ArrayList<IWriteable> iWriteables;

    public void run() {
        Airlines airlines = new Airlines();
        Airports airports = new Airports();
        Aeroplanes aeroplanes = new Aeroplanes();
        Flights flights = new Flights();

        //Setup array list of IWriteable so that all the read and writes can be called
        iWriteables = new ArrayList<IWriteable>();
        iWriteables.add(airlines);
        iWriteables.add(airports);
        iWriteables.add(aeroplanes);
        iWriteables.add(flights);

        readFromFiles();
        ReportFile reportFile = new ReportFile();
        showTravelGUI();
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
