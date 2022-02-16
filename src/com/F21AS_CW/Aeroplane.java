package com.F21AS_CW;

public class Aeroplane {

    private String model;
    private float cruiseSpeed;
    private float fuelConsumption;

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

    public void setModel(String model) {
        this.model = model;
    }

    public float getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(float cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

}
