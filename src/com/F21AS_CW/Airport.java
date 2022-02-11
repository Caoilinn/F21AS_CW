package com.F21AS_CW;

public class Airport {

    private String name;
    private String code;
    private GPSCoordinates gpsLocation;
    private ControlTower controlTower;

    public Airport(String name, String code, GPSCoordinates gpsLocation, ControlTower controlTower) {
        this.name = name;
        this.code = code;
        this.gpsLocation = gpsLocation;
        this.controlTower = controlTower;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public GPSCoordinates getGpsLocation() {
        return gpsLocation;
    }

    public ControlTower getControlTower() {
        return controlTower;
    }
}
