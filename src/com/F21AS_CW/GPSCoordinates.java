package com.F21AS_CW;

public class GPSCoordinates {
    private double longitude;
    private double latitude;

    public GPSCoordinates( double lat,double l0ng) {
        longitude = l0ng;
        latitude = lat;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
