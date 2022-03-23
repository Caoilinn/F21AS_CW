package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public boolean WriteToFile() {
        try {
            FileWriter fileWriter = new FileWriter("LogFile");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String line : log) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
