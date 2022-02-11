package com.F21AS_CW;

import org.w3c.dom.Node;

import java.util.LinkedList;

public class FlightPlan {
    private LinkedList<ControlTower> flightPlan;
    public FlightPlan(LinkedList<ControlTower> fp) {
        flightPlan = fp;
    }

    public void addToPlan(ControlTower node) {
        flightPlan.addLast(node);
    }

    public LinkedList<ControlTower> getFlightPlan() {
        return flightPlan;
    }
}
