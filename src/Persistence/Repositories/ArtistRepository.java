package Persistence.Repositories;

import Models.APP_User.Artist;
import Persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistRepository implements GenericRepository<Artist> {
    private static ArtistRepository instance = null;
    private ArtistRepository() {}

    public static ArtistRepository getInstance() {
        if (instance == null) {
            instance = new ArtistRepository();
        }
        return instance;
    }

    private int retrievLastUserId(){
        String selectQuery = "SELECT last_number FROM user_sequences WHERE SEQUENCE_NAME = 'USER_INDEX'";
        String result = null;
        try{
            PreparedStatement preparedStatement = dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("last_number");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Integer.parseInt(result);
    }
    private int retrievLastArtistId(){
        String selectQuery = "SELECT last_number FROM user_sequences WHERE SEQUENCE_NAME = 'ARTIST_INDEX'";
        String result = null;
        try{
            PreparedStatement preparedStatement = dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("last_number");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Integer.parseInt(result);
    }

    @Override
    public void add(Artist artist) {
        String insertUser = "INSERT INTO app_user VALUES (user_index.nextval, ?, ?, ?, ?, ?, ?, ?, 0)";

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertUser);

            preparedStatement.setString(1, artist.getFirst_name());
            preparedStatement.setString(2, artist.getLast_name());
            preparedStatement.setString(3, artist.getEmail());
            preparedStatement.setString(4, artist.getUsername());
            preparedStatement.setString(5, artist.getProfile_pic());
            preparedStatement.setString(6, artist.getPhone_number());
            preparedStatement.setDate(7, artist.getRegister_date());

            preparedStatement.executeUpdate();

            artist.setUser_id(retrievLastUserId() -1);

        }catch (SQLException ex){

            throw new RuntimeException(ex);
        }
        String insertArt = """
                INSERT INTO artist VALUES(artist_index.nextval, ?, ?, ?, ?, ?)
                 """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertArt);

            preparedStatement.setInt(1, artist.getUser_id());
            preparedStatement.setString(2, artist.getDescription());
            preparedStatement.setInt(3, artist.getMonthly_listeners());
            preparedStatement.setInt(4, artist.getVerified() ? 1 : 0);
            preparedStatement.setString(5, artist.getLabel());

            preparedStatement.executeUpdate();

            artist.setArtist_id(retrievLastArtistId() -1);
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Artist get(int id){
        String selectQuery = """
                
                SELECT us.user_id, us.first_name,us.last_name, us.email,
                    us.username, us.profile_pic, us.register_date, 
                    us.phone_number,us.deleted,  a.artist_id, a.description,
                    a.monthly_listeners, a.verified, a.lable
                                
                    FROM app_user us, artist a WHERE a.user_id = us.user_id
                    AND a.artist_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                Artist artist = new Artist(
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
                        res.getString(11),
                        res.getInt(12),
                        res.getBoolean(13),
                        res.getString(14),
                        new ArrayList<>()
                );
                return artist;
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Artist> getAll(){
        String selectQuery = """
                    SELECT us.user_id, us.first_name,us.last_name, us.email,
                    us.username, us.profile_pic, us.register_date,
                    us.phone_number, a.artist_id, a.description,
                    a.monthly_listeners, a.verified, a.lable
                                
                    FROM app_user us, artist a WHERE a.user_id = us.user_id
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Artist> artists = new ArrayList<>();

            while(res.next()){
                Artist artist = new Artist(
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
                        res.getString(11),
                        res.getInt(12),
                        res.getBoolean(13),
                        res.getString(14),
                        new ArrayList<>()
                );
                artists.add(artist);
            }
            return artists;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Artist obj){
        String updateStatementArt = """
                    UPDATE artist
                    SET
                        description = ?,
                        monthly_listeners = ?,
                        verified = ?,
                        lable = ?
                    WHERE
                        artist_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementArt);

            preparedStatement.setString(1, obj.getDescription());
            preparedStatement.setInt(2, obj.getMonthly_listeners());
            preparedStatement.setInt(3, (obj.getVerified()) ? 1 : 0);
            preparedStatement.setString(4, obj.getLabel());
            preparedStatement.setInt(5, obj.getArtist_id());

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
    public void delete(Artist obj){
        String deleteStatementArt = """
                DELETE FROM artist
                WHERE artist_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatementArt);

            preparedStatement.setInt(1, obj.getArtist_id());

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