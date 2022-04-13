package Model;

import View.IObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Airlines implements IReadable, ISubject {

    private HashMap<String, Airline> airlines;
    private ArrayList<IObserver> observers = new ArrayList<>();

    //  public Airlines(HashSet<String> airlines) {this.airlines = airlines;}

    public Airlines() {
    }

    public HashMap<String, Airline> getAirlines() {
        return airlines;
    }


    @Override
    public boolean ReadFromFile() {
        try {
            this.airlines = new HashMap<String, Airline>();
            InputStream data = getClass().getResourceAsStream("/files/Airlines.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(data));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(";");

                //If the line doesn't contain a name and code then skip over the line
                if (fields.length < 2)
                    continue;

                String airlineCode = fields[0];
                String airlineName = fields[1];

                Airline airline = new Airline(airlineName, airlineCode);
                this.airlines.put(airlineCode, airline);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
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
