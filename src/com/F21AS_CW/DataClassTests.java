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
        LinkedList<ControlTower> emptyList = new LinkedList<ControlTower>();
        flightPlan = new FlightPlan(emptyList);
        assertEquals(0,flightPlan.getFlightPlanTotalDistance());
        GPSCoordinates gps1 = new GPSCoordinates(0,51);
        GPSCoordinates gps2 = new GPSCoordinates(-55,25);
        flightPlan.addToPlan(new ControlTower(gps1));
        //assertEquals(0,flightPlan.getFlightPlanTotalDistance());
        flightPlan.addToPlan(new ControlTower(gps2));
        assertEquals(5451,flightPlan.getFlightPlanTotalDistance());
    }
    @Test
    public void testFlights() {

    }
    @Test
    public void testAirports() {

    }


}
