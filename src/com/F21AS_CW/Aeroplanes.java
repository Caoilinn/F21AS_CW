package com.F21AS_CW;
import java.util.HashSet;

public class Aeroplanes implements IWriteable{

    private HashSet<Aeroplane> aeroplanes;

    public Aeroplanes(HashSet<Aeroplane> aeroplanes) {
        this.aeroplanes = aeroplanes;
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
