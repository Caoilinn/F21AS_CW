package com.F21AS_CW;

import java.util.HashSet;

public class Airlines implements IWriteable{

    private HashSet<Airline> airlines;

    public Airlines(HashSet<Airline> airlines) {
        this.airlines = airlines;
    }

    public Airlines(){

    }

    public HashSet<Airline> getAirlines() {
        return airlines;
    }

    @Override
    public boolean WriteToFile() {
        return false;
    }

    @Override
    public boolean ReadFromFile() {
        return false;
    }

    @Override
    public void ProcessLine() {

    }
}
