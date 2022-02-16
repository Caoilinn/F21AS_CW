package com.F21AS_CW;

public class ControlTower {
  private GPSCoordinates gpsLocation;

    public ControlTower(GPSCoordinates gps) {
        gpsLocation = gps;
    }

    public GPSCoordinates getGpsLocation() {
        return gpsLocation;
    }
}
