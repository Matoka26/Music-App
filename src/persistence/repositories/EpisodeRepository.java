package persistence.repositories;

import models.Episode;
import models.Song;
import persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeRepository implements GenericRepository<Episode> {
    private static EpisodeRepository instance = null;
    private static PodcastRepository podcastRepo;
    private EpisodeRepository(){}
    public static EpisodeRepository getInstance(){
        if(instance == null){
            instance = new EpisodeRepository();
            podcastRepo = PodcastRepository.getInstance();
        }
        return instance;
    }

    @Override
    public void add(Episode obj) {
        String insertStatement = """
                INSERT INTO episode
                VALUES(episode_index.nextval,?,?,?,?,?,0)
                """;
        try{
            PreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertStatement);

            preparedStatement.setInt(1, obj.getPodcast().getPodcast_id());
            preparedStatement.setString(2,obj.getTitle());
            preparedStatement.setString(3, obj.getHost());
            preparedStatement.setString(4, obj.getGuest());
            preparedStatement.setInt(5, obj.getDuration());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Episode get(int id) {
        String selectQuery = """
                
                SELECT episode_id, podcast_id, title,
                    host,guest, duration, likes, plays
                    FROM episode 
                    WHERE episode_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Episode(
                        res.getInt(1),
                        podcastRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                        );
            }else{
                throw new RuntimeException();
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Episode> getAll() {
        String selectQuery = """
                
                SELECT episode_id, podcast_id, title,
                    host,guest, duration, likes, plays
                    FROM episode 
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Episode> eps = new ArrayList<>();
            while(res.next()) {
                Episode ep = new Episode(
                        res.getInt(1),
                        podcastRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                    eps.add(ep);
                }
                return eps;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Episode obj) {
        String updateStatement = """
                UPDATE episode
                SET
                    title = ?,
                    host = ?,
                    guest = ?,
                    duration = ?,
                    plays = ?
                WHERE
                    episode_id = ?
                """;
        try {
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatement);

            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setString(2, obj.getHost());
            preparedStatement.setString(3, obj.getGuest());
            preparedStatement.setInt(4, obj.getDuration());
            preparedStatement.setInt(5, obj.getPlays());
            preparedStatement.setInt(6, obj.getEpisode_id());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Episode obj) {
        String deleteStatement = """
                DELETE FROM episode
                WHERE episode_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getEpisode_id());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Episode> getAllbyPodcastId(int podcastId) {
        String selectQuery = """
                select episode_id, podcast_id, title, host, guest, duration, plays
                from episode
                where podcast_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, podcastId);
            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Episode> episodes = new ArrayList<>();
            while(res.next()){
                Episode ep = new Episode(
                    res.getInt(1),
                        podcastRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7)
                );
                episodes.add(ep);
            }
            return episodes;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
