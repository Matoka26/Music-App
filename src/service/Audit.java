package service;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {
    private static Audit instance = null;
    private FileWriter writer;
    private String path = "audit.csv";
    private static int count = 0;

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
    public <T> void write(String sqlStatement, T entity, String result){
        try{
            writer.write("Statement " + count++ + "\n");
            writer.write(sqlStatement + '\n' + "ObjectID: " + entity + '\n' + result);
            writer.write("\n---------------------" + "\n\n\n");
            writer.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
