package com.F21AS_CW;

import Controller.TravelController;
import Model.*;
import View.TravelGUI;

import java.util.LinkedList;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        TravelModel travelModel = new TravelModel();
        TravelGUI travelGUI = new TravelGUI(travelModel);
        travelModel.registerObserver(travelGUI);
        travelGUI.guiCreate();
        TravelController travelController = new TravelController(travelModel, travelGUI);

        travelModel.getFlights().get("AF670").printGPSLocation();
        travelModel.getFlights().get("AF670").updateGPSPosition();
        travelModel.getFlights().get("AF670").printGPSLocation();
    }
}
