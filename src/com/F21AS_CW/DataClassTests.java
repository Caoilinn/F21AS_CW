
package com.F21AS_CW;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;


public class DataClassTests {
    @BeforeAll
    public static void setUpDataClasses() {
        //Creates instance of each Data Structure Class so we have access to them for each test
        Airlines airlines = new Airlines();
        Airports airports = new Airports();
        Aeroplanes aeroplanes = new Aeroplanes();
        Flights flights = new Flights();
    }
    @Test
    public void testFlightPlan() {
        //numbers given for flight were compared to results given from various online calculators,
        // due to the pseudo-code lacking precision the total distance figures were slightly off from figures found online,
        // thus as long as the values returned by our code were within 100KM they were made the new expected value, as they suit the purpose of this coursework

        //creates and tests fresh FlightPlan, tests that distance isn't being calculated until more than one Airport is present in the plan
        FlightPlan flightPlan = new FlightPlan();
        assertEquals(0,flightPlan.getFlightPlanTotalDistance());
        GPSCoordinates gps1 = new GPSCoordinates(49,-2);
        GPSCoordinates gps2 = new GPSCoordinates(41,-12);
        flightPlan.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(0,flightPlan.getFlightPlanTotalDistance());

        //test that calculation method gives correct answer
        flightPlan.addToPlan(new Airport("Test","Test", new ControlTower(gps2)));
        assertEquals(1186,flightPlan.getFlightPlanTotalDistance());

        //getting flight and associated flightPlan from flights class
        FlightPlan testFlightPlan = Flights.getFlights().get("BA534").getFlightPlan();
        //test that flight has correct total distance
        assertEquals(11278,testFlightPlan.getFlightPlanTotalDistance());

        //testing flightPlan cannot exceed 10 airports in length
        FlightPlan testFlightPlan2 = Flights.getFlights().get("AF670").getFlightPlan();
        assertEquals(5,testFlightPlan2.getFlightPlan().size());
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(6,testFlightPlan2.getFlightPlan().size());
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(7,testFlightPlan2.getFlightPlan().size());
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(8,testFlightPlan2.getFlightPlan().size());
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(9,testFlightPlan2.getFlightPlan().size());
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(10,testFlightPlan2.getFlightPlan().size());

        //this would take the flight plan over size 10, should print out warning and return without adding the given airport
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(10,testFlightPlan2.getFlightPlan().size());

    }
    @Test
    public void testFlights() {

    }

}
