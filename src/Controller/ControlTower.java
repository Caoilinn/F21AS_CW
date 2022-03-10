package Controller;

import Model.GPSCoordinates;

public class ControlTower {
  private GPSCoordinates gpsLocation;

    public ControlTower(GPSCoordinates gps) {
        gpsLocation = gps;
    }

    public GPSCoordinates getGpsLocation() {
        return gpsLocation;
    }
}
