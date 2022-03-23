package Model;

import Model.GPSCoordinates;
import View.IObserver;
import com.F21AS_CW.Main;

import java.util.ArrayList;

public class ControlTower implements Runnable, ISubject {
    private GPSCoordinates gpsLocation;
    private String name;
    private ArrayList<IObserver> observers = new ArrayList<>();


    //The flights that the control tower is currently looking after
    private ArrayList<Flight> ctFlights;

    public ControlTower(GPSCoordinates gps, String name) {
        gpsLocation = gps;
        ctFlights = new ArrayList<>();
        this.name = name;
    }

    public GPSCoordinates getGpsLocation() {
        return gpsLocation;
    }

    public synchronized void addFlight(Flight flight) {
        this.ctFlights.add(flight);
    }

    public synchronized void removeFlight(Flight flight) {
        this.ctFlights.remove(flight);
    }


    @Override
    public void run() {
        //Threaded behaviour here
        //Update GUI
        for (Flight flight : this.ctFlights) {
            Thread thread = new Thread(flight);
            thread.start();
        }
        while (!this.ctFlights.isEmpty()) {
            try {
                Thread.sleep(Main.FLIGHT_UPDATE_TIME_OFFSET);
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exited ");
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
    public synchronized void notifyObservers() {
        for (IObserver obs : this.observers) {
            obs.update();
        }
    }
}
