package com.F21AS_CW;

import Controller.TravelController;
import Model.TravelModel;
import View.TravelGUI;

public class Main {

    public static void main(String[] args) {
        TravelModel travelModel = new TravelModel();
        TravelGUI travelGUI = new TravelGUI(travelModel);
        travelModel.registerObserver(travelGUI);
        travelGUI.guiCreate();
        TravelController travelController = new TravelController(travelModel, travelGUI);
    }
}
