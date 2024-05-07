package models.track;

import models.app_user.Artist;
import models.Song;

import java.sql.Date;
import java.util.ArrayList;

public class Album extends Track{
    private int album_id;
    private String genre;
    ArrayList<Song> songs;

    public Album(int track_id, Artist artist, String name,
                 String picture, Date release_date, int album_id,
                 String genre,ArrayList<Song> songs) {
        super(track_id, artist, name, picture, release_date);
        this.album_id = album_id;
        this.genre = genre;
        this.songs = songs;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String toString(){
        return super.toString() +
                "Genre: " + genre + '\n';
    }
}
