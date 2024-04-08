package Service;

import java.sql.*;
public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Mihai";
    private static final String PASSWROD = "parola";

    private static Connection context;
    private static DBConnection instance;

    private DBConnection(){
        try{
            context = DriverManager.getConnection(URL, USER, PASSWROD);
        } catch(SQLException ex){
            System.out.println("Error connectiong to database" + ex.getMessage());
        }
    }
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getContext(){
        return context;
    }
    public static void setContext(Connection context){
        DBConnection.context = context;
    }
    public static void setInstance(DBConnection instance){
        DBConnection.instance = instance;
    }
}

