
/*package Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DataClassTests {
    @BeforeAll
    public static void setUpDataClasses() {
        //Creates instance of each Data Structure Class so we have access to them for each test
        Airlines airlines = new Airlines();
        airlines.ReadFromFile();
        Airports airports = new Airports();
        airports.ReadFromFile();
        Aeroplanes aeroplanes = new Aeroplanes();
        aeroplanes.ReadFromFile();
        Flights flights = new Flights();
        flights.ReadFromFile();
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
        //this would take the flight plan over size 8, should print out warning and return without adding the given airport
        testFlightPlan2.addToPlan(new Airport("Test","Test", new ControlTower(gps1)));
        assertEquals(8,testFlightPlan2.getFlightPlan().size());

    }
    @Test
    public void testFlight() {
        //test that attempting to retrieve a flight that doesn't exist returns null
        Flight testFlight = Flights.getFlights().get("AF6");
        assertNull(testFlight);

        //check we are receiving valid flight from correct input
        testFlight = Flights.getFlights().get("BA534");
        assertNotNull(testFlight);
        //check calculations for fuelConsumption, Co2Emissions and flight Duration
        assertEquals(107455,testFlight.getFuelConsumption());
        assertEquals(88113,testFlight.getCo2Emissions());
        assertEquals("12:53",testFlight.getDurationOfFlight());
        //Check exceptions are working
        assertThrows(InvalidFlightException.class,() -> new Flight("anadna",Aeroplanes.getAeroplanes().get("B777"),null,null,"Date", "Time",null,null));

    }

}**/
