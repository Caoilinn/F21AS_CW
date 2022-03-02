package com.F21AS_CW;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Aeroplanes implements IWriteable{

    private HashSet<Aeroplane> aeroplanes;


   

   // public Aeroplanes(HashSet<Aeroplane> aeroplanes) {this.aeroplanes = aeroplanes;}


    public Aeroplanes(){

        try {
            this.aeroplanes = new HashSet<Aeroplane>();
            File aeroplanesFile = new File("Aeroplanes");
            Scanner reader = new Scanner(aeroplanesFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(";");

                //If the line doesn't contain the required fields then skip over the line
                if (fields.length < 3)
                    continue;

                String planeModel = fields[0];
                float planeCruiseSpeed = Float.parseFloat(fields[2]);
                float planeFuelConsump = Float.parseFloat(fields[3]);

                Aeroplane aeroplane = new Aeroplane(planeModel, planeCruiseSpeed,planeFuelConsump);
                this.aeroplanes.add(aeroplane);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public HashSet<Aeroplane> getAeroplanes() {
        return aeroplanes;
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