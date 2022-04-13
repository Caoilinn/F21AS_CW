package Model;

import Controller.AddFlightController;
import View.IObserver;
import Controller.TravelController;
import com.F21AS_CW.Main;

import java.util.ArrayList;

public class ControlTower implements Runnable, ISubject {
    private GPSCoordinates gpsLocation;
    private String name;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private boolean newFlightAdded = false;

    //The flights that the control tower is currently looking after
    public ArrayList<Flight> ctFlights;

    public ControlTower(GPSCoordinates gps, String name) {
        gpsLocation = gps;
        ctFlights = new ArrayList<>();
        this.name = name;
    }

    public GPSCoordinates getGpsLocation() {
        return gpsLocation;
    }

    public synchronized void addFlight(Flight flight) {
        newFlightAdded = true;
        if (!this.ctFlights.contains(flight))
            this.ctFlights.add(flight);
    }

    public synchronized void removeFlight(Flight flight) {
        this.ctFlights.remove(flight);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        //Threaded behaviour here
        //Update GUI
        for (Flight flight : this.ctFlights) {
            Thread thread = new Thread(flight);
            thread.start();
        }
        while (true) {
            if (newFlightAdded) {
                newFlightAdded = false;
                if (ctFlights.size() > 0) {
                    Thread thread = new Thread(this.ctFlights.get(ctFlights.size() - 1));
                    thread.start();
                }
            }

            if (TravelController.suspended || AddFlightController.suspended) {
                synchronized (this) {
                    while (TravelController.suspended) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Thread.sleep(Main.FLIGHT_UPDATE_TIME_OFFSET);

                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void restartThreads() {
        synchronized (this) {
            notify();
        }
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
