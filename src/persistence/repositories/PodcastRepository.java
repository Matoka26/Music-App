package persistence.repositories;

import models.Song;
import models.track.Album;
import models.track.Podcast;
import persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PodcastRepository implements GenericRepository<Podcast> {
    private static PodcastRepository instance = null;
    private static ArtistRepository artistRepo;
    private PodcastRepository() {}

    public static PodcastRepository getInstance(){
        if(instance == null){
            instance = new PodcastRepository();
            artistRepo = ArtistRepository.getInstance();
        }

        return instance;
    }

    @Override
    public void add(Podcast podcast) {
        String insertTrack = """
                INSERT INTO track VALUES (track_index.nextval, ?, ?, ?,?)
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertTrack);

            preparedStatement.setInt(1,podcast.getArtist().getArtist_id());
            preparedStatement.setString(2, podcast.getName());
            preparedStatement.setString(3, podcast.getPicture());
            preparedStatement.setDate(4, podcast.getRelease_date());

            preparedStatement.executeUpdate();

            podcast.setTrack_id(retrievLastId("TRACK_INDEX"));
            audit.write(insertTrack, podcast.getTrack_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(insertTrack, podcast.getTrack_id(), "Error while adding track");
            throw new RuntimeException(ex);
        }
        String insertPod = """
                INSERT INTO PODCAST VALUES(podcast_index.nextval, ?, ?, ?)
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertPod);

            preparedStatement.setInt(1, podcast.getTrack_id());
            preparedStatement.setString(2, podcast.getTopic());
            preparedStatement.setString(3, podcast.getDescription());

            preparedStatement.executeUpdate();
            audit.write(insertPod, podcast.getPodcast_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(insertPod, podcast.getPodcast_id(), "Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Podcast get(int id) {
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.podcast_id, p.topic, p.description
                FROM podcast p, track t WHERE p.track_id = t.track_id
                AND p.podcast_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){

                return new Podcast(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
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
    public ArrayList<Podcast> getAll() {
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.podcast_id, p.topic, p.description
                FROM podcast p, track t WHERE p.track_id = t.track_id
                """;

        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Podcast> podcasts = new ArrayList<>();

            while(res.next()){
                Podcast podcast = new Podcast(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        new ArrayList<>()
                );
                podcasts.add(podcast);
            }
            return podcasts;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Podcast obj) {
        String updateStatementPod = """
                    UPDATE podcast
                    SET
                       topic = ?,
                       description = ?
                    WHERE
                        podcast_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatementPod);

            preparedStatement.setString(1, obj.getTopic());
            preparedStatement.setString(2, obj.getDescription());
            preparedStatement.setInt(3, obj.getPodcast_id());

            preparedStatement.executeUpdate();
            audit.write(updateStatementPod, obj.getPodcast_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(updateStatementPod, obj.getPodcast_id(), "Error: " + ex.getMessage());
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
            audit.write(updateStatementTrk, obj.getTrack_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(updateStatementTrk, obj.getTrack_id(), "Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(Podcast obj) {
        String deleteStatement = """
                DELETE FROM podcast
                WHERE podcast_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getPodcast_id());

            preparedStatement.executeUpdate();
            audit.write(deleteStatement, obj.getPodcast_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(deleteStatement, obj.getPodcast_id(), "Error: " + ex.getMessage());
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
            audit.write(deleteStatementTrk, obj.getTrack_id(), "Done successfully");
        }catch (SQLException ex){
            audit.write(deleteStatementTrk, obj.getTrack_id(), "Error: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public Podcast getByTrackId(int track_id) {
        String selectQuery = """
                SELECT tr.track_id,tr.artist_id, tr.name, tr.picture, tr.release_date,
                        a.podcast_id, a.topic, a.description
                FROM podcast a,track tr
                WHERE tr.track_id = ? and a.track_id = tr.track_id
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, track_id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Podcast(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        new ArrayList<>()
                );
            }
            return null;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Podcast> getFewRandom(){
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.podcast_id, p.topic, p.description
                FROM podcast p, track t 
                WHERE p.track_id = t.track_id
                ORDER BY dbms_random.value
                FETCH FIRST 10 ROWS ONLY
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Podcast> podcasts = new ArrayList<>();
            while(res.next()){
                Podcast podcast = new Podcast(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        new ArrayList<>()
                );
                podcasts.add(podcast);
            }
            return podcasts;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Podcast> getByTitle(String title){
        String selectQuery = """
                SELECT t.track_id, t.artist_id, t.name,
                        t.picture, t.release_date,
                        p.podcast_id, p.topic, p.description
                FROM podcast p, track t
                WHERE p.track_id = t.track_id
                    AND lower(t.name) = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setString(1, title.toLowerCase());
            ResultSet res = preparedStatement.executeQuery();


            ArrayList<Podcast> podcasts = new ArrayList<>();
            while(res.next()){
                Podcast podcast = new Podcast(
                        res.getInt(1),
                        artistRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getDate(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        new ArrayList<>()
                );
                podcasts.add(podcast);
            }
            return podcasts;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
