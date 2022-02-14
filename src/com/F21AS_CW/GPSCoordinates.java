package com.F21AS_CW;

public class GPSCoordinates {
    private int longitude;
    private int latitude;

    public GPSCoordinates(int l0ng, int lat) {
        longitude = l0ng;
        latitude = lat;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }
}
