package Model;

import View.IObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Aeroplanes implements IReadable, ISubject {

    private HashMap<String, Aeroplane> aeroplanes;
    private ArrayList<IObserver> observers = new ArrayList<>();

    // public Aeroplanes(HashSet<Aeroplane> aeroplanes) {this.aeroplanes = aeroplanes;}


    public Aeroplanes() {
    }

    public HashMap<String, Aeroplane> getAeroplanes() {
        return aeroplanes;
    }



    @Override
    public boolean ReadFromFile() {

        try {
            this.aeroplanes = new HashMap<String, Aeroplane>();
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

                Aeroplane aeroplane = new Aeroplane(planeModel, planeCruiseSpeed, planeFuelConsump);
                this.aeroplanes.put(planeModel, aeroplane);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void registerObserver(IObserver obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : observers)
            obs.update();
    }

}