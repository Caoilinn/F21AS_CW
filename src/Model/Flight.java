package Model;

import View.IObserver;
import Controller.TravelController;
import com.F21AS_CW.Main;

import java.util.ArrayList;

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
    private ControlTower heading;
    private boolean flightLanded;
    public int listCounter = 1;
    private int updateCounter = 1;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private double distanceInOneUpdate;
    private double currentBearing;
    private boolean headingChanged = false;

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
        this.heading = nextCT;

        //Adds this flight to its departure airport's control tower
        this.currentControlTower = this.departure.getControlTower();
        this.currentControlTower.addFlight(this);
        addAirline();
        this.flightLanded = false;
        //one update is equal to 15 minutes of simulated time
        this.distanceInOneUpdate = this.plane.getCruiseSpeed() * 0.25;

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
        double roundedLat = Math.round(this.gpsCoordinates.getLatitude()*100.0)/100.0;
        double roundedLong = Math.round(this.gpsCoordinates.getLongitude()*100.0)/100.0;
        return  "Latitude " + roundedLat + " Longitude " + roundedLong;
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
        return this.updateCounter * (this.distanceInOneUpdate);
    }

    public void caluclateBearing() {
        double rLatCurrent = Math.toRadians(this.gpsCoordinates.getLatitude());
        double rLongCurrent = Math.toRadians(this.gpsCoordinates.getLongitude());

        double rLatNext = Math.toRadians(this.heading.getGpsLocation().getLatitude());
        double rLongNext = Math.toRadians(this.heading.getGpsLocation().getLongitude());

        double deltaLong = rLongNext - rLongCurrent;

        double bearingY = Math.sin(deltaLong) * Math.cos(rLatNext);

        double bearingX = Math.cos(rLatCurrent) * Math.sin(rLatNext) - Math.sin(rLatCurrent) * Math.cos(rLatNext) * Math.cos(deltaLong);

        this.currentBearing = (Math.toDegrees(Math.atan2(bearingY, bearingX)) + 360) % 360;

    }

    public synchronized void updateGPSPosition() {
        this.updateCounter++;
        caluclateBearing();
        double rLatCurrent = Math.toRadians(this.gpsCoordinates.getLatitude());
        double rLongCurrent = Math.toRadians(this.gpsCoordinates.getLongitude());

        // Every update is 15min sim time. Speed = kmph.
        double linearDistance = this.distanceInOneUpdate;
        double angularDistance = linearDistance / GPSCoordinates.EARTH_RADIUS;

        double latNew = Math.asin(Math.sin(rLatCurrent) * Math.cos(angularDistance) + Math.cos(rLatCurrent) * Math.sin(angularDistance) * Math.cos(Math.toRadians(this.currentBearing)));
        double longNew = rLongCurrent + Math.atan2(Math.sin(Math.toRadians(this.currentBearing)) * Math.sin(angularDistance) * Math.cos(rLatCurrent), Math.cos(angularDistance) - Math.sin(rLatCurrent) * Math.sin(latNew));
        longNew = (longNew + 3 * Math.PI) % (2 * Math.PI) - Math.PI;
        this.gpsCoordinates = new GPSCoordinates(Math.toDegrees(latNew), Math.toDegrees(longNew));
    }

    //The new control tower should set itself as the flight's current control tower
    public synchronized void updateControlTower() {
            this.listCounter++;
            if(nextCT != null) {
                currentControlTower = nextCT;
            }
            this.currentControlTower.addFlight(this);
            if (listCounter < flightPlan.getFlightPlan().size()) {
                nextCT = flightPlan.getFlightPlan().get(listCounter).getControlTower();
            } else
                nextCT = null;
            headingChanged = false;
            Log.getInstance().addToLog("Flight: " + flightCode + " is now communicating with CT: " + currentControlTower.getName());
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
        while (true) {
            if (currentControlTower == null)
                break;
            try {
                if (TravelController.suspended) {
                    synchronized (this) {
                        wait();
                    }
                }

                Thread.sleep(Main.FLIGHT_UPDATE_TIME_OFFSET);
                updateGPSPosition();

                double distanceCurrentControlTower = GPSCoordinates.calcDistance(this.gpsCoordinates, currentControlTower.getGpsLocation());

                double distanceToHeading = GPSCoordinates.calcDistance(this.gpsCoordinates, heading.getGpsLocation());


                //Only check for updating control towers if there is a new control tower to travel to
                if (nextCT != null) {
                    if (headingChanged) {
                        double distanceNextControlTower = GPSCoordinates.calcDistance(this.gpsCoordinates, nextCT.getGpsLocation());

                        if (distanceCurrentControlTower > distanceNextControlTower) {
                            this.currentControlTower.removeFlight(this);

                            updateControlTower();
                        }
                    }
                }

                if (distanceToHeading <= this.distanceInOneUpdate) {
                    if (listCounter < flightPlan.getFlightPlan().size()) {
                        this.heading = flightPlan.getFlightPlan().get(listCounter).getControlTower();
                        headingChanged = true;
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.flightLanded = true;
        Log.getInstance().addToLog(this.flightCode + " Has Landed!");
        this.currentControlTower.removeFlight(this);

    }

    public void restartThreads() {
        synchronized (this) {
            notify();
        }
    }
}
