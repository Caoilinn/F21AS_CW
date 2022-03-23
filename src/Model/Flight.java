package Model;

import View.IObserver;
import com.F21AS_CW.Main;

import java.util.ArrayList;
import java.util.ListIterator;

public class Flight implements ISubject, Runnable {

    private String flightCode;
    private Aeroplane plane;
    private Airport departure;
    private Airport destination;
    private String date;
    private String departureTime;
    private FlightPlan flightPlan;
    private Airline airline;
    private GPSCoordinates gpsCoordinates;
    private ControlTower currentControlTower;
    private ControlTower nextCT;
    private boolean flightLanded;
    public int listCounter = 1;
    private int updateCounter = 1;
    private ArrayList<IObserver> observers = new ArrayList<>();

    @Override
    public String toString() {
        return "Flight{" +
                "flightCode='" + flightCode + '\'' +
                ", plane=" + plane +
                ", departure=" + departure +
                ", destination=" + destination +
                ", date='" + date + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", flightPlan=" + flightPlan +
                ", airline=" + airline +
                '}';
    }

    public Flight(String flightCode, Aeroplane plane, Airport departure, Airport destination, String date, String departureTime, FlightPlan flightPlan, Airline airline) {
        this.flightCode = flightCode;
        this.plane = plane;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.flightPlan = flightPlan;
        this.airline = airline;
        //Initial GPS location of a flight is that of its origin control tower
        this.gpsCoordinates = departure.getControlTower().getGpsLocation();
        this.nextCT = flightPlan.getFlightPlan().get(1).getControlTower();

        //Adds this flight to its departure airport's control tower
        this.currentControlTower = this.departure.getControlTower();
        this.currentControlTower.addFlight(this);
        addAirline();
        this.flightLanded = false;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Aeroplane getPlane() {
        return plane;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public String getGPSCoordinates() {
        return this.gpsCoordinates.getLatitude() + " " + this.gpsCoordinates.getLongitude();
    }

    public int getCo2Emissions() {
        //using the numbers shown in coursework spec example of gui, i figured that for every 1 litre of fuel consumed 0.82kg of CO2 is emitted,
        // this isn't exact but it'll do for our purposes
        float emission = this.getFuelConsumption() * 0.82f;
        //returns integer for ease of use with Gui
        return (int) emission;
    }

    public double getDistance() {
        return flightPlan.getFlightPlanTotalDistance();
    }

    public int getFuelConsumption() {

        float consumption = plane.getFuelConsumption() * ((float) flightPlan.getFlightPlanTotalDistance() / 100.0f);
        //returns integer for ease of use with GUI
        return (int) consumption;
    }

    public boolean getStatus() {
        return this.flightLanded;
    }

    public void addAirline() {
        airline.addFlight(this);
    }

    public String getDurationOfFlight() {
        double time = flightPlan.getFlightPlanTotalDistance() / plane.getCruiseSpeed();
        int hour = (int) time;
        int minutes = (int) (60 * (time - hour));
        String time_Duration = hour + ":" + minutes;
        return time_Duration;
    }

    public double getCurrentDistance() {
        return this.updateCounter * (this.plane.getCruiseSpeed() * 0.25);
    }

    public synchronized void updateGPSPosition() {
        this.updateCounter++;
        double rLatCurrent = Math.toRadians(this.gpsCoordinates.getLatitude());
        double rLongCurrent = Math.toRadians(this.gpsCoordinates.getLongitude());
        double rLatNext = 0.0;
        double rLongNext = 0.0;
        if (nextCT != null) {
            rLatNext = Math.toRadians(nextCT.getGpsLocation().getLatitude());
            rLongNext = Math.toRadians(nextCT.getGpsLocation().getLongitude());
        } else {
            rLatNext = Math.toRadians(currentControlTower.getGpsLocation().getLatitude());
            rLongNext = Math.toRadians(currentControlTower.getGpsLocation().getLatitude());
        }


        double deltaLong = rLongCurrent - rLongNext;

        double bearing = Math.atan2(Math.sin(deltaLong) * Math.cos(rLatNext), Math.cos(rLatCurrent) * Math.sin(rLatNext) - Math.sin(rLatCurrent) * Math.cos(rLatNext) * Math.cos(deltaLong));
        bearing = bearing * -1.0;
        // Every update is 15min sim time. Speed = kmph.
        double linearDistance = this.plane.getCruiseSpeed() / 0.25;
        double angularDistance = linearDistance / GPSCoordinates.EARTH_RADIUS;

        double latNew = Math.asin((Math.sin(rLatCurrent) * Math.cos(angularDistance)) + (Math.cos(rLatCurrent) * Math.sin(angularDistance) * Math.cos(bearing)));
        double longNew = rLongCurrent + Math.atan2(Math.sin(bearing) * Math.sin(angularDistance) * Math.cos(rLatCurrent), Math.cos(angularDistance) - Math.sin(rLatCurrent) * Math.sin(latNew));

        this.gpsCoordinates = new GPSCoordinates(Math.toDegrees(latNew), Math.toDegrees(longNew));
        if (this.flightCode.equals("BA664"))
            System.out.println("------Position update------");
    }

    //The new control tower should set itself as the flight's current control tower
    public synchronized void updateControlTower() {
        this.listCounter++;
        ControlTower temp = nextCT;
        currentControlTower = nextCT;
        if (listCounter < flightPlan.getFlightPlan().size())
            nextCT = flightPlan.getFlightPlan().get(listCounter).getControlTower();
        else
            nextCT = null;
    }

    public void printGPSLocation() {
        System.out.println("Latitude: " + this.gpsCoordinates.getLatitude() + "\n Longitude: " + this.gpsCoordinates.getLongitude());
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

    //Flights should synchronously update their positions
    @Override
    public void run() {
        if (this.flightCode.equals("BA664")) {
            System.out.println("-------TotalDistance------\n" + getDistance());
        }

        while (true) {
            try {
                // No need to synchronise here all Flight objects have their own instance of the
                // GPS location variables
                //if (nextCT == null) {
                //    break;
                //}

                Thread.sleep(Main.FLIGHT_UPDATE_TIME_OFFSET);
                updateGPSPosition();

                double distanceCurrentControlTower = GPSCoordinates.calcDistance(this.gpsCoordinates, currentControlTower.getGpsLocation());

                if (getCurrentDistance() >= getDistance()) {
                    break;
                }


                if (this.flightCode.equals("BA664")) {
                    System.out.println(getCurrentDistance());
                }


                //Only check for updating control towers if there is a new control tower to travel to
                if (nextCT != null) {
                    double distanceNextControlTower = GPSCoordinates.calcDistance(this.gpsCoordinates, nextCT.getGpsLocation());

                    if (distanceCurrentControlTower > distanceNextControlTower) {
                        this.currentControlTower.removeFlight(this);
                        updateControlTower();
                        this.currentControlTower.addFlight(this);
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.flightLanded = true;
        this.gpsCoordinates = this.currentControlTower.getGpsLocation();
        System.out.println(this.flightCode + "Has Landed!");
        this.currentControlTower.removeFlight(this);

    }
}
