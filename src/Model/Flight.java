package Model;

import View.IObserver;

import java.util.ArrayList;

public class Flight implements ISubject {

    private String flightCode;
    private Aeroplane plane;
    private Airport departure;
    private Airport destination;
    private String date;
    private String departureTime;
    private FlightPlan flightPlan;
    private Airline airline;
    private GPSCoordinates gpsCoordinates;
    private ControlTower nextCT;
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

        addAirline();
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

    public void updateGPSPosition() {
        double rLatCurrent = Math.toRadians(this.gpsCoordinates.getLatitude());
        double rLongCurrent = Math.toRadians(this.gpsCoordinates.getLongitude());
        double rLatNext = Math.toRadians(nextCT.getGpsLocation().getLatitude());
        double rLongNext = Math.toRadians(nextCT.getGpsLocation().getLongitude());

        double deltaLong = rLongCurrent - rLongNext;

        double bearing = Math.atan2(Math.sin(deltaLong) * Math.cos(rLatNext), Math.cos(rLatCurrent) * Math.sin(rLatNext) - Math.sin(rLatCurrent) * Math.cos(rLatNext) * Math.cos(deltaLong));
        System.out.println(Math.toDegrees(bearing));

        //Assuming that every update is one hour and speed is in kmph
        double angularDistance = this.plane.getCruiseSpeed() / GPSCoordinates.EARTH_RADIUS;

        double latNew = Math.asin((Math.sin(rLatCurrent) * Math.cos(angularDistance)) + (Math.cos(rLatCurrent) * Math.sin(angularDistance) * Math.cos(bearing)));
        double longNew = rLongCurrent + Math.atan2(Math.sin(bearing) * Math.sin(angularDistance) * Math.cos(rLatCurrent), Math.cos(angularDistance) - Math.sin(rLatCurrent) * Math.sin(latNew));

        this.gpsCoordinates = new GPSCoordinates(Math.toDegrees(latNew), Math.toDegrees(longNew));
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
}
