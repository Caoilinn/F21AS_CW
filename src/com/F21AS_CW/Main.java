package com.F21AS_CW;

import Controller.TravelController;
import Model.*;
import View.TravelGUI;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Timer;

public class Main {
    public static final int FLIGHT_UPDATE_TIME_OFFSET = 500;

    public static void main(String[] args) throws ParseException {
        TravelModel travelModel = new TravelModel();
        TravelGUI travelGUI = new TravelGUI(travelModel);
        travelModel.registerObserver(travelGUI);
        travelModel.addControlTowersObserver();

        for (Airport airport : travelModel.getAirports().values()) {
            Thread thread = new Thread(airport.getControlTower());
            thread.start();
        }

        travelGUI.guiCreate();
        TravelController travelController = new TravelController(travelModel, travelGUI);

    }


}
