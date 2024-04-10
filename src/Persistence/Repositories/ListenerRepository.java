package Persistence.Repositories;

import Models.APP_User.Artist;
import Models.APP_User.Listener;
import Persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListenerRepository implements GenericRepository<Listener> {
    private static  ListenerRepository instance = null;
    private ListenerRepository(){}
    public static ListenerRepository getInstance(){
        if(instance == null){
            instance = new ListenerRepository();
        }
        return instance;
    }

    @Override
    public void add(Listener listener) {
        String insertUser = "INSERT INTO app_user VALUES (user_index.nextval, ?, ?, ?, ?, ?, ?, ?, 0)";

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertUser);

            preparedStatement.setString(1, listener.getFirst_name());
            preparedStatement.setString(2, listener.getLast_name());
            preparedStatement.setString(3, listener.getEmail());
            preparedStatement.setString(4, listener.getUsername());
            preparedStatement.setString(5, listener.getProfile_pic());
            preparedStatement.setString(6, listener.getPhone_number());
            preparedStatement.setDate(7, listener.getRegister_date());

            preparedStatement.executeUpdate();

            listener.setUser_id(retrievLastId("USER_INDEX") -1);

        }catch (SQLException ex){

            throw new RuntimeException(ex);
        }
        String insertArt = """
                INSERT INTO listener VALUES(listener_index.nextval, ?, 0)
                 """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertArt);

            preparedStatement.setInt(1, listener.getUser_id());

            preparedStatement.executeUpdate();

            listener.setListener(retrievLastId("LISTENER_INDEX") -1);
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Listener get(int id) {
        String selectQuery = """
                
                SELECT us.user_id, us.first_name,us.last_name, us.email,
                    us.username, us.profile_pic, us.register_date, 
                    us.phone_number,us.deleted,  a.listener_id,
                    a.time_played
                                
                    FROM app_user us, listener a WHERE a.user_id = us.user_id
                    AND a.listener_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Listener(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getDate(7),
                        res.getString(8),
                        res.getBoolean(9),
                        res.getInt(10),
                        res.getInt(11)
                );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Listener> getAll() {
        String selectQuery = """
                
                SELECT us.user_id, us.first_name,us.last_name, us.email,
                    us.username, us.profile_pic, us.register_date, 
                    us.phone_number,us.deleted,  a.listener_id,
                    a.time_played
                                
                    FROM app_user us, listener a WHERE a.user_id = us.user_id
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Listener> listeners = new ArrayList<>();

            while(res.next()){
                Listener listener = new Listener(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getDate(7),
                        res.getString(8),
                        res.getBoolean(9),
                        res.getInt(10),
                        res.getInt(11)
                );

                listeners.add(listener);
            }
            return listeners;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Listener obj) {
        String updateStatementLis = """
                    UPDATE listener
                    SET
                        time_played = ?
                    WHERE
                        listener_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementLis);

            preparedStatement.setInt(1, obj.getTime_played());
            preparedStatement.setInt(2, obj.getListener_id());


            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        String updateStatementUser = """
                    UPDATE app_user
                    SET
                        first_name = ?,
                        last_name = ?,
                        email = ?,
                        username = ?,
                        profile_pic = ?,
                        phone_number = ?,
                        register_date = ?,
                        deleted = ?
                    WHERE 
                        user_id = ?
                    """;

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementUser);

            preparedStatement.setString(1, obj.getFirst_name());
            preparedStatement.setString(2, obj.getLast_name());
            preparedStatement.setString(3, obj.getEmail());
            preparedStatement.setString(4, obj.getUsername());
            preparedStatement.setString(5, obj.getProfile_pic());
            preparedStatement.setString(6, obj.getPhone_number());
            preparedStatement.setDate(7, obj.getRegister_date());
            preparedStatement.setInt(8, (obj.getDeleted()) ? 1 : 0);
            preparedStatement.setInt(9, obj.getUser_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Listener obj) {
        String deleteStatementLis = """
                DELETE FROM listener
                WHERE listener_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatementLis);

            preparedStatement.setInt(1, obj.getListener_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        String deleteStatementUsr = """
                DELETE FROM app_user
                WHERE user_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatementUsr);

            preparedStatement.setInt(1, obj.getUser_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
