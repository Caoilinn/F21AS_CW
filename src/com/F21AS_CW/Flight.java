package com.F21AS_CW;

import java.sql.Time;
import java.util.Date;

public class Flight {

    public Flight(String flightCode, String planeCode, Airport departure,
                  Airport destination, Date departureDate, Time departureTime, FlightPlan flightPlan) throws InvalidFlightException {
        if (destination == null || departure == null) {
            //Demo for the exception, would be better to throw if airports are not in the airports set
            throw new InvalidFlightException("Ths is a null airport");
        }
    }
}
