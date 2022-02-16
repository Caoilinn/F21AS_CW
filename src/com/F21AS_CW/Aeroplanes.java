package com.F21AS_CW;
import java.util.HashSet;

public class Aeroplanes implements IWriteable{

    private HashSet<String> aeroplanes;

    public Aeroplanes(HashSet<String> aeroplanes) {
        this.aeroplanes = aeroplanes;
    }

    public HashSet<String> getAeroplanes() {
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
