package persistence.repositories;

import models.app_user.Listener;
import persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.lang.runtime.SwitchBootstraps;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

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
        String insertUser = "INSERT INTO app_user VALUES (user_index.nextval, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertUser);

            preparedStatement.setString(1, listener.getFirst_name());
            preparedStatement.setString(2, listener.getLast_name());
            preparedStatement.setString(3, listener.getEmail());
            preparedStatement.setString(4, listener.getUsername());
            preparedStatement.setString(5, listener.getPassword());
            preparedStatement.setString(6, listener.getProfile_pic());
            preparedStatement.setString(7, listener.getPhone_number());
            preparedStatement.setDate(8, listener.getRegister_date());

            preparedStatement.executeUpdate();

            listener.setUser_id(retrievLastId("USER_INDEX"));

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

            listener.setListener(retrievLastId("LISTENER_INDEX"));
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Listener get(int id) {
        String selectQuery = """
                
                SELECT us.user_id, us.first_name,us.last_name, us.email,
                    us.username, us.password, us.profile_pic, us.register_date, 
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
                        res.getString(7),
                        res.getDate(8),
                        res.getString(9),
                        res.getBoolean(10),
                        res.getInt(11),
                        res.getInt(12)
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
                    us.username, us.password, us.profile_pic, us.register_date, 
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
                        res.getString(7),
                        res.getDate(8),
                        res.getString(9),
                        res.getBoolean(10),
                        res.getInt(11),
                        res.getInt(12)
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
                        password = ?,
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
            preparedStatement.setString(5, obj.getPassword());
            preparedStatement.setString(6, obj.getProfile_pic());
            preparedStatement.setString(7, obj.getPhone_number());
            preparedStatement.setDate(8, obj.getRegister_date());
            preparedStatement.setInt(9, (obj.getDeleted()) ? 1 : 0);
            preparedStatement.setInt(10, obj.getUser_id());

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

    public Map.Entry<Integer, Integer> validateLogin(String username, String password){
        /* returns the Map.Entry containing user_id and artist_id or null if none */

        String selectQuery = """
                SELECT us.user_id, ar.listener_id
                FROM app_user us, listener ar
                WHERE us.username = ? AND us.password = ?
                     AND ar.user_id = us.user_id AND us.deleted = 0
             """;

        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                int userId = res.getInt(1);
                int artistId = res.getInt(2);
                return new AbstractMap.SimpleEntry<>(userId, artistId);
            } else {
                return null; // No matching user found
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void likeSong(int listener_id, int song_id) {
        String insertStatement = """
                INSERT INTO listener_likes_song VALUES(?, ?)
                """;
        try{
            PreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertStatement);

            preparedStatement.setInt(1, listener_id);
            preparedStatement.setInt(2, song_id);

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void unlikeSong(int listener_id, int song_id) {
        String deleteStatement = """
                DELETE FROM listener_likes_song
                WHERE listener_id = ? AND song_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, listener_id);
            preparedStatement.setInt(2, song_id);

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
