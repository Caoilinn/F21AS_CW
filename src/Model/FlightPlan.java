package Model;

import View.IObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;

public class FlightPlan implements ISubject {
    private LinkedList<Airport> flightPlan;
    private double flightPlanTotalDistance = 0.0;
    private ArrayList<IObserver> observers = new ArrayList<>();

    @Override
    public String toString() {
        String plan = "";
        for (Airport airport : flightPlan)
            plan += airport.getName() + " ";
        return plan;
    }

    public double getFlightPlanTotalDistance() {
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
                flightPlanTotalDistance += GPSCoordinates.calcDistance(gps1, gps2);
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
            flightPlanTotalDistance += GPSCoordinates.calcDistance(node.getControlTower().getGpsLocation(), prevLast.getControlTower().getGpsLocation());
        }
    }

    public LinkedList<Airport> getFlightPlan() {
        return flightPlan;
    }


    @Override
    public void registerObserver(IObserver obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : observers)
            obs.update();
    }
}
