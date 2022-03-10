package Model;

import java.util.LinkedList;
import java.lang.Math;

public class FlightPlan {
    private LinkedList<Airport> flightPlan;
    private static final int earthRadius = 6379;
    private int flightPlanTotalDistance = 0;

    @Override
    public String toString() {
        String plan = "";
        for (Airport airport : flightPlan)
            plan += airport.getName() + " ";
        return plan;
    }

    public int getFlightPlanTotalDistance() {
        return flightPlanTotalDistance;
    }

    //Constructor for use with  already made flightPlans, allows flightPlans to be created from a file more easily
    public FlightPlan(LinkedList<Airport> fp) {

        flightPlan = fp;
        //calculates total distance traveled along initial flightpath given by

        if (flightPlan.size() > 1) {
            for (int i = 0; i < flightPlan.size() - 1; i++) {
                GPSCoordinates gps1 = flightPlan.get(i).getControlTower().getGpsLocation();
                GPSCoordinates gps2 = flightPlan.get(i + 1).getControlTower().getGpsLocation();
                flightPlanTotalDistance += calcDistance(gps1, gps2);
            }
        }
    }

    public FlightPlan() {
        this.flightPlan = new LinkedList<Airport>();
    }

    public void addToPlan(Airport node) {
        //flightPlan cant be bigger than size 8 so we check for this
        if (flightPlan.size() >= 8) {
            System.out.println("Plan is full");
            return;
        }
        Airport prevLast = null;
        // if there are nodes in the flightPlan, the last one becomes the previous last, if not prevLast stays null and we wont do a calculation
        if (flightPlan.size() > 0) {
            prevLast = flightPlan.getLast();
        }
        flightPlan.addLast(node);

        //checks if flightPlan has more than one node in it
        if (flightPlan.size() > 1 && prevLast != null) {
            //adds new distance between previous final control tower and the new final to the total of the flightPlan
            flightPlanTotalDistance += calcDistance(node.getControlTower().getGpsLocation(), prevLast.getControlTower().getGpsLocation());
        }
    }

    public LinkedList<Airport> getFlightPlan() {
        return flightPlan;
    }

    //implements pseudo-code example given in coursework spec, gives results that are approximately correct (Checked by hand using a calculator)
    public int calcDistance(GPSCoordinates coords1, GPSCoordinates coords2) {
        double rlat1 = coords1.getLatitude() * (Math.PI / 180);

        double rlong1 = coords1.getLongitude() * (Math.PI / 180);

        double rlat2 = coords2.getLatitude() * (Math.PI / 180);

        double rlong2 = coords2.getLongitude() * (Math.PI / 180);

        double deltaLat = rlat2 - rlat1;

        double deltaLong = rlong2 - rlong1;

        double trig = ((Math.pow(Math.sin(deltaLat / 2), 2))) + (Math.cos(rlat1)) * (Math.cos(rlat2)) * (Math.pow(Math.sin(deltaLong / 2), 2));

        double distance = 2 * earthRadius * (Math.asin(Math.sqrt(trig)));

        //returns int for ease of use with gui
        return (int) distance;
    }
}
