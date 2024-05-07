package models;

import models.track.Album;

public class Song {
    private int song_id;
    private Album album;
    private String title;
    private int duration;
    private int plays;
    private int likes;

    public Song(int song_id, Album album, String title,
                int duration, int plays, int likes) {
        this.song_id = song_id;
        this.album = album;
        this.title = title;
        this.duration = duration;
        this.plays = plays;
        this.likes = likes;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String toString(){
        return album.getPicture() + '\n' +
                title + '\n' +
                album.getArtist().getUsername() + '\n' +
                // for feat
                "Duration: " + duration + '\n' +
                '\u2764' + likes + '\n' +
                '\u25B6' + plays + '\n';
    }
}
