package Model;

import View.IObserver;

import java.util.ArrayList;

public class Airport implements ISubject {
    private String name;
    private String code;
    private ControlTower controlTower;
    private ArrayList<IObserver> observers = new ArrayList<>();

    public Airport(String name, String code, ControlTower controlTower) {
        this.name = name;
        this.code = code;
        this.controlTower = controlTower;
    }

    public Airport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public Airport() {

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ControlTower getControlTower() {
        return controlTower;
    }

    public void setControlTower(ControlTower controlTower) {
        this.controlTower = controlTower;
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
