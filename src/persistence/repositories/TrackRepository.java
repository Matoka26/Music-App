package persistence.repositories;

import models.app_user.Artist;
import models.track.Track;
import oracle.jdbc.OraclePreparedStatement;
import persistence.GenericRepository;
import service.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackRepository{
    private static TrackRepository instance = null;
    DBConnection dbConnection = DBConnection.getInstance();
    private TrackRepository() {}

    public static TrackRepository getInstance() {
        if (instance == null) {
            instance = new TrackRepository();
        }
        return instance;
    }

    public ArrayList<Track> getAllByArtistId(Artist artist){
        String selectQuery = """
                    SELECT track_id, name, picture, release_date
                    FROM track
                    WHERE artist_id = ?
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, artist.getArtist_id());

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Track> tracks = new ArrayList<>();


            while (res.next()) {
                Track track = new Track(
                        res.getInt(1),
                        artist,
                        res.getString(2),
                        res.getString(3),
                        res.getDate(4)
                );
                tracks.add(track);
            }
            return tracks;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(int id){
        String deleteStatement = """
                DELETE FROM track
                WHERE track_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
