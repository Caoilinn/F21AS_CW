package Model;

import View.IObserver;

import java.util.ArrayList;

public class Aeroplane implements Comparable<Aeroplane>, ISubject {

    private String model;
    //speed in kilometres per hour
    private float cruiseSpeed;
    //fuel consumed per 100km of distance
    private float fuelConsumption;
    //the observers listening for changes instances of this class
    private ArrayList<IObserver> observers = new ArrayList<>();

    @Override
    public String toString() {
        return "Aeroplane{" +
                "model='" + model + '\'' +
                ", cruiseSpeed=" + cruiseSpeed +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }

    public Aeroplane(String model, float cruiseSpeed, float fuelConsumption) {
        this.model = model;
        this.cruiseSpeed = cruiseSpeed;
        this.fuelConsumption = fuelConsumption;

        if (model == null) {
            //Aeroplanes must have a model
            throw new InvalidFlightException("This is a null aeroplane");
        }
    }

    public String getModel() {
        return model;
    }


    public float getCruiseSpeed() {
        return cruiseSpeed;
    }


    public float getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public int compareTo(Aeroplane o) {
        return this.model.compareTo(o.getModel());
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
