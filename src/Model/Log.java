package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Log {
    private static Log instance;
    private ArrayList<String> log;

    private Log() {
        this.log = new ArrayList<String>();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addToLog(String line) {
        if (line == null) {
            return;
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedDate = myDateObj.format(myFormatObj);

        log.add("Time Stamp: " + formattedDate + " \t" + line);
    }

    public String getLine(int x) {
        return log.get(x);
    }

    public int getSize() {
        return log.size();
    }

    public void WriteToFile() {
        try {

            FileWriter fileWriter = new FileWriter("LogFile");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String s : this.log) {
                bufferedWriter.write(s + "");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
