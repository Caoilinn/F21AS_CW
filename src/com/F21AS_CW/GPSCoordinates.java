package com.F21AS_CW;

public class GPSCoordinates {
    private String longitude;
    private String latitude;

    public GPSCoordinates(String l0ng, String lat) {
        longitude = l0ng;
        latitude = lat;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
