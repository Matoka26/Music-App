package persistence.repositories;

import models.Song;
import persistence.GenericRepository;
import oracle.jdbc.OraclePreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongRepository implements GenericRepository<Song> {

    private static SongRepository instance = null;
    private static AlbumRepository albumRepo;
    private SongRepository(){}

    public static SongRepository getInstance(){
        if(instance == null){
            instance = new SongRepository();
            albumRepo = AlbumRepository.getInstance();
        }
        return instance;
    }

    @Override
    public void add(Song obj) {
        String insertStatement = """
                INSERT INTO SONG VALUES (song_index.nextval, ?, ?, ?, 0, 0) 
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(insertStatement);

            preparedStatement.setInt(1, obj.getAlbum().getAlbum_id());
            preparedStatement.setString(2, obj.getTitle());
            preparedStatement.setInt(3, obj.getDuration());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Song get(int id) {
        String selectQuery = """
                SELECT song_id, album_id, title,
                        duration, likes, plays
                FROM song
                WHERE song_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                return new Song(
                        res.getInt(1),
                        albumRepo.get(res.getInt(2)),
                        res.getString(3),
                        res.getInt(4),
                        res.getInt(5),
                        res.getInt(6)
                );
            }
            return null;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Song> getAll() {
        String selectQuery = """
                SELECT song_id, album_id, title,
                        duration, likes, plays
                FROM song
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(selectQuery);

            ResultSet res = preparedStatement.executeQuery();

            ArrayList<Song> songs = new ArrayList<>();
            while(res.next()){
                Song song =  new Song(
                            res.getInt(1),
                            albumRepo.get(res.getInt(2)),
                            res.getString(3),
                            res.getInt(4),
                            res.getInt(5),
                            res.getInt(6)
                            );
                songs.add(song);
            }
            return songs;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Song obj) {
        String updateStatement = """
                UPDATE song
                SET
                   title = ?,
                   duration = ?,
                   likes = ?,
                   plays = ?
                WHERE
                    song_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(updateStatement);

            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setInt(2, obj.getDuration());
            preparedStatement.setInt(3, obj.getLikes());
            preparedStatement.setInt(4, obj.getPlays());
            preparedStatement.setInt(5, obj.getSong_id());

            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Song obj) {
        String deleteStatement = """
                DELETE FROM song
                WHERE song_id = ?
                """;
        try{
            OraclePreparedStatement preparedStatement = (OraclePreparedStatement)
                    dbConnection.getContext().prepareStatement(deleteStatement);

            preparedStatement.setInt(1, obj.getSong_id());

            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
