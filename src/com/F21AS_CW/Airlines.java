package com.F21AS_CW;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Airlines implements IWriteable{

    private HashSet<Airline> airlines;

  //  public Airlines(HashSet<String> airlines) {this.airlines = airlines;}

    public Airlines( ){
        try {
            this.airlines = new HashSet<String>();
            File airlinesFile = new File("Airlines");
            Scanner Reader = new Scanner(airlinesFile);
            while (Reader.hasNextLine()) {
                String airline = Reader.nextLine();
                //String line = Reader.nextLine();
                // String [] fields = line.split(";");
                // String airlineCode = fields[0];
                //String airline = fields[1];
                this.airlines.add(airline);
                //System.out.println(airline);
            }
            System.out.println(airlines);
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
