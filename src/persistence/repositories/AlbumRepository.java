package persistence.repositories;

import models.Song;
import models.track.Album;
import persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlbumRepository implements GenericRepository<Album> {
    private static AlbumRepository instance = null;
    private static ArtistRepository artistRepo;
    private AlbumRepository(){}
    public  static AlbumRepository getInstance() {
        if (instance == null) {
            instance = new AlbumRepository();
            artistRepo = ArtistRepository.getInstance();
        }
        return instance;
    }


    @Override
    public void add(Album obj) {
        String insertTrack = """
                INSERT INTO track VALUES (track_index.nextval, ?, ?, ?,?)
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertTrack);

            preparedStatement.setInt(1,obj.getArtist().getArtist_id());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getPicture());
            preparedStatement.setDate(4, obj.getRelease_date());

            preparedStatement.executeUpdate();

            obj.setTrack_id(retrievLastId("TRACK_INDEX"));

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        String insertPod = """
                INSERT INTO album VALUES(podcast_index.nextval, ?, ?)
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertPod);

            preparedStatement.setInt(1, obj.getTrack_id());
            preparedStatement.setString(2, obj.getGenre());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Album get(int id) {
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.album_id, p.genre
                FROM album p, track t WHERE p.track_id = t.track_id
                AND p.album_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                return new Album(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        new ArrayList<>()
                );
            }
            else{
                throw new RuntimeException();
            }

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Album> getAll() {
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.album_id, p.genre
                FROM album p, track t WHERE p.track_id = t.track_id
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Album> albums = new ArrayList<>();

            while(res.next()){
                Album album = new Album(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        new ArrayList<>()
                        );
                albums.add(album);
            }
            return albums;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Album obj) {
        String updateStatementPod = """
                    UPDATE album
                    SET
                       genre = ?
                    WHERE
                        album_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementPod);

            preparedStatement.setString(1, obj.getGenre());
            preparedStatement.setInt(2, obj.getAlbum_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        String updateStatementTrk = """
                    UPDATE track
                    SET
                        name = ?,
                        picture = ?,
                        release_date = ?
                    WHERE
                        track_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementTrk);

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getPicture());
            preparedStatement.setDate(3, obj.getRelease_date());
            preparedStatement.setInt(4, obj.getTrack_id());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(Album obj) {
        String deleteStatement = """
                DELETE FROM album
                WHERE album_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getAlbum_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

        String deleteStatementTrk = """
                DELETE FROM track
                WHERE track_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatementTrk);

            preparedStatement.setInt(1, obj.getTrack_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public Album getByTrackId(int track_id) {
        String selectQuery = """
                SELECT tr.track_id,tr.artist_id, tr.name, tr.picture, tr.release_date,
                        a.album_id, a.genre
                FROM album a,track tr
                WHERE tr.track_id = ? and a.track_id = tr.track_id
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, track_id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Album(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        new ArrayList<>()
                );
            }
            return null;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
