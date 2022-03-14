package Model;

public class GPSCoordinates {
    private double longitude;
    private double latitude;
    public static final double EARTH_RADIUS = 6371.0;

    public GPSCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //implements pseudo-code example given in coursework spec, gives results that are approximately correct (Checked by hand using a calculator)
    public static double calcDistance(GPSCoordinates coords1, GPSCoordinates coords2) {
        double rlat1 = coords1.getLatitude() * (Math.PI / 180);

        double rlong1 = coords1.getLongitude() * (Math.PI / 180);

        double rlat2 = coords2.getLatitude() * (Math.PI / 180);

        double rlong2 = coords2.getLongitude() * (Math.PI / 180);

        double deltaLat = rlat2 - rlat1;

        double deltaLong = rlong2 - rlong1;

        double trig = ((Math.pow(Math.sin(deltaLat / 2), 2))) + (Math.cos(rlat1)) * (Math.cos(rlat2)) * (Math.pow(Math.sin(deltaLong / 2), 2));

        double distance = 2 * EARTH_RADIUS * (Math.asin(Math.sqrt(trig)));

        //returns int for ease of use with gui
        return distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
