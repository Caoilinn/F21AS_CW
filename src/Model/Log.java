package Model;

import java.util.ArrayList;

public class Log {
    private static Log instance;
    private ArrayList<String> log;

    private Log() {
        this.log = new ArrayList<String>();
    }
    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addToLog(String line) {
        if(line == null) {
            return;
        }
        log.add(line);
    }

    public String getLine(int x) {
        return log.get(x);
    }

    public int getSize() {
        return log.size();
    }
}
