package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Audit {
    private static Audit instance = null;
    private FileWriter writer;
    private String path = "audit.csv";

    private Audit(){
        try{
            writer = new FileWriter(path);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Audit getInstance(){
        if(instance == null){
            instance = new Audit();
        }
        return instance;
    }

    private String getTime(){
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

        int hours = now.getHour();
        int minutes = now.getMinute();
        int seconds = now.getSecond();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void write(int id, String action){
        try{
            writer.write(id + "," + action + "," + getTime() + "\n");
            writer.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
