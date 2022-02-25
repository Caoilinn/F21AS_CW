package com.F21AS_CW;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.lang.Math;

public class FlightPlan {
    private LinkedList<Airport> flightPlan;
    private static final int earthRadius = 6371;
    private int flightPlanTotalDistance = 0;

    public int getFlightPlanTotalDistance() {
        return flightPlanTotalDistance;
    }

    public FlightPlan(LinkedList<Airport> fp) {

        flightPlan = fp;

        //calculates total distance traveled along initial flightpath given
        if(fp.size() > 1) {
            for (int i = 0; i < flightPlan.size() - 1; i++) {
                GPSCoordinates gps1 = flightPlan.get(i).getControlTower().getGpsLocation();
                GPSCoordinates gps2 = flightPlan.get(i + 1).getControlTower().getGpsLocation();
                flightPlanTotalDistance += calcDistance(gps1, gps2);
            }
        }
    }

    public FlightPlan() {
        flightPlan = new LinkedList<Airport>();
    }

    public void addToPlan(Airport node) {
        Airport prevLast = null;
        if(flightPlan.size() > 0) {
            prevLast = flightPlan.getLast();
        }
        flightPlan.addLast(node);
        //adds new distance between previous final control tower and the new final to the total of the flightPlan
        if(flightPlan.size() > 1 && prevLast!=null) {
            flightPlanTotalDistance += calcDistance(node.getControlTower().getGpsLocation(), prevLast.getControlTower().getGpsLocation());
        }
    }

    public LinkedList<Airport> getFlightPlan() {
        return flightPlan;
    }

    public int calcDistance(GPSCoordinates coords1, GPSCoordinates coords2) {

       double rlat1 = ((double) coords1.getLatitude() * (3.14/180));

       double rlong1 = ((double) coords1.getLongitude() * (3.14/180));

        double rlat2 = ((double) coords2.getLatitude() * (3.14/180));

        double rlong2 = ((double) coords2.getLongitude() * (3.14/180));

        double deltaLat = rlat2 - rlat1;

        double deltaLong = rlong2 - rlong1;

        double trig = ((Math.pow(Math.sin(deltaLat/2),2))) + (Math.cos(rlat1)) * (Math.cos(rlat2)) * (Math.pow(Math.sin(deltaLong/2),2));

        double distance = 2 * earthRadius * (Math.asin(Math.sqrt(trig)));

        //returns int for ease of use with gui
        return (int) distance;
    }
}
