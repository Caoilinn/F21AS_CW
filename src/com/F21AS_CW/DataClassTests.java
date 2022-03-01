/*
package com.F21AS_CW;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;


public class DataClassTests {
    FlightPlan flightPlan;
    @Test
    public void testFlightPlan() {
        LinkedList<Airport> emptyList = new LinkedList<Airport>();
        flightPlan = new FlightPlan(emptyList);
        assertEquals(0,flightPlan.getFlightPlanTotalDistance());
        GPSCoordinates gps1 = new GPSCoordinates(-2,49);
        GPSCoordinates gps2 = new GPSCoordinates(-12,41);
        flightPlan.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(0,flightPlan.getFlightPlanTotalDistance());
        flightPlan.addToPlan(new Airport("Test","Test", new ControlTower(gps2)));
        assertEquals(1185,flightPlan.getFlightPlanTotalDistance());
    }
    @Test
    public void testFlights() {

    }
    @Test
    public void testAirports() {

    }


}
*/