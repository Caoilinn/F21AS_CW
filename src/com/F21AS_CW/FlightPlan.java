package com.F21AS_CW;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.lang.Math;

public class FlightPlan {
    private LinkedList<ControlTower> flightPlan;
    private static final int earthRadius = 6371;
    private int flightPlanTotalDistance = 0;
    public FlightPlan(LinkedList<ControlTower> fp) {

        flightPlan = fp;

        //calculates total distance traveled along intial flightpath given
        for (int i = 0; i < flightPlan.size()-1; i++) {
            GPSCoordinates gps1 = flightPlan.get(i).getGpsLocation();
            GPSCoordinates gps2 = flightPlan.get(i+1).getGpsLocation();
            flightPlanTotalDistance += calcDistance(gps1,gps2);
        }
    }

    public void addToPlan(ControlTower node) {
        flightPlan.addLast(node);
        //adds new distance between previous final control tower and the new final to the total of the flightPlan
        flightPlanTotalDistance += calcDistance(node.getGpsLocation(),flightPlan.getLast().getGpsLocation());
    }

    public LinkedList<ControlTower> getFlightPlan() {
        return flightPlan;
    }

    public int calcDistance(GPSCoordinates coords1, GPSCoordinates coords2) {

       double rlat1 = ((double) coords1.getLatitude() * (3.14/180));
       double rlong1 = ((double) coords1.getLongitude() * (3.14/180));
        double rlat2 = ((double) coords2.getLatitude() * (3.14/180));
        double rlong2 = ((double) coords2.getLongitude() * (3.14/180));
        double deltaLat = rlat2 - rlat1;
        double deltaLong = rlong2 - rlong1;
        double trig = ((Math.sin(deltaLat))*(Math.sin(deltaLat))) + (Math.cos(rlat1)) * (Math.cos(rlat2)) * ((Math.sin(deltaLong))*(Math.sin(deltaLong)));
        double distance = 2 * earthRadius * (Math.asin(Math.sqrt(trig)));

        //returns int for ease of use with gui
        return (int) distance;
    }
}
